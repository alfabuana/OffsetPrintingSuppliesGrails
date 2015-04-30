package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class StockAdjustmentDetailService {
	StockAdjustmentDetailValidationService stockAdjustmentDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return StockAdjustmentDetail.get(object)
		}
		def getList(){
			return StockAdjustmentDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			StockAdjustment stockAdjustment = StockAdjustment.get(object.stockAdjustmentId)
			object.stockAdjustment = stockAdjustment
			object.isDeleted = false
			object.isConfirmed = false
			object.item = Item.find{id == object.itemId}
			object = stockAdjustmentDetailValidationService.createObjectValidation(object as StockAdjustmentDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
				stockAdjustment.addToStockAdjustmentDetails(object)
			}
			return object
		}
		def updateObject(def object){
			def valObject = StockAdjustmentDetail.read(object.id)
			valObject.stockAdjustment = StockAdjustment.find{id == object.stockAdjustmentId}
			valObject.item = Item.find{id == object.itemId}
			valObject.code = object.code
			if (NumberUtils.isNumber(object.quantity) ==  true)
			{
				valObject.quantity = Integer.parseInt(object.quantity)
			}
			else
			{
				valObject.quantity = null
			}
			if (NumberUtils.isNumber(object.price) ==  true)
			{
				valObject.price = Double.parseDouble(object.price)
			}
			else
			{
				valObject.price = null
			}
			valObject = stockAdjustmentDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = StockAdjustmentDetail.get(object.id)
			newObject = stockAdjustmentDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = StockAdjustmentDetail.get(object.id)
			newObject = stockAdjustmentDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = StockAdjustmentDetail.get(object.id)
			newObject = stockAdjustmentDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
