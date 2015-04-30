package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class RollerTypeService {
	RollerTypeValidationService rollerTypeValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return RollerType.get(object)
	}
	def getList(){
		return RollerType.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object = rollerTypeValidationService.createObjectValidation(object as RollerType)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = RollerType.read(object.id)
		valObject.name = object.name
		valObject.description = object.description
		valObject = rollerTypeValidationService.updateObjectValidation(valObject)
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
		def newObject = RollerType.get(object.id)
		newObject = rollerTypeValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}

}
