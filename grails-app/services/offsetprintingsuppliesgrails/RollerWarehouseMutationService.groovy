package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class RollerWarehouseMutationService {
RollerWarehouseMutationValidationService rollerWarehouseMutationValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return RollerWarehouseMutation.get(object)
		}
		def getList(){
			return RollerWarehouseMutation.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isConfirmed = false
			object.recoveryOrder = RecoveryOrder.find{id == object.recoveryOrderId}
			object.warehouseFrom = Warehouse.find{id == object.warehouseId}
			object.warehouseTold = Warehouse.find{id == object.warehouseId}
			object = rollerWarehouseMutationValidationService.createObjectValidation(object as RollerWarehouseMutation)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = RollerWarehouseMutation.read(object.id)
			valObject.recoveryOrder = RecoveryOrder.find{id == object.recoveryOrderId}
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
			valObject = rollerWarehouseMutationValidationService.updateObjectValidation(valObject)
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
			def newObject = RollerWarehouseMutation.get(object.id)
			newObject = rollerWarehouseMutationValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = RollerWarehouseMutation.get(object.id)
			newObject = rollerWarehouseMutationValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = RollerWarehouseMutation.get(object.id)
			newObject = rollerWarehouseMutationValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
