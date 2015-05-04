package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class PurchaseOrderDetailService {
	PurchaseOrderDetailValidationService purchaseOrderDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return PurchaseOrderDetail.get(object)
		}
		def getList(){
			return PurchaseOrderDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isAllreceived = false
			object.purchaseOrder = PurchaseOrder.find{id == object.purchaseOrderId}
			object.item = Item.find{id == object.itemId}
			object = purchaseOrderDetailValidationService.createObjectValidation(object as PurchaseOrderDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = PurchaseOrderDetail.read(object.id)
			valObject.code = object.code
			valObject.purchaseOrder = PurchaseOrder.find{id == object.purchaseOrderId}
			valObject.item = Item.find{id == object.itemId}
			if (NumberUtils.isNumber(object.quantity) ==  true)
			{
				valObject.quantity = Double.parseDouble(object.quantity)
			}
			else
			{
				valObject.quantity = null
			}
			if (NumberUtils.isNumber(object.pendingReceivalQuantity) ==  true)
			{
				valObject.pendingReceivalQuantity = Double.parseDouble(object.pendingReceivalQuantity)
			}
			else
			{
				valObject.pendingReceivalQuantity = null
			}
			if (NumberUtils.isNumber(object.price) ==  true)
			{
				valObject.price = Double.parseDouble(object.price)
			}
			else
			{
				valObject.price = null
			}
			valObject = purchaseOrderDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = PurchaseOrderDetail.get(object.id)
			newObject = purchaseOrderDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = PurchaseOrderDetail.get(object.id)
			newObject = purchaseOrderDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = PurchaseOrderDetail.get(object.id)
			newObject = purchaseOrderDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
	
}
