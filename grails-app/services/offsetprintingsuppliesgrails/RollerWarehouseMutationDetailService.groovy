package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class RollerWarehouseMutationDetailService {
RollerWarehouseMutationDetailValidationService rollerWarehouseMutationDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return RollerWarehouseMutationDetail.get(object)
		}
		def getList(){
			return RollerWarehouseMutationDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			RollerWarehouseMutation rollerWarehouseMutation = RollerWarehouseMutation.get(object.rollerWarehouseMutationId)
			object.rollerWarehouseMutation = rollerWarehouseMutation
			object.isDeleted = false
			object.isConfirmed = false
			object.recoveryOrderDetail = RecoveryOrderDetail.find{id == object.recoveryOrderDetailId}
			object.item = Item.find{id == object.itemId}
			object = rollerWarehouseMutationDetailValidationService.createObjectValidation(object as RollerWarehouseMutationDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
				rollerWarehouseMutation.addToRollerWarehouseMutationDetails(object)
			}
			return object
		}
		def updateObject(def object){
			def valObject = RollerWarehouseMutationDetail.read(object.id)
			valObject.rollerWarehouseMutation = RollerWarehouseMutation.find{id == object.rollerWarehouseMutationId}
			valObject.recoveryOrderDetail = RecoveryOrderDetail.find{id == object.recoveryOrderDetailId}
			valObject.code = object.code
			valObject.item = Item.find{id == object.itemId}
			valObject = rollerWarehouseMutationDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = RollerWarehouseMutationDetail.get(object.id)
			newObject = rollerWarehouseMutationDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = RollerWarehouseMutationDetail.get(object.id)
			newObject = rollerWarehouseMutationDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = RollerWarehouseMutationDetail.get(object.id)
			newObject = rollerWarehouseMutationDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
