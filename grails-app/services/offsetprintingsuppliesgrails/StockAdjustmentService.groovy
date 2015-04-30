package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class StockAdjustmentService {
StockAdjustmentValidationService stockAdjustmentValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return StockAdjustment.get(object)
	}
	def getList(){
		return StockAdjustment.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object.isConfirmed = false
		object.warehouse = Warehouse.find{id == object.warehouseId}
		object = stockAdjustmentValidationService.createObjectValidation(object as StockAdjustment)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = StockAdjustment.read(object.id)
		valObject.warehouse = Warehouse.find{id == object.warehouseId}
		valObject.adjustmentDate = object.adjustmentDate
		valObject.description = object.description
		valObject.code = object.code
		if (NumberUtils.isNumber(object.total) ==  true)
		{
			valObject.total = Double.parseDouble(object.total)
		}
		else
		{
			valObject.total = null
		}
		valObject = stockAdjustmentValidationService.updateObjectValidation(valObject)
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
		def newObject = StockAdjustment.get(object.id)
		newObject = stockAdjustmentValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
	def confirmObject(def object){
		def newObject = StockAdjustment.get(object.id)
		newObject = stockAdjustmentValidationService.confirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = true
			newObject.confirmationDate = new Date()
			newObject.save()
		}
		return newObject
	}
	def unConfirmObject(def object){
		def newObject = StockAdjustment.get(object.id)
		newObject = stockAdjustmentValidationService.unConfirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = false
			newObject.confirmationDate = null
			newObject.save()
		}
		return newObject
	}
}
