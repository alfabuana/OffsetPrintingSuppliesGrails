package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class PurchaseReceivalService {
	PurchaseReceivalValidationService purchaseReceivalValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return PurchaseReceival.get(object)
		}
		def getList(){
			return PurchaseReceival.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isAllreceived = false
			object.purchaseOrder = PurchaseOrder.find{id == object.purchaseOrderId}
			object.warehouse = Warehouse.find{id == object.warehouseId}
			object.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			object = purchaseReceivalValidationService.createObjectValidation(object as PurchaseReceival)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = PurchaseReceival.read(object.id)
			valObject.code = object.code
			valObject.purchaseOrder = PurchaseOrder.find{id == object.purchaseOrderId}
			valObject.receivalDate = object.receivalDate
			valObject.warehouse = Warehouse.find{id == object.warehouseId}
			valObject.nomorSurat = object.nomorSurat
			if (NumberUtils.isNumber(object.exchangeRateAmount) ==  true)
			{
				valObject.exchangeRateAmount = Double.parseDouble(object.exchangeRateAmount)
			}
			else
			{
				valObject.exchangeRateAmount = null
			}
			valObject.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			if (NumberUtils.isNumber(object.totalCOGS) ==  true)
			{
				valObject.totalCOGS = Double.parseDouble(object.totalCOGS)
			}
			else
			{
				valObject.totalCOGS = null
			}
			if (NumberUtils.isNumber(object.totalAmount) ==  true)
			{
				valObject.totalAmount = Double.parseDouble(object.totalAmount)
			}
			else
			{
				valObject.totalAmount = null
			}
			valObject.description = object.description
			valObject = purchaseReceivalValidationService.updateObjectValidation(valObject)
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
			def newObject = PurchaseReceival.get(object.id)
			newObject = purchaseReceivalValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = traue
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = PurchaseReceival.get(object.id)
			newObject = purchaseReceivalValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = PurchaseReceival.get(object.id)
			newObject = purchaseReceivalValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
