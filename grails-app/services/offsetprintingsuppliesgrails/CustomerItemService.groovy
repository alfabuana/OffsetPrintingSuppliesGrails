package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class CustomerItemService {
CustomerItemValidationService customerItemValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return CustomerItem.get(object)
	}
	def getList(){
		return CustomerItem.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object.contact = Contact.find{id == object.contactId}
		object.warehouseItem = WarehouseItem.find{id == object.warehouseItemId}
		object = customerItemValidationService.createObjectValidation(object as CustomerItem)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = CustomerItem.read(object.id)
		valObject.contact = Contact.find{id == object.contactId}
		valObject.warehouseItem = WarehouseItem.find{id == object.warehouseItemId}
		if (NumberUtils.isNumber(object.quantity) ==  true)
		{
			valObject.quantity = Double.parseDouble(object.quantity)
		}
		else
		{
			valObject.quantity = null
		}
		if (NumberUtils.isNumber(object.virtual) ==  true)
		{
			valObject.virtual = Double.parseDouble(object.virtual)
		}
		else
		{
			valObject.virtual = null
		}
		valObject = customerItemValidationService.updateObjectValidation(valObject)
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
		def newObject = CustomerItem.get(object.id)
		newObject = customerItemValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
}
