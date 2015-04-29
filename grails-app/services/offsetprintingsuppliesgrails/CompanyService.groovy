package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class CompanyService {
	CompanyValidationService companyValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return Company.get(object)
	}
	def getList(){
		return Company.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object = companyValidationService.createObjectValidation(object as Company)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = Company.read(object.id)
		valObject.name = object.name
		valObject.address = object.address
		valObject.contactNo = object.contactNo
		valObject.email = object.email
		valObject.logo = object.logo
		valObject.npwp = object.npwp
		valObject.city = object.city
		valObject = companyValidationService.updateObjectValidation(valObject)
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
		def newObject = Company.get(object.id)
		newObject = companyValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
}
