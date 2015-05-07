package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class DeliveryOrderDetailDetailService {
	DeliveryOrderDetailValidationService deliveryOrderDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return DeliveryOrderDetail.get(object)
		}
		def getList(){
			return DeliveryOrderDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isAllInvoiced = false
			object.isConfirmed = false
			object.deliveryOrder = DeliveryOrder.find{id == object.deliveryOrderId}
			object.item = Item.find{id == object.itemId}
			object.contact = Contact.find{id == object.contactId}
			object = deliveryOrderDetailValidationService.createObjectValidation(object as DeliveryOrderDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = DeliveryOrderDetail.read(object.id)
			valObject.code = object.code
			if (NumberUtils.isNumber(object.orderType) ==  true)
			{
				valObject.orderType = Integer.parseInt(object.orderType)
			}
			else
			{
				valObject.orderType = null
			}
			valObject.orderCode = object.orderCode
			valObject.deliveryOrder = DeliveryOrder.find{id == object.deliveryOrderId}
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
			if (NumberUtils.isNumber(object.salesOrderDetail) ==  true)
			{
				valObject.salesOrderDetail = Integer.parseInt(object.salesOrderDetail)
			}
			else
			{
				valObject.salesOrderDetail = null
			}
			if (NumberUtils.isNumber(object.cogs) ==  true)
			{
				valObject.cogs = Integer.parseInt(object.cogs)
			}
			else
			{
				valObject.cogs = null
			}
			valObject.contact = Contact.find{id == object.contactId}
			valObject = deliveryOrderDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = DeliveryOrderDetail.get(object.id)
			newObject = deliveryOrderDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = DeliveryOrderDetail.get(object.id)
			newObject = deliveryOrderDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = DeliveryOrderDetail.get(object.id)
			newObject = deliveryOrderDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
