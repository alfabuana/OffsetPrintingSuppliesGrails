package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class CoreIdentificationService {
CoreIdentificationValidationService coreIdentificationValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return CoreIdentification.get(object)
	}
	def getList(){
		return CoreIdentification.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isInHouse = false
		object.isConfirmed = false
		object.isCompleted = false
		object.isDeleted = false
		object.warehouse = Warehouse.find{id == object.warehouseId}
		object.contact = Contact.find{id == object.contactId}
		object = coreIdentificationValidationService.createObjectValidation(object as CoreIdentification)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = CoreIdentification.read(object.id)
		valObject.code = object.code
		valObject.warehouse = Warehouse.find{id == object.warehouseId}
		valObject.contact = Contact.find{id == object.contactId}
		if (NumberUtils.isNumber(object.quantity) ==  true)
		{
			valObject.quantity  = Double.parseDouble(object.quantity)
		}
		else
		{
			valObject.quantity  = null
		}
		valObject.identifiedDate = object.identifiedDate
		valObject.confirmationDate = object.confirmationDate
		valObject.nomorDisassembly = object.nomorDisassembly
		valObject.incomingRoll = object.incomingRoll
		valObject = coreIdentificationValidationService.updateObjectValidation(valObject)
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
		def newObject = CoreIdentification.get(object.id)
		newObject = coreIdentificationValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
	def confirmObject(def object){
		def newObject = CoreIdentification.get(object.id)
		newObject = coreIdentificationValidationService.confirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = true
			newObject.confirmationDate = new Date()
			newObject.save()
		}
		return newObject
	}
	def unConfirmObject(def object){
		def newObject = CoreIdentification.get(object.id)
		newObject = coreIdentificationValidationService.unConfirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = false
			newObject.confirmationDate = null
			newObject.save()
		}
		return newObject
	}

}
