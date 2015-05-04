package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class GeneralLedgerJournalService {
	GeneralLedgerJournalValidationService generalLedgerJournalValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return GeneralLedgerJournal.get(object)
		}
		def getList(){
			return GeneralLedgerJournal.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.account = Account.find{id == object.accountId}
			object = generalLedgerJournalValidationService.createObjectValidation(object as GeneralLedgerJournal)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = GeneralLedgerJournal.read(object.id)
			valObject.account = Account.find{id == object.accountId}
			valObject.sourceDocument = object.sourceDocument
			if (NumberUtils.isNumber(object.sourceDocumentId) ==  true)
			{
				valObject.sourceDocumentId = Integer.parseInt(object.sourceDocumentId)
			}
			else
			{
				valObject.sourceDocumentId = null
			}
			if (NumberUtils.isNumber(object.status) ==  true)
			{
				valObject.status = Integer.parseInt(object.status)
			}
			else
			{
				valObject.status = null
			}
			valObject.transactionDate = object.transactionDate
			if (NumberUtils.isNumber(object.amount) ==  true)
			{
				valObject.amount = Double.parseDouble(object.amount)
			}
			else
			{
				valObject.amount = null
			}
			valObject.description = object.description
			valObject = generalLedgerJournalValidationService.updateObjectValidation(valObject)
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
			def newObject = GeneralLedgerJournal.get(object.id)
			newObject = generalLedgerJournalValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
}
