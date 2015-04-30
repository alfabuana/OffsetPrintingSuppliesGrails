package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class BlanketOrderService {

	BlanketOrderValidationService blanketOrderValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return BlanketOrder.get(object)
	}
	def getList(){
		return BlanketOrder.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object.isConfirmed = false
		object.hasDueDate = false
		object.isCompleted = false
		object.contact = Contact.find{id == object.contactId}
		object.warehouse = Warehouse.find{id == object.warehouseId}
		object = blanketOrderValidationService.createObjectValidation(object as BlanketOrder)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = BlanketOrder.read(object.id)
		valObject.contact = Contact.find{id == object.contactId}
		valObject.warehouse = Warehouse.find{id == object.warehouseId}
		valObject.code = object.code
		if (NumberUtils.isNumber(object.quantityReceived) ==  true)
		{
			valObject.quantityReceived = Double.parseDouble(object.quantityReceived)
		}
		else
		{
			valObject.quantityReceived = null
		}
		if (NumberUtils.isNumber(object.quantityRejected) ==  true)
		{
			valObject.quantityRejected = Double.parseDouble(object.quantityRejected)
		}
		else
		{
			valObject.quantityRejected = null
		}
		if (NumberUtils.isNumber(object.quantityFinal) ==  true)
		{
			valObject.quantityFinal = Double.parseDouble(object.quantityFinal)
		}
		else
		{
			valObject.quantityFinal = null
		}
		valObject.productionNo = object.productionNo
		valObject.orderDate = object.orderDate
		valObject.notes = object.notes
		valObject = blanketOrderValidationService.updateObjectValidation(valObject)
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
		def newObject = BlanketOrder.get(object.id)
		newObject = blanketOrderValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
	def confirmObject(def object){
		def newObject = BlanketOrder.get(object.id)
		newObject = blanketOrderValidationService.confirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = true
			newObject.confirmationDate = new Date()
			newObject.save()
		}
		return newObject
	}
	def unConfirmObject(def object){
		def newObject = BlanketOrder.get(object.id)
		newObject = blanketOrderValidationService.unConfirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = false
			newObject.confirmationDate = null
			newObject.save()
		}
		return newObject
	}
}
