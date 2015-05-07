package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class VirtualOrderService {
	VirtualOrderValidationService virtualOrderValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return VirtualOrder.get(object)
		}
		def getList(){
			return VirtualOrder.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isDeliveryCompleted = false
			object.isConfirmed = false
			object.contact = Contact.find{id == object.contactId}
			object.currency = Currency.find{id == object.currencyId}
			object = virtualOrderValidationService.createObjectValidation(object as VirtualOrder)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = VirtualOrder.read(object.id)
			valObject.code = object.code
			valObject.contact = Contact.find{id == object.contactId}
			if (NumberUtils.isNumber(object.orderType) ==  true)
			{
				valObject.orderType = Double.parseDouble(object.orderType)
			}
			else
			{
				valObject.orderType = null
			}
			valObject.orderDate = object.orderDate
			valObject.nomorSurat = object.nomorSurat
			valObject.currency = Currency.find{id == object.currencyId}
			valObject = virtualOrderValidationService.updateObjectValidation(valObject)
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
			def newObject = VirtualOrder.get(object.id)
			newObject = virtualOrderValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = VirtualOrder.get(object.id)
			newObject = virtualOrderValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = VirtualOrder.get(object.id)
			newObject = virtualOrderValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
