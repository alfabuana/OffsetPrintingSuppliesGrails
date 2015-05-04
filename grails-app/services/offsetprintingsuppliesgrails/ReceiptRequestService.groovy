package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class ReceiptRequestService {
	ReceiptRequestValidationService receiptRequestValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return ReceiptRequest.get(object)
		}
		def getList(){
			return ReceiptRequest.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.contact = Contact.find{id == object.contactId}
			object.currency = Currency.find{id == object.currencyId}
			object.accountReceivable = Receivable.find{id == object.receivableId}
			object.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			object = receiptRequestValidationService.createObjectValidation(object as ReceiptRequest)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = ReceiptRequest.read(object.id)
			valObject.contact = Contact.find{id == object.contactId}
			valObject.description = object.description
			valObject.code = object.code
			valObject.currency = Currency.find{id == object.currencyId}
			valObject.accountReceivable = Receivable.find{id == object.receivableId}
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
			valObject = receiptRequestValidationService.updateObjectValidation(valObject)
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
			def newObject = ReceiptRequest.get(object.id)
			newObject = receiptRequestValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = ReceiptRequest.get(object.id)
			newObject = receiptRequestValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = ReceiptRequest.get(object.id)
			newObject = receiptRequestValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
