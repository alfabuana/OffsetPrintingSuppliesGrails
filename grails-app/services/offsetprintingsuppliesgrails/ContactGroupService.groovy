package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class ContactGroupService {
	ContactGroupValidationService contactGroupValidationService

    def serviceMethod() {

    }
	def getObjectById(def object){
		return ContactGroup.get(object)
	}
	def getList(){
		return ContactGroup.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object = contactGroupValidationService.createObjectValidation(object as ContactGroup)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = ContactGroup.read(object.id)
		valObject.name = object.name
		valObject.description = object.description
		valObject = contactGroupValidationService.updateObjectValidation(valObject)
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
		def newObject = ContactGroup.get(object.id)
		newObject = contactGroupValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
}
