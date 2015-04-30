package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class BlendingRecipeService {
	BlendingRecipeValidationService blendingRecipeValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return BlendingRecipe.get(object)
	}
	def getList(){
		return BlendingRecipe.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object.targetItem = Item.find{id == object.itemId}
		object = blendingRecipeValidationService.createObjectValidation(object as BlendingRecipe)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = BlendingRecipe.read(object.id)
		valObject.name = object.name
		valObject.description = object.description
		valObject.targetItem = Item.find{id == object.itemId}
		if (NumberUtils.isNumber(object.targetQuantity) ==  true)
		{
			valObject.targetQuantity = Double.parseDouble(object.targetQuantity)
		}
		else
		{
			valObject.targetQuantity = null
		}
		valObject = blendingRecipeValidationService.updateObjectValidation(valObject)
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
		def newObject = BlendingRecipe.get(object.id)
		newObject = blendingRecipeValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
}
