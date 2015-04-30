package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class BlendingRecipeDetailService {

	BlendingRecipeDetailValidationService blendingRecipeDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return BlendingRecipeDetail.get(object)
		}
		def getList(){
			return BlendingRecipeDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			BlendingRecipe blendingRecipe = BlendingRecipe.get(object.blendingRecipeId)
			object.blendingRecipe = blendingRecipe
			object.isDeleted = false
			object.item = Item.find{id == object.itemId}
			object = blendingRecipeDetailValidationService.createObjectValidation(object as BlendingRecipeDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
				blendingRecipe.addToBlendingRecipeDetails(object)
			}
			return object
		}
		def updateObject(def object){
			def valObject = BlendingRecipeDetail.read(object.id)
			valObject.item = Item.find{id == object.itemId}
			if (NumberUtils.isNumber(object.quantity) ==  true)
			{
				valObject.quantity = Double.parseDouble(object.quantity)
			}
			else
			{
				valObject.quantity = null
			}
			valObject = blendingRecipeDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = BlendingRecipeDetail.get(object.id)
			newObject = blendingRecipeDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
	
}
