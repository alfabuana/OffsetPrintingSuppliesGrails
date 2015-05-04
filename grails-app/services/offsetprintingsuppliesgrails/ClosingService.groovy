package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class ClosingService {
	ClosingValidationService closingValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return Closing.get(object)
		}
		def getList(){
			return Closing.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isClosed = false
			object = closingValidationService.createObjectValidation(object as Closing)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = Closing.read(object.id)
			if (NumberUtils.isNumber(object.period) ==  true)
			{
				valObject.period = Integer.parseInt(object.period)
			}
			else
			{
				valObject.period = null
			}
			if (NumberUtils.isNumber(object.yearPeriod) ==  true)
			{
				valObject.yearPeriod = Integer.parseInt(object.yearPeriod)
			}
			else
			{
				valObject.yearPeriod = null
			}
			valObject.beginningPeriod = object.cbeginningPeriod
			valObject.endDatePeriod = object.cendDatePeriod
			valObject.isYear = object.cisYear
			valObject = closingValidationService.updateObjectValidation(valObject)
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
			def newObject = Closing.get(object.id)
			newObject = closingValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def closeObject(def object){
			def newObject = Closing.get(object.id)
			newObject = closingValidationService.closeObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isClosed = true
				newObject.closedDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unCloseObject(def object){
			def newObject = Closing.get(object.id)
			newObject = closingValidationService.unCloseObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isClosed = false
				newObject.closedDate = null
				newObject.save()
			}
			return newObject
		}
}
