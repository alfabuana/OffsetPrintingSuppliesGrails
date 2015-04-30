package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class BlanketWarehouseMutationDetailService {
BlanketWarehouseMutationDetailValidationService blanketWarehouseMutationDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return BlanketWarehouseMutationDetail.get(object)
		}
		def getList(){
			return BlanketWarehouseMutationDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			BlanketWarehouseMutation blanketWarehouseMutation = BlanketWarehouseMutation.get(object.blanketWarehouseMutationId)
			object.blanketWarehouseMutation = blanketWarehouseMutation
			object.isDeleted = false
			object.isConfirmed = false
			object.blanketOrderDetail = BlanketOrderDetail.find{id == object.blanketOrderDetailId}
			object.item = Item.find{id == object.itemId}
			object = blanketWarehouseMutationDetailValidationService.createObjectValidation(object as BlanketWarehouseMutationDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
				blanketWarehouseMutation.addToBlanketWarehouseMutationDetails(object)
			}
			return object
		}
		def updateObject(def object){
			def valObject = BlanketWarehouseMutationDetail.read(object.id)
			valObject.blanketWarehouseMutation = BlanketWarehouseMutation.find{id == object.blanketWarehouseMutationId}
			valObject.blanketOrderDetail = BlanketOrderDetail.find{id == object.blanketOrderDetailId}
			valObject.code = object.code
			valObject.item = Item.find{id == object.itemId}
			valObject = blanketWarehouseMutationDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = BlanketWarehouseMutationDetail.get(object.id)
			newObject = blanketWarehouseMutationDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = BlanketWarehouseMutationDetail.get(object.id)
			newObject = blanketWarehouseMutationDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = BlanketWarehouseMutationDetail.get(object.id)
			newObject = blanketWarehouseMutationDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
