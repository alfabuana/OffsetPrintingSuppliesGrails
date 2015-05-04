package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class PaymentRequestService {
	PaymentRequestValidationService paymentRequestValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return PaymentRequest.get(object)
		}
		def getList(){
			return PaymentRequest.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.contact = Contact.find{id == object.contactId}
			object.currency = Currency.find{id == object.currencyId}
			object.accountPayable = Payable.find{id == object.payableId}
			object.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			object = paymentRequestValidationService.createObjectValidation(object as PaymentRequest)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = PaymentRequest.read(object.id)
			valObject.contact = Contact.find{id == object.contactId}
			valObject.description = object.description
			valObject.code = object.code
			valObject.currency = Currency.find{id == object.currencyId}
			valObject.accountPayable = Payable.find{id == object.payableId}
			if (NumberUtils.isNumber(object.amount) ==  true)
			{
				valObject.amount = Double.parseDouble(object.amount)
			}
			else
			{
				valObject.amount = null
			}
			if (NumberUtils.isNumber(object.exchangeRateAmount) ==  true)
			{
				valObject.exchangeRateAmount = Double.parseDouble(object.exchangeRateAmount)
			}
			else
			{
				valObject.exchangeRateAmount = null
			}
			valObject.requestDate = object.requestDate
			valObject.dueDate = object.dueDate
			valObject.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			valObject.noBukti = object.noBukti
			valObject = paymentRequestValidationService.updateObjectValidation(valObject)
			if (valObject.errors.getErrorCount() == 0)
			{
				valObject.save()
			}
			else
			{
				valObject.discard()
			}
			return valObject
		}
		def softDeletedObject(def object){
			def newObject = PaymentRequest.get(object.id)
			newObject = paymentRequestValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = PaymentRequest.get(object.id)
			newObject = paymentRequestValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = PaymentRequest.get(object.id)
			newObject = paymentRequestValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
