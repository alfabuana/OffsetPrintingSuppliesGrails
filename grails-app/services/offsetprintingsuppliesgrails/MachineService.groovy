package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class MachineService {
	MachineValidationService machineValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return Machine.get(object)
	}
	def getList(){
		return Machine.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object = machineValidationService.createObjectValidation(object as Machine)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = Machine.read(object.id)
		valObject.code = object.code
		valObject.name = object.name
		valObject.description = object.description
		valObject = machineValidationService.updateObjectValidation(valObject)
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
		def newObject = Machine.get(object.id)
		newObject = machineValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}

}
