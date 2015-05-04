package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class CurrencyService {
	CurrencyValidationService currencyValidationService
	
		def serviceMethod() {
	
		}
		def getObjectById(def object){
			return Currency.get(object)
		}
		def getList(){
			return Currency.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object = currencyValidationService.createObjectValidation(object as Currency)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = Currency.read(object.id)
			valObject.name = object.name
			valObject.description = object.description
			valObject.isBase = object.isBase
			valObject = currencyValidationService.updateObjectValidation(valObject)
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
			def newObject = Currency.get(object.id)
			newObject = currencyValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
}
