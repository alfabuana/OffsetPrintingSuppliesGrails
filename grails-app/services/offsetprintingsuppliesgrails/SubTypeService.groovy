package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class SubTypeService {
	SubTypeValidationService subTypeValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return SubType.get(object)
	}
	def getList(){
		return SubType.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object.itemType = ItemType.find{id == object.itemTypeId}
		object = subTypeValidationService.createObjectValidation(object as SubType)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = SubType.read(object.id)
		valObject.name = object.name
		valObject.itemType = ItemType.find{id == object.itemTypeId}
		valObject = subTypeValidationService.updateObjectValidation(valObject)
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
		def newObject = SubType.get(object.id)
		newObject = subTypeValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
}
