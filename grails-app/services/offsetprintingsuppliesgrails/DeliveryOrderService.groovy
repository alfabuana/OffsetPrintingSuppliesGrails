package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class DeliveryOrderService {
	DeliveryOrderValidationService deliveryOrderValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return DeliveryOrder.get(object)
		}
		def getList(){
			return DeliveryOrder.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isInvoiceCompleted = false
			object.isConfirmed = false
			object.salesOrder = SalesOrder.find{id == object.salesOrderId}
			object.warehouse = Warehouse.find{id == object.currencyId}
			object.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			object = deliveryOrderValidationService.createObjectValidation(object as DeliveryOrder)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = DeliveryOrder.read(object.id)
			valObject.code = object.code
			valObject.salesOrder = SalesOrder.find{id == object.salesOrderId}
			valObject.deliveryDate = object.deliveryDate
			valObject.warehouse = Warehouse.find{id == object.currencyId}
			valObject.nomorSurat = object.nomorSurat
			if (NumberUtils.isNumber(object.totalCOGC) ==  true)
			{
				valObject.totalCOGC = Double.parseDouble(object.totalCOGC)
			}
			else
			{
				valObject.totalCOGC = null
			}
			if (NumberUtils.isNumber(object.exchangeRateAmount) ==  true)
			{
				valObject.exchangeRateAmount = Double.parseDouble(object.exchangeRateAmount)
			}
			else
			{
				valObject.exchangeRateAmount = null
			}
			valObject.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			valObject.remark = object.remark
			valObject = deliveryOrderValidationService.updateObjectValidation(valObject)
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
			def newObject = DeliveryOrder.get(object.id)
			newObject = deliveryOrderValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = DeliveryOrder.get(object.id)
			newObject = deliveryOrderValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = DeliveryOrder.get(object.id)
			newObject = deliveryOrderValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
