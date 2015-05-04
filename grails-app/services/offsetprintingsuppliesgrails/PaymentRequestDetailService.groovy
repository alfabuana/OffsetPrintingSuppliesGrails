package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class PaymentRequestDetailService {
	PaymentRequestDetailValidationService paymentRequestDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return PaymentRequestDetail.get(object)
		}
		def getList(){
			return PaymentRequestDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isLegacy = false
			object.paymentRequest = PaymentRequest.find{id == object.paymentRequestId}
			object.account = Account.find{id == object.accountId}
			object = paymentRequestDetailValidationService.createObjectValidation(object as PaymentRequestDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = PaymentRequestDetail.read(object.id)
			valObject.paymentRequest = PaymentRequest.find{id == object.paymentRequestId}
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
			valObject = paymentRequestDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = PaymentRequestDetail.get(object.id)
			newObject = paymentRequestDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = PaymentRequestDetail.get(object.id)
			newObject = paymentRequestDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = PaymentRequestDetail.get(object.id)
			newObject = paymentRequestDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
