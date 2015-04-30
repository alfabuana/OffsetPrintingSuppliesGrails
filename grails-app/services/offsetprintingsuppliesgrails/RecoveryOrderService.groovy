package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class RecoveryOrderService {
	RecoveryOrderValidationService recoveryOrderValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return RecoveryOrder.get(object)
		}
		def getList(){
			return RecoveryOrder.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isConfirmed = false
			object.hasDueDate = false
			object.isCompleted = false
			object.coreIdentification = CoreIdentification.find{id == object.coreIdentificationId}
			object.warehouse = Warehouse.find{id == object.warehouseId}
			object = recoveryOrderValidationService.createObjectValidation(object as RecoveryOrder)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = RecoveryOrder.read(object.id)
			valObject.coreIdentification = CoreIdentification.find{id == object.coreIdentificationId}
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
			valObject = recoveryOrderValidationService.updateObjectValidation(valObject)
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
			def newObject = RecoveryOrder.get(object.id)
			newObject = recoveryOrderValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = RecoveryOrder.get(object.id)
			newObject = recoveryOrderValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = RecoveryOrder.get(object.id)
			newObject = recoveryOrderValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
