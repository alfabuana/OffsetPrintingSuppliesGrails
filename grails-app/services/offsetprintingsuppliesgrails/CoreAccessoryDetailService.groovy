package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class CoreAccessoryDetailService {
	CoreAccessoryDetailValidationService coreAccessoryDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return CoreAccessoryDetail.get(object)
		}
		def getList(){
			return CoreAccessoryDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.coreIdentificationDetail = CoreIdentificationDetail.find{id == object.coreIdentificationDetailId}
			object.item = Item.find{id == object.itemId}
			object = itemValidationService.createObjectValidation(object as Item)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = CoreAccessoryDetail.read(object.id)
			valObject.coreIdentificationDetail = CoreIdentificationDetail.find{id == object.coreIdentificationDetailId}
			valObject.item = Item.find{id == object.itemId}
			if (NumberUtils.isNumber(object.targetQuantity) ==  true)
			{
				valObject.quantity = Double.parseDouble(object.quantity)
			}
			else
			{
				valObject.quantity = null
			}
			valObject = coreAccessoryDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = CoreAccessoryDetail.get(object.id)
			newObject = coreAccessoryDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
}
