package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class ItemTypeService {
	ItemTypeValidationService itemTypeValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return ItemType.get(object)
	}
	def getList(){
		return ItemType.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object.isLegacy = false
		object.account = Account.find{id == object.accountId}
		object = itemTypeValidationService.createObjectValidation(object as ItemType)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = ItemType.read(object.id)
		valObject.name = object.name
		valObject.description = object.description
		valObject.account = Account.find{id == object.accountId}
		valObject = itemTypeValidationService.updateObjectValidation(valObject)
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
		def newObject = ItemType.get(object.id)
		newObject = itemTypeValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
}
