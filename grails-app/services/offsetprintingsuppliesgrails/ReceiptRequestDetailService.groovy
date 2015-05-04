package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class ReceiptRequestDetailService {
	ReceiptRequestDetailValidationService receiptRequestDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return ReceiptRequestDetail.get(object)
		}
		def getList(){
			return ReceiptRequestDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isLegacy = false
			object.receiptRequest = ReceiptRequest.find{id == object.receiptRequestId}
			object.account = Account.find{id == object.accountId}
			object = receiptRequestDetailValidationService.createObjectValidation(object as ReceiptRequestDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = ReceiptRequestDetail.read(object.id)
			valObject.receiptRequest = ReceiptRequest.find{id == object.receiptRequestId}
			valObject.account = Account.find{id == object.accountId}
			valObject.code = object.code
			if (NumberUtils.isNumber(object.status) ==  true)
			{
				valObject.status = Integer.parseInt(object.status)
			}
			else
			{
				valObject.status = null
			}
			if (NumberUtils.isNumber(object.amount) ==  true)
			{
				valObject.amount = Double.parseDouble(object.amount)
			}
			else
			{
				valObject.amount = null
			}
			valObject = receiptRequestDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = ReceiptRequestDetail.get(object.id)
			newObject = receiptRequestDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = ReceiptRequestDetail.get(object.id)
			newObject = receiptRequestDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = ReceiptRequestDetail.get(object.id)
			newObject = receiptRequestDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}

}
