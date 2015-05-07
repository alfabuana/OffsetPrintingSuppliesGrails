package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class VirtualOrderDetailService {
	VirtualOrderDetailValidationService virtualOrderDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return VirtualOrderDetail.get(object)
		}
		def getList(){
			return VirtualOrderDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isAllDelivered = false
			object.isConfirmed = false
			object.virtualOrder = VirtualOrder.find{id == object.virtualOrderId}
			object.item = Item.find{id == object.itemId}
			object = virtualOrderDetailValidationService.createObjectValidation(object as VirtualOrderDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = VirtualOrderDetail.read(object.id)
			valObject.code = object.code
			valObject.virtualOrder = VirtualOrder.find{id == object.virtualOrderId}
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
				valObject.price = Integer.parseInt(object.price)
			}
			else
			{
				valObject.price = null
			}
			valObject = virtualOrderDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = VirtualOrderDetail.get(object.id)
			newObject = virtualOrderDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = VirtualOrderDetail.get(object.id)
			newObject = virtualOrderDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = VirtualOrderDetail.get(object.id)
			newObject = virtualOrderDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
