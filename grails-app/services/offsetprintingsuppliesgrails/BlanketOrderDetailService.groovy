package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class BlanketOrderDetailService {
BlanketOrderDetailValidationService blanketOrderDetailValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return BlanketOrderDetail.get(object)
	}
	def getList(){
		return BlanketOrderDetail.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		BlanketOrder blanketOrder = BlanketOrder.get(object.blanketOrderId)
		object.blanketOrder = blanketOrder
		object.isCut = false
		object.isSideSealed = false
		object.isBarPrepared = false
		object.isAdhesiveTapeApplied = false
		object.isBarMounted = false
		object.isBarHeatPressed = false
		object.isBarPullOffTested = false
		object.isQCAndMarked = false
		object.isPackaged = false
		object.isRejected = false
		object.isJobScheduled = false
		object.isFinished = false
		object.isDeleted = false
		object.blanket = Blanket.find{id == object.blanketId}
		object = blanketOrderDetailValidationService.createObjectValidation(object as BlanketOrderDetail)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
			blanketOrder.addToBlanketOrderDetails(object)
		}
		return object
	}
	def updateObject(def object){
		def valObject = BlanketOrderDetail.read(object.id)
		valObject.blanketOrder = BlanketOrder.find{id == object.blanketOrderId}
		valObject.blanket = Blanket.find{id == object.blanketId}
		if (NumberUtils.isNumber(object.totalCost) ==  true)
		{
			valObject.totalCost = Double.parseDouble(object.totalCost)
		}
		else
		{
			valObject.totalCost = null
		}
		if (NumberUtils.isNumber(object.barCost) ==  true)
		{
			valObject.barCost = Double.parseDouble(object.barCost)
		}
		else
		{
			valObject.barCost = null
		}
		if (NumberUtils.isNumber(object.adhesiveCost) ==  true)
		{
			valObject.adhesiveCost = Double.parseDouble(object.adhesiveCost)
		}
		else
		{
			valObject.adhesiveCost = null
		}
		if (NumberUtils.isNumber(object.rollBlanketCost) ==  true)
		{
			valObject.rollBlanketCost = Double.parseDouble(object.rollBlanketCost)
		}
		else
		{
			valObject.rollBlanketCost = null
		}
		if (NumberUtils.isNumber(object.rollBlanketUsage) ==  true)
		{
			valObject.rollBlanketUsage = Double.parseDouble(object.rollBlanketUsage)
		}
		else
		{
			valObject.rollBlanketUsage = null
		}
		if (NumberUtils.isNumber(object.rollBlanketDefect) ==  true)
		{
			valObject.rollBlanketDefect = Double.parseDouble(object.rollBlanketDefect)
		}
		else
		{
			valObject.rollBlanketDefect = null
		}
		valObject = blanketOrderDetailValidationService.updateObjectValidation(valObject)
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
		def newObject = BlanketOrderDetail.get(object.id)
		newObject = blanketOrderDetailValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
	def finishObject(def object){
		def newObject = BlanketOrderDetail.get(object.id)
		newObject = blanketOrderDetailValidationService.finishObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isFinished = true
			newObject.FinishedDate = new Date()
			newObject.save()
		}
		return newObject
	}
	def unFinishObject(def object){
		def newObject = BlanketOrderDetail.get(object.id)
		newObject = blanketOrderDetailValidationService.unFinishObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isFinished = false
			newObject.FinishedDate = null
			newObject.save()
		}
		return newObject
	}
	def rejectObject(def object){
		def newObject = BlanketOrderDetail.get(object.id)
		newObject = blanketOrderDetailValidationService.rejectObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isRejected = true
			newObject.RejectedDate = new Date()
			newObject.save()
		}
		return newObject
	}
	def unRejectObject(def object){
		def newObject = BlanketOrderDetail.get(object.id)
		newObject = blanketOrderDetailValidationService.unRejectObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isRejected = false
			newObject.RejectedDate = null
			newObject.save()
		}
		return newObject
	}
}
