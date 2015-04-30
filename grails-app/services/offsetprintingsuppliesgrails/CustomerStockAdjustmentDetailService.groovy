package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class CustomerStockAdjustmentDetailService {
CustomerStockAdjustmentDetailValidationService customerStockAdjustmentDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return CustomerStockAdjustmentDetail.get(object)
		}
		def getList(){
			return CustomerStockAdjustmentDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			CustomerStockAdjustment customerStockAdjustment = CustomerStockAdjustment.get(object.customerStockAdjustmentId)
			object.customerStockAdjustment = customerStockAdjustment
			object.isDeleted = false
			object.isConfirmed = false
			object.item = Item.find{id == object.itemId}
			object = customerStockAdjustmentDetailValidationService.createObjectValidation(object as CustomerStockAdjustmentDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
				customerStockAdjustment.addToCustomerStockAdjustmentDetails(object)
			}
			return object
		}
		def updateObject(def object){
			def valObject = CustomerStockAdjustmentDetail.read(object.id)
			valObject.customerStockAdjustment = CustomerStockAdjustment.find{id == object.customerStockAdjustmentId}
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
			if (NumberUtils.isNumber(object.priceMutation) ==  true)
			{
				valObject.priceMutation = Double.parseDouble(object.priceMutation)
			}
			else
			{
				valObject.priceMutation = null
			}
			valObject = customerStockAdjustmentDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = CustomerStockAdjustmentDetail.get(object.id)
			newObject = customerStockAdjustmentDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = CustomerStockAdjustmentDetail.get(object.id)
			newObject = customerStockAdjustmentDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = CustomerStockAdjustmentDetail.get(object.id)
			newObject = customerStockAdjustmentDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
	}
