package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class TemporaryDeliveryOrderService {
	TemporaryDeliveryOrderValidationService temporaryDeliveryOrderValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return TemporaryDeliveryOrder.get(object)
		}
		def getList(){
			return TemporaryDeliveryOrder.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isDeliveryCompleted = false
			object.isConfirmed = false
			object.isReconciled = false
			object.isPushed = false
			object.virtualOrder = VirtualOrder.find{id == object.virtualOrderId}
			object.deliveryOrder = DeliveryOrder.find{id == object.deliveryOrderId}
			object.warehouse = Warehouse.find{id == object.currencyId}
			object = temporaryDeliveryOrderValidationService.createObjectValidation(object as TemporaryDeliveryOrder)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = TemporaryDeliveryOrder.read(object.id)
			valObject.code = object.code
			if (NumberUtils.isNumber(object.orderType) ==  true)
			{
				valObject.orderType = Integer.parseInt(object.orderType)
			}
			else
			{
				valObject.orderType = null
			}
			valObject.virtualOrder = VirtualOrder.find{id == object.virtualOrderId}
			valObject.deliveryOrder = DeliveryOrder.find{id == object.deliveryOrderId}
			valObject.deliveryDate = object.deliveryDate
			valObject.warehouse = Warehouse.find{id == object.currencyId}
			valObject.nomorSurat = object.nomorSurat
			if (NumberUtils.isNumber(object.totalWasteCOGS) ==  true)
			{
				valObject.totalWasteCOGS = Double.parseDouble(object.totalWasteCOGS)
			}
			else
			{
				valObject.totalWasteCOGS = null
			}
			valObject = temporaryDeliveryOrderValidationService.updateObjectValidation(valObject)
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
			def newObject = TemporaryDeliveryOrder.get(object.id)
			newObject = temporaryDeliveryOrderValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = TemporaryDeliveryOrder.get(object.id)
			newObject = temporaryDeliveryOrderValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = TemporaryDeliveryOrder.get(object.id)
			newObject = temporaryDeliveryOrderValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
