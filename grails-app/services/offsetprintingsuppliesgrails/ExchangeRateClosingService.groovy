package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class ExchangeRateClosingService {
	ExchangeRateClosingValidationService exchangeRateClosingValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return ExchangeRateClosing.get(object)
		}
		def getList(){
			return ExchangeRateClosing.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.closing = Closing.find{id == object.closingId}
			object.currency = Currency.find{id == object.currencyId}
			object = exchangeRateClosingValidationService.createObjectValidation(object as ExchangeRateClosing)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = ExchangeRateClosing.read(object.id)
			valObject.closing = Closing.find{id == object.closingId}
			valObject.currency = Currency.find{id == object.currencyId}
			if (NumberUtils.isNumber(object.rate) ==  true)
			{
				valObject.rate = Double.parseDouble(object.rate)
			}
			else
			{
				valObject.rate = null
			}
			valObject = exchangeRateClosingValidationService.updateObjectValidation(valObject)
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
			def newObject = ExchangeRateClosing.get(object.id)
			newObject = exchangeRateClosingValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
}
