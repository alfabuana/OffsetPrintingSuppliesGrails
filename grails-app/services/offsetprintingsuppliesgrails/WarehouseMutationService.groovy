package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class WarehouseMutationService {
WarehouseMutationValidationService warehouseMutationValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return WarehouseMutation.get(object)
	}
	def getList(){
		return WarehouseMutation.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object.isConfirmed = false
		object.warehouseFrom = Warehouse.find{id == object.warehouseId}
		object.warehouseTold = Warehouse.find{id == object.warehouseId}
		object = warehouseMutationValidationService.createObjectValidation(object as WarehouseMutation)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = WarehouseMutation.read(object.id)
		valObject.code = object.code
		valObject.warehouseFrom = Warehouse.find{id == object.warehouseId}
		valObject.warehouseTold = Warehouse.find{id == object.warehouseId}
		valObject.mutationDate = object.mutationDate
		valObject = warehouseMutationValidationService.updateObjectValidation(valObject)
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
		def newObject = WarehouseMutation.get(object.id)
		newObject = warehouseMutationValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
	def confirmObject(def object){
		def newObject = WarehouseMutation.get(object.id)
		newObject = warehouseMutationValidationService.confirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = true
			newObject.confirmationDate = new Date()
			newObject.save()
		}
		return newObject
	}
	def unConfirmObject(def object){
		def newObject = WarehouseMutation.get(object.id)
		newObject = warehouseMutationValidationService.unConfirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = false
			newObject.confirmationDate = null
			newObject.save()
		}
		return newObject
	}
}
