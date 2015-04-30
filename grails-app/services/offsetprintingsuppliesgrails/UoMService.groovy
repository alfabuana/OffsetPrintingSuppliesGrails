package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class UoMService {
	UoMValidationService uomValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return UoM.get(object)
	}
	def getList(){
		return UoM.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object = uomValidationService.createObjectValidation(object as UoM)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = UoM.read(object.id)
		valObject.name = object.name
		valObject = uomValidationService.updateObjectValidation(valObject)
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
		def newObject = UoM.get(object.id)
		newObject = uomValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}

}
