package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class PurchaseDownPaymentAllocationDetailService {
	PurchaseDownPaymentAllocationDetailValidationService purchaseDownPaymentAllocationDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return PurchaseDownPaymentAllocationDetail.get(object)
		}
		def getList(){
			return PurchaseDownPaymentAllocationDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.purchaseDownPaymentAllocation = PurchaseDownPaymentAllocation.find{id == object.purchaseDownPaymentAllocationId}
			object.payable = Payable.find{id == object.payableId}
			object = purchaseDownPaymentAllocationDetailValidationService.createObjectValidation(object as PurchaseDownPaymentAllocationDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = PurchaseDownPaymentAllocationDetail.read(object.id)
			valObject.purchaseDownPaymentAllocation = PurchaseDownPaymentAllocation.find{id == object.purchaseDownPaymentAllocationId}
			valObject.payable = Payable.find{id == object.payableId}
			valObject.code = object.code
			if (NumberUtils.isNumber(object.amount) ==  true)
			{
				valObject.amount = Double.parseDouble(object.amount)
			}
			else
			{
				valObject.amount = null
			}
			if (NumberUtils.isNumber(object.amountPaid) ==  true)
			{
				valObject.amountPaid = Double.parseDouble(object.amountPaid)
			}
			else
			{
				valObject.amountPaid = null
			}
			if (NumberUtils.isNumber(object.rate) ==  true)
			{
				valObject.rate = Double.parseDouble(object.rate)
			}
			else
			{
				valObject.rate = null
			}
			valObject.description = object.description
			valObject = purchaseDownPaymentAllocationDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = PurchaseDownPaymentAllocationDetail.get(object.id)
			newObject = purchaseDownPaymentAllocationDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = PurchaseDownPaymentAllocationDetail.get(object.id)
			newObject = purchaseDownPaymentAllocationDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = PurchaseDownPaymentAllocationDetail.get(object.id)
			newObject = purchaseDownPaymentAllocationDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
