package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class ContactDetailService {
	ContactDetailValidationService contactDetailValidationService
	ContactService	contactService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return ContactDetail.get(object)
	}
	def getList(){
		return ContactDetail.getAll()
	}
	
	
	def createObject(object){
		Contact contact = Contact.get(object.contactId)
		object.contact = contact
		object.isDeleted = false
		object.isConfirmed = false
		object = contactDetailValidationService.createObjectValidation(object as ContactDetail)
		
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
			contact.addToContactDetails(object)
		}
		return object
	}
	def updateObject(def object){
		def valObject = ContactDetail.read(object.id)
		valObject.name = object.name
		valObject.contactNo = object.contactNo
		valObject.email = object.email
		valObject.address = object.address
		valObject.description = object.description
		valObject = contactDetailValidationService.updateObjectValidation(valObject)
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
		def newObject = ContactDetail.get(object.id)
		newObject = contactDetailValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject

	}
}
