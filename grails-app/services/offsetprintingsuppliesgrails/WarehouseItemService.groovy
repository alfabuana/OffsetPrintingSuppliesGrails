package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class WarehouseItemService {
WarehouseItemValidationService wareHouseItemValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return WarehouseItem.get(object)
	}
	def getList(){
		return WarehouseItem.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object.warehouse = Warehouse.find{id == object.warehouseId}
		object.item = Item.find{id == object.itemId}
		object = wareHouseItemValidationService.createObjectValidation(object as WarehouseItem)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = WarehouseItem.read(object.id)
		valObject.warehouse = Warehouse.find{id == object.warehouseId}
		valObject.item = Item.find{id == object.itemId}
		if (NumberUtils.isNumber(object.quantity) ==  true)
		{
			valObject.quantity = Double.parseDouble(object.quantity)
		}
		else
		{
			valObject.quantity = null
		}
		if (NumberUtils.isNumber(object.pendingDelivery) ==  true)
		{
			valObject.pendingDelivery = Double.parseDouble(object.pendingDelivery)
		}
		else
		{
			valObject.pendingDelivery = null
		}
		if (NumberUtils.isNumber(object.pendingReceival) ==  true)
		{
			valObject.pendingReceival = Double.parseDouble(object.pendingReceival)
		}
		else
		{
			valObject.pendingReceival = null
		}
		if (NumberUtils.isNumber(object.customerQuantity) ==  true)
		{
			valObject.customerQuantity = Double.parseDouble(object.customerQuantity)
		}
		else
		{
			valObject.customerQuantity = null
		}			
		valObject = wareHouseItemValidationService.updateObjectValidation(valObject)
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
		def newObject = WarehouseItem.get(object.id)
		newObject = wareHouseItemValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
}
