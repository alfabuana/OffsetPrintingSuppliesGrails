package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class RepackingService {
RepackingValidationService repackingValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return Repacking.get(object)
	}
	def getList(){
		return Repacking.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object.isConfirmed = false
		object.blendingRecipe = BlendingRecipe.find{id == object.blendingRecipeId}
		object.warehouse = Warehouse.find{id == object.warehouseId}
		object = repackingValidationService.createObjectValidation(object as Repacking)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = Repacking.read(object.id)
		valObject.code = object.code
		valObject.description = object.description
		valObject.repackingDate = object.repackingDate
		valObject.blendingRecipe = BlendingRecipe.find{id == object.blendingRecipeId}
		valObject.warehouse = Warehouse.find{id == object.warehouseId}
		valObject = repackingValidationService.updateObjectValidation(valObject)
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
		def newObject = Repacking.get(object.id)
		newObject = repackingValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
	def confirmObject(def object){
		def newObject = Repacking.get(object.id)
		newObject = repackingValidationService.confirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = true
			newObject.confirmationDate = new Date()
			newObject.save()
		}
		return newObject
	}
	def unConfirmObject(def object){
		def newObject = Repacking.get(object.id)
		newObject = repackingValidationService.unConfirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = false
			newObject.confirmationDate = null
			newObject.save()
		}
		return newObject
	}
}
