package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class PurchaseReceivalDetailDetailService {
	PurchaseReceivalDetailValidationService purchaseReceivalDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return PurchaseReceivalDetail.get(object)
		}
		def getList(){
			return PurchaseReceivalDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isAllInvoiced = false
			object.isConfirmed = false
			object.purchaseReceival = PurchaseReceival.find{id == object.purchaseReceivalId}
			object.item = Item.find{id == object.itemId}
			object = purchaseReceivalDetailValidationService.createObjectValidation(object as PurchaseReceivalDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = PurchaseReceivalDetail.read(object.id)
			valObject.code = object.code
			valObject.purchaseReceival = PurchaseReceival.find{id == object.purchaseReceivalId}
			valObject.item = Item.find{id == object.itemId}
			if (NumberUtils.isNumber(object.quantity) ==  true)
			{
				valObject.quantity = Double.parseDouble(object.quantity)
			}
			else
			{
				valObject.quantity = null
			}
			if (NumberUtils.isNumber(object.pendingInvoicedQuantity) ==  true)
			{
				valObject.pendingInvoicedQuantity = Double.parseDouble(object.pendingInvoicedQuantity)
			}
			else
			{
				valObject.pendingInvoicedQuantity = null
			}
			if (NumberUtils.isNumber(object.purchaseOrderDetail) ==  true)
			{
				valObject.purchaseOrderDetail = Double.parseDouble(object.purchaseOrderDetail)
			}
			else
			{
				valObject.purchaseOrderDetail = null
			}
			if (NumberUtils.isNumber(object.cogs) ==  true)
			{
				valObject.cogs = Double.parseDouble(object.cogs)
			}
			else
			{
				valObject.cogs = null
			}
			valObject = purchaseReceivalDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = PurchaseReceivalDetail.get(object.id)
			newObject = purchaseReceivalDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = traue
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = PurchaseReceivalDetail.get(object.id)
			newObject = purchaseReceivalDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = PurchaseReceivalDetail.get(object.id)
			newObject = purchaseReceivalDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
