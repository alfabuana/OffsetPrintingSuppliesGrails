package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class RecoveryOrderDetailService {
	RecoveryOrderDetailValidationService recoveryOrderDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return RecoveryOrderDetail.get(object)
		}
		def getList(){
			return RecoveryOrderDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			RecoveryOrder recoveryOrder = RecoveryOrder.get(object.recoveryOrderId)
			object.recoveryOrder = recoveryOrder
			object.isDisassembled = false
			object.isStrippedAndGlued = false
			object.isWrapped = false
			object.isVulcanized = false
			object.isFacedOff = false
			object.isConventionalGrinded = false
			object.isCNCGrinded = false
			object.isPolishedAndQC = false
			object.isPackaged = false
			object.isRejected = false
			object.isFinished false
			object.isDeleted = false
			object.coreIdentificationDetail = CoreIdentificationDetail.find{id == object.coreIdentificationDetailId}
			object.rollerBuilder = RollerBuilder.find{id == object.rollerBuilderId}
			object.compoundUnderLayer = Item.find{id == object.compoundUnderLayerId}
			object = recoveryOrderDetailValidationService.createObjectValidation(object as RecoveryOrderDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
				recoveryOrder.addToRecoveryOrderDetails(object)
			}
			return object
		}
		def updateObject(def object){
			def valObject = RecoveryOrderDetail.read(object.id)
			valObject.coreIdentificationDetail = CoreIdentificationDetail.find{id == object.coreIdentificationDetailId}
			valObject.rollerBuilder = RollerBuilder.find{id == object.rollerBuilderId}
			if (NumberUtils.isNumber(object.totalCost) ==  true)
			{
				valObject.totalCost = Double.parseDouble(object.totalCost)
			}
			else
			{
				valObject.totalCost = null
			}
			if (NumberUtils.isNumber(object.compoundUsage) ==  true)
			{
				valObject.compoundUsage = Double.parseDouble(object.compoundUsage)
			}
			else
			{
				valObject.compoundUsage = null
			}
			valObject.coreTypeCase = object.coreTypeCase
			if (NumberUtils.isNumber(object.accessoriesCost) ==  true)
			{
				valObject.accessoriesCost = Double.parseDouble(object.accessoriesCost)
			}
			else
			{
				valObject.accessoriesCost = null
			}
			if (NumberUtils.isNumber(object.coreCost) ==  true)
			{
				valObject.coreCost = Double.parseDouble(object.coreCost)
			}
			else
			{
				valObject.coreCost = null
			}
			if (NumberUtils.isNumber(object.compoundCost) ==  true)
			{
				valObject.compoundCost = Double.parseDouble(object.compoundCost)
			}
			else
			{
				valObject.compoundCost = null
			}
			valObject.compoundUnderLayer = Item.find{id == object.compoundUnderLayerId}
			if (NumberUtils.isNumber(object.compoundUnderLayerUsage) ==  true)
			{
				valObject.compoundUnderLayerUsage = Double.parseDouble(object.compoundUnderLayerUsage)
			}
			else
			{
				valObject.compoundUnderLayerUsage = null
			}
			valObject = recoveryOrderDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = RecoveryOrderDetail.get(object.id)
			newObject = recoveryOrderDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def finishObject(def object){
			def newObject = RecoveryOrderDetail.get(object.id)
			newObject = recoveryOrderDetailValidationService.finishObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isFinished = true
				newObject.FinishedDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unFinishObject(def object){
			def newObject = RecoveryOrderDetail.get(object.id)
			newObject = recoveryOrderDetailValidationService.unFinishObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isFinished = false
				newObject.FinishedDate = null
				newObject.save()
			}
			return newObject
		}
		def rejectObject(def object){
			def newObject = RecoveryOrderDetail.get(object.id)
			newObject = recoveryOrderDetailValidationService.rejectObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isRejected = true
				newObject.RejectedDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unRejectObject(def object){
			def newObject = RecoveryOrderDetail.get(object.id)
			newObject = recoveryOrderDetailValidationService.unRejectObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isRejected = false
				newObject.RejectedDate = null
				newObject.save()
			}
			return newObject
		}
	
}
