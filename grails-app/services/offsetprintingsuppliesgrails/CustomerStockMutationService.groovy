package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class CustomerStockMutationService {
CustomerStockMutationValidationService customerStockMutationValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return CustomerStockMutation.get(object)
		}
		def getList(){
			return CustomerStockMutation.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.item = Item.find{id == object.itemId}
			object.contact = Contact.find{id == object.contactId}
			object.customerItem = CustomerItem.find{id == object.customerItemId}
			object.warehouseItem = WarehouseItem.find{id == object.warehouseItemId}
			object = customerStockMutationValidationService.createObjectValidation(object as CustomerStockMutation)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = CustomerStockMutation.read(object.id)
			valObject.item = Item.find{id == object.itemId}
			valObject.contact = Contact.find{id == object.contactId}
			valObject.customerItem = CustomerItem.find{id == object.customerItemId}
			valObject.warehouseItem = WarehouseItem.find{id == object.warehouseItemId}
			if (NumberUtils.isNumber(object.itemCase) ==  true)
			{
				valObject.itemCase = Integer.parseInt(object.itemCase)
			}
			else
			{
				valObject.itemCase = null
			}
			if (NumberUtils.isNumber(object.status) ==  true)
			{
				valObject.status = Integer.parseInt(object.status)
			}
			else
			{
				valObject.status = null
			}
			valObject.sourceDocumentType = object.sourceDocumentType
			valObject.sourceDocumentDetailType = object.sourceDocumentDetailType
			if (NumberUtils.isNumber(object.sourceDocumentId) ==  true)
			{
				valObject.sourceDocumentId = Integer.parseInt(object.sourceDocumentId)
			}
			else
			{
				valObject.sourceDocumentId = null
			}
			if (NumberUtils.isNumber(object.sourceDocumentDetailId) ==  true)
			{
				valObject.sourceDocumentDetailId = Integer.parseInt(object.sourceDocumentDetailId)
			}
			else
			{
				valObject.sourceDocumentDetailId = null
			}
			valObject.mutationDate = object.mutationDate
			if (NumberUtils.isNumber(object.quantity) ==  true)
			{
				valObject.quantity = Double.parseDouble(object.quantity)
			}
			else
			{
				valObject.quantity = null
			}			
			valObject = customerStockMutationValidationService.updateObjectValidation(valObject)
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
			def newObject = CustomerStockMutation.get(object.id)
			newObject = customerStockMutationValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
	}
