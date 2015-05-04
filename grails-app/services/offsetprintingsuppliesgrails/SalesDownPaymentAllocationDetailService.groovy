package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class SalesDownPaymentAllocationDetailService {
	SalesDownPaymentAllocationDetailValidationService salesDownPaymentAllocationDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return SalesDownPaymentAllocationDetail.get(object)
		}
		def getList(){
			return SalesDownPaymentAllocationDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.salesDownPaymentAllocation = SalesDownPaymentAllocation.find{id == object.salesDownPaymentAllocationId}
			object.receivable = Receivable.find{id == object.receivableId}
			object = salesDownPaymentAllocationDetailValidationService.createObjectValidation(object as SalesDownPaymentAllocationDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = SalesDownPaymentAllocationDetail.read(object.id)
			valObject.salesDownPaymentAllocation = SalesDownPaymentAllocation.find{id == object.salesDownPaymentAllocationId}
			valObject.receivable = Receivable.find{id == object.receivableId}
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
			valObject = salesDownPaymentAllocationDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = SalesDownPaymentAllocationDetail.get(object.id)
			newObject = salesDownPaymentAllocationDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = SalesDownPaymentAllocationDetail.get(object.id)
			newObject = salesDownPaymentAllocationDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = SalesDownPaymentAllocationDetail.get(object.id)
			newObject = salesDownPaymentAllocationDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
