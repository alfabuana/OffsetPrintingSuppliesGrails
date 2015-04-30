package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class WarehouseMutationDetailService {
WarehouseMutationDetailValidationService warehouseMutationDetailValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return WarehouseMutationDetail.get(object)
	}
	def getList(){
		return WarehouseMutationDetail.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		WarehouseMutation warehouseMutation = WarehouseMutation.get(object.warehouseMutationId)
		object.warehouseMutation = warehouseMutation
		object.isDeleted = false
		object.isConfirmed = false
		object.item = Item.find{id == object.itemId}
		object = warehouseMutationDetailValidationService.createObjectValidation(object as WarehouseMutationDetail)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
			warehouseMutation.addToWarehouseMutationDetails(objects)
		}
		return object
	}
	def updateObject(def object){
		def valObject = WarehouseMutationDetail.read(object.id)
		valObject.warehouseMutation = WarehouseMutation.find{id == object.warehouseMutationId}
		valObject.code = object.code
		valObject.item = Item.find{id == object.itemId}
		if (NumberUtils.isNumber(object.quantity) ==  true)
		{
			valObject.quantity = Double.parseDouble(object.quantity)
		}
		else
		{
			valObject.quantity = null
		}
		valObject = warehouseMutationDetailValidationService.updateObjectValidation(valObject)
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
		def newObject = WarehouseMutationDetail.get(object.id)
		newObject = warehouseMutationDetailValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
	def confirmObject(def object){
		def newObject = WarehouseMutationDetail.get(object.id)
		newObject = warehouseMutationDetailValidationService.confirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = true
			newObject.confirmationDate = new Date()
			newObject.save()
		}
		return newObject
	}
	def unConfirmObject(def object){
		def newObject = WarehouseMutationDetail.get(object.id)
		newObject = warehouseMutationDetailValidationService.unConfirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = false
			newObject.confirmationDate = null
			newObject.save()
		}
		return newObject
	}
}
