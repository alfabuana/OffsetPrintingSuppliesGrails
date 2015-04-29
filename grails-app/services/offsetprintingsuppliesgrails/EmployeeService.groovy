package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class EmployeeService {
	EmployeeValidationService employeeValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return Employee.get(object)
	}
	def getList(){
		return Employee.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object = employeeValidationService.createObjectValidation(object as Employee)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = Employee.read(object.id)
		valObject.name = object.name
		valObject.address = object.address
		valObject.contactNo = object.contactNo
		valObject.email = object.email
		valObject.description = object.description
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
		def newObject = Employee.get(object.id)
		newObject = employeeValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}

}
