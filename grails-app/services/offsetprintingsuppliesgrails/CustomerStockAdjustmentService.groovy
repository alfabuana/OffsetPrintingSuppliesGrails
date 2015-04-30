package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class CustomerStockAdjustmentService {
	CustomerStockAdjustmentValidationService customerStockAdjustmentValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return CustomerStockAdjustment.get(object)
		}
		def getList(){
			return CustomerStockAdjustment.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isConfirmed = false
			object.warehouse = Warehouse.find{id == object.warehouseId}
			object.contact = Contact.find{id == object.contactId}
			object = customerStockAdjustmentValidationService.createObjectValidation(object as CustomerStockAdjustment)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = CustomerStockAdjustment.read(object.id)
			valObject.warehouse = Warehouse.find{id == object.warehouseId}
			valObject.contact = Contact.find{id == object.contactId}
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
			valObject = customerStockAdjustmentValidationService.updateObjectValidation(valObject)
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
			def newObject = CustomerStockAdjustment.get(object.id)
			newObject = customerStockAdjustmentValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = CustomerStockAdjustment.get(object.id)
			newObject = customerStockAdjustmentValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = CustomerStockAdjustment.get(object.id)
			newObject = customerStockAdjustmentValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
