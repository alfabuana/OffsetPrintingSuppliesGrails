package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class ExchangeRateService {
	ExchangeRateValidationService exchangeRateValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return ExchangeRate.get(object)
		}
		def getList(){
			return ExchangeRate.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.currency = Currency.find{id == object.currencyId}
			object = exchangeRateValidationService.createObjectValidation(object as ExchangeRate)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = ExchangeRate.read(object.id)
			valObject.currency = Currency.find{id == object.currencyId}
			valObject.exRateDate = object.exRateDate
			if (NumberUtils.isNumber(object.rate) ==  true)
			{
				valObject.rate = Double.parseDouble(object.rate)
			}
			else
			{
				valObject.rate = null
			}
			valObject = exchangeRateValidationService.updateObjectValidation(valObject)
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
			def newObject = ExchangeRate.get(object.id)
			newObject = exchangeRateValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
}
