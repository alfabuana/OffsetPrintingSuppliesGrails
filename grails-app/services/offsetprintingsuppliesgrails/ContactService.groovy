package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class ContactService {
	ContactValidationService contactValidationService

    def serviceMethod() {

    }
	def getObjectById(def object){
		return Contact.get(object)
	}
	def getList(){
		return Contact.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object.isTaxable = false
		object.contactGroup = ContactGroup.find{id == object.contactGroupId}
		object = contactValidationService.createObjectValidation(object as Contact)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = Contact.read(object.id)
		valObject.name = object.name
		valObject.address = object.address
		valObject.deliveryAddress = object.deliveryAddress
		valObject.npwp = object.npwp
		valObject.description = object.description
		valObject.contactNo = object.contactNo
		valObject.pic = object.pic
		valObject.picContactNo = object.picContactNo
		valObject.email = object.email
		valObject.taxCode = object.taxCode
		valObject.contactType = object.contactType
		valObject.defaultPaymentTerm = object.defaultPaymentTerm
		valObject.namaFakturPajak = object.namaFakturPajak
		valObject.contactGroup = ContactGroup.find{id == object.contactGroupId}
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
		def newObject = Contact.get(object.id)
		newObject = contactValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
}
