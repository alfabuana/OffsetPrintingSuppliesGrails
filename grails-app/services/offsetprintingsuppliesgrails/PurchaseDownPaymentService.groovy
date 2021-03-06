package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class PurchaseDownPaymentService {
	PurchaseDownPaymentValidationService purchaseDownPaymentValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return PurchaseDownPayment.get(object)
		}
		def getList(){
			return PurchaseDownPayment.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.contact = Contact.find{id == object.contactId}
			object.receivable = Receivable.find{id == object.receivableId}
			object.payable = Payable.find{id == object.payableId}
			object.currency = Currency.find{id == object.currencyId}
			object.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			object = purchaseDownPaymentValidationService.createObjectValidation(object as PurchaseDownPayment)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = PurchaseDownPayment.read(object.id)
			valObject.contact = Contact.find{id == object.contactId}
			valObject.receivable = Receivable.find{id == object.receivableId}
			valObject.payable = Payable.find{id == object.payableId}
			valObject.code = object.code
			valObject.downPaymentDate = object.downPaymentDate
			valObject.dueDate = object.dueDate
			valObject.currency = Currency.find{id == object.currencyId}
			if (NumberUtils.isNumber(object.exchangeRateAmount) ==  true)
			{
				valObject.exchangeRateAmount = Double.parseDouble(object.exchangeRateAmount)
			}
			else
			{
				valObject.exchangeRateAmount = null
			}
			valObject.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			if (NumberUtils.isNumber(object.totalAmount) ==  true)
			{
				valObject.totalAmount = Double.parseDouble(object.totalAmount)
			}
			else
			{
				valObject.totalAmount = null
			}
			valObject = purchaseDownPaymentValidationService.updateObjectValidation(valObject)
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
			def newObject = PurchaseDownPayment.get(object.id)
			newObject = purchaseDownPaymentValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = PurchaseDownPayment.get(object.id)
			newObject = purchaseDownPaymentValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = PurchaseDownPayment.get(object.id)
			newObject = purchaseDownPaymentValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
