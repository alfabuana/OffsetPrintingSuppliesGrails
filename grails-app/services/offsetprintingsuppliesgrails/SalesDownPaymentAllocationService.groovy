package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class SalesDownPaymentAllocationService {
	SalesDownPaymentAllocationValidationService salesDownPaymentAllocationValidationService
	
	def serviceMethod() {

	}
	
	def getObjectById(def object){
		return SalesDownPaymentAllocation.get(object)
	}
	def getList(){
		return SalesDownPaymentAllocation.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object.contact = Contact.find{id == object.contactId}
		object.payable = Payable.find{id == object.payableId}
		object = salesDownPaymentAllocationValidationService.createObjectValidation(object as SalesDownPaymentAllocation)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = SalesDownPaymentAllocation.read(object.id)
		valObject.contact = Contact.find{id == object.contactId}
		valObject.payable = Payable.find{id == object.payableId}
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
		valObject = salesDownPaymentAllocationValidationService.updateObjectValidation(valObject)
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
		def newObject = SalesDownPaymentAllocation.get(object.id)
		newObject = salesDownPaymentAllocationValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
	
	def confirmObject(def object){
		def newObject = SalesDownPaymentAllocation.get(object.id)
		newObject = salesDownPaymentAllocationValidationService.confirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = true
			newObject.confirmationDate = new Date()
			newObject.save()
		}
		return newObject
	}
	def unConfirmObject(def object){
		def newObject = SalesDownPaymentAllocation.get(object.id)
		newObject = salesDownPaymentAllocationValidationService.unConfirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = false
			newObject.confirmationDate = null
			newObject.save()
		}
		return newObject
	}
}
