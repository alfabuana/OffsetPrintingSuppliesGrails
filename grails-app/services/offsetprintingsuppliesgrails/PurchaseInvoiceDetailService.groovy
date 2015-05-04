package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class PurchaseInvoiceDetailDetailService {
	PurchaseInvoiceDetailValidationService purchaseInvoiceDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return PurchaseInvoiceDetail.get(object)
		}
		def getList(){
			return PurchaseInvoiceDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isConfirmed = false
			object.purchaseInvoice = PurchaseInvoice.find{id == object.purchaseInvoiceId}
			object.purchaseReceivalDetail = PurchaseReceivalDetail.find{id == object.purchaseReceivalDetailId}
			object = purchaseInvoiceDetailValidationService.createObjectValidation(object as PurchaseInvoiceDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = PurchaseInvoiceDetail.read(object.id)
			valObject.purchaseInvoice = PurchaseInvoice.find{id == object.purchaseInvoiceId}
			valObject.purchaseReceivalDetail = PurchaseReceivalDetail.find{id == object.purchaseReceivalDetailId}
			valObject.code = object.code
			if (NumberUtils.isNumber(object.quantity) ==  true)
			{
				valObject.quantity = Double.parseDouble(object.quantity)
			}
			else
			{
				valObject.quantity = null
			}
			if (NumberUtils.isNumber(object.amount) ==  true)
			{
				valObject.amount = Double.parseDouble(object.amount)
			}
			else
			{
				valObject.amount = null
			}
			valObject = purchaseInvoiceDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = PurchaseInvoiceDetail.get(object.id)
			newObject = purchaseInvoiceDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = PurchaseInvoiceDetail.get(object.id)
			newObject = purchaseInvoiceDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = PurchaseInvoiceDetail.get(object.id)
			newObject = purchaseInvoiceDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
