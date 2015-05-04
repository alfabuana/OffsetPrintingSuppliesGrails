package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class SalesOrderDetailDetailService {
	SalesOrderDetailValidationService salesOrderDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return SalesOrderDetail.get(object)
		}
		def getList(){
			return SalesOrderDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isAlDelivered = false
			object.isService = false
			object.isConfirmed = false
			object.salesOrder = SalesOrder.find{id == object.salesOrderId}
			object.item = Item.find{id == object.itemId}
			object = salesOrderDetailValidationService.createObjectValidation(object as SalesOrderDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = SalesOrderDetail.read(object.id)
			valObject.code = object.code
			valObject.orderCode = object.orderCode
			valObject.salesOrder = SalesOrder.find{id == object.salesOrderId}
			valObject.item = Item.find{id == object.itemId}
			if (NumberUtils.isNumber(object.quantity) ==  true)
			{
				valObject.quantity = Double.parseDouble(object.quantity)
			}
			else
			{
				valObject.quantity = null
			}
			if (NumberUtils.isNumber(object.pendingDeliveryQuantity) ==  true)
			{
				valObject.pendingDeliveryQuantity = Double.parseDouble(object.pendingDeliveryQuantity)
			}
			else
			{
				valObject.pendingDeliveryQuantity = null
			}
			if (NumberUtils.isNumber(object.price) ==  true)
			{
				valObject.price = Double.parseDouble(object.price)
			}
			else
			{
				valObject.price = null
			}
			valObject = salesOrderDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = SalesOrderDetail.get(object.id)
			newObject = salesOrderDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = SalesOrderDetail.get(object.id)
			newObject = salesOrderDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = SalesOrderDetail.get(object.id)
			newObject = salesOrderDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}

}
