package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class BlanketWarehouseMutationService {

	BlanketWarehouseMutationValidationService blanketWarehouseMutationValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return BlanketWarehouseMutation.get(object)
		}
		def getList(){
			return BlanketWarehouseMutation.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isConfirmed = false
			object.blanketOrder = BlanketOrder.find{id == object.blanketOrderId}
			object.warehouseFrom = Warehouse.find{id == object.warehouseId}
			object.warehouseTold = Warehouse.find{id == object.warehouseId}
			object = blanketWarehouseMutationValidationService.createObjectValidation(object as BlanketWarehouseMutation)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = BlanketWarehouseMutation.read(object.id)
			valObject.blanketOrder = BlanketOrder.find{id == object.blanketOrderId}
			valObject.code = object.code
			valObject.warehouseFrom = Warehouse.find{id == object.warehouseId}
			valObject.warehouseTold = Warehouse.find{id == object.warehouseId}
			valObject.mutationDate = object.mutationDate
			if (NumberUtils.isNumber(object.quantity) ==  true)
			{
				valObject.quantity = Double.parseDouble(object.quantity)
			}
			else
			{
				valObject.quantity = null
			}
			valObject = blanketWarehouseMutationValidationService.updateObjectValidation(valObject)
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
			def newObject = BlanketWarehouseMutation.get(object.id)
			newObject = blanketWarehouseMutationValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = BlanketWarehouseMutation.get(object.id)
			newObject = blanketWarehouseMutationValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = BlanketWarehouseMutation.get(object.id)
			newObject = blanketWarehouseMutationValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
	
}
