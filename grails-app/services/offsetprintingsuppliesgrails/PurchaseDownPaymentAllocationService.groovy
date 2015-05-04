package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class PurchaseDownPaymentAllocationService {
	PurchaseDownPaymentAllocationValidationService purchaseDownPaymentAllocationValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return PurchaseDownPaymentAllocation.get(object)
		}
		def getList(){
			return PurchaseDownPaymentAllocation.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.contact = Contact.find{id == object.contactId}
			object.receivable = Receivable.find{id == object.receivableId}
			object = purchaseDownPaymentAllocationValidationService.createObjectValidation(object as PurchaseDownPaymentAllocation)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = PurchaseDownPaymentAllocation.read(object.id)
			valObject.contact = Contact.find{id == object.contactId}
			valObject.receivable = Receivable.find{id == object.receivableId}
			valObject.code = object.code
			valObject.allocationDate = object.allocationDate
			if (NumberUtils.isNumber(object.totalAmount) ==  true)
			{
				valObject.totalAmount = Double.parseDouble(object.totalAmount)
			}
			else
			{
				valObject.totalAmount = null
			}
			if (NumberUtils.isNumber(object.rateToIDR) ==  true)
			{
				valObject.rateToIDR = Double.parseDouble(object.rateToIDR)
			}
			else
			{
				valObject.rateToIDR = null
			}
			valObject = purchaseDownPaymentAllocationValidationService.updateObjectValidation(valObject)
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
			def newObject = PurchaseDownPaymentAllocation.get(object.id)
			newObject = purchaseDownPaymentAllocationValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = PurchaseDownPaymentAllocation.get(object.id)
			newObject = purchaseDownPaymentAllocationValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = PurchaseDownPaymentAllocation.get(object.id)
			newObject = purchaseDownPaymentAllocationValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
