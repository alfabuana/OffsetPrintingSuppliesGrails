package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class PurchaseOrderService {
	PurchaseOrderValidationService purchaseOrderValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return PurchaseOrder.get(object)
		}
		def getList(){
			return PurchaseOrder.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isReceivalCompleted = false
			object.contact = Contact.find{id == object.contactId}
			object.currency = Currency.find{id == object.currencyId}
			object = purchaseOrderValidationService.createObjectValidation(object as PurchaseOrder)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = PurchaseOrder.read(object.id)
			valObject.code = object.code
			valObject.contact = Contact.find{id == object.contactId}
			valObject.purchaseDate = object.purchaseDate
			valObject.nomorSurat = object.nomorSurat
			valObject.currency = Currency.find{id == object.currencyId}
			valObject.description = object.description
			valObject.allowEditDetail = object.allowEditDetail
			valObject = purchaseOrderValidationService.updateObjectValidation(valObject)
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
			def newObject = PurchaseOrder.get(object.id)
			newObject = purchaseOrderValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = PurchaseOrder.get(object.id)
			newObject = purchaseOrderValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = PurchaseOrder.get(object.id)
			newObject = purchaseOrderValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
