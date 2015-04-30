package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class BlendingWorkOrderService {

	BlendingWorkOrderValidationService blendingWorkOrderValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return BlendingWorkOrder.get(object)
		}
		def getList(){
			return BlendingWorkOrder.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isConfirmed = false
			object.blendingRecipe = BlendingRecipe.find{id == object.blendingRecipeId}
			object.warehouse = Warehouse.find{id == object.warehouseId}
			object = blendingWorkOrderValidationService.createObjectValidation(object as BlendingWorkOrder)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = BlendingWorkOrder.read(object.id)
			valObject.code = object.code
			valObject.description = object.description
			valObject.blendingDate = object.blendingDate
			valObject.blendingRecipe = BlendingRecipe.find{id == object.blendingRecipeId}
			valObject.warehouse = Warehouse.find{id == object.warehouseId}
			valObject = blendingWorkOrderValidationService.updateObjectValidation(valObject)
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
			def newObject = BlendingWorkOrder.get(object.id)
			newObject = blendingWorkOrderValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = BlendingWorkOrder.get(object.id)
			newObject = blendingWorkOrderValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = BlendingWorkOrder.get(object.id)
			newObject = blendingWorkOrderValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
	
}
