package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class GLNonBaseCurrencyService {
	GLNonBaseCurrencyValidationService glNonBaseCurrencyValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return GLNonBaseCurrency.get(object)
		}
		def getList(){
			return GLNonBaseCurrency.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.generalLedgerJournal = GeneralLedgerJournal.find{id == object.generalLedgerJournalId}
			object.currency = Currency.find{id == object.currencyId}
			object = glNonBaseCurrencyValidationService.createObjectValidation(object as GLNonBaseCurrency)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = GLNonBaseCurrency.read(object.id)
			valObject.generalLedgerJournal = GeneralLedgerJournal.find{id == object.generalLedgerJournalId}
			valObject.currency = Currency.find{id == object.currencyId}
			if (NumberUtils.isNumber(object.amount) ==  true)
			{
				valObject.amount = Double.parseDouble(object.amount)
			}
			else
			{
				valObject.amount = null
			}
			valObject = glNonBaseCurrencyValidationService.updateObjectValidation(valObject)
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
			def newObject = GLNonBaseCurrency.get(object.id)
			newObject = glNonBaseCurrencyValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
}
