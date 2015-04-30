package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class WarehouseService {
	WarehouseValidationService warehouseValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return Warehouse.get(object)
	}
	def getList(){
		return Warehouse.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object = warehouseValidationService.createObjectValidation(object as Warehouse)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = Warehouse.read(object.id)
		valObject.code = object.code
		valObject.name = object.name
		valObject.description = object.description
		valObject = warehouseValidationService.updateObjectValidation(valObject)
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
		def newObject = Warehouse.get(object.id)
		newObject = warehouseValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}

}
