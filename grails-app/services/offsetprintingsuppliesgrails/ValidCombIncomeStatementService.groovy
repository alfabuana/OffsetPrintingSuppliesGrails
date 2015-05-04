package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class ValidCombIncomeStatementService {
	ValidCombIncomeStatementValidationService validCombIncomeStatementValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return ValidCombIncomeStatement.get(object)
		}
		def getList(){
			return ValidCombIncomeStatement.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.account = Account.find{id == object.accountId}
			object.closing = Closing.find{id == object.closingId}
			object = validCombIncomeStatementValidationService.createObjectValidation(object as ValidCombIncomeStatement)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = ValidCombIncomeStatement.read(object.id)
			valObject.account = Account.find{id == object.accountId}
			valObject.closing = Closing.find{id == object.closingId}
			if (NumberUtils.isNumber(object.amount) ==  true)
			{
				valObject.amount = Double.parseDouble(object.amount)
			}
			else
			{
				valObject.amount = null
			}
			valObject = validCombIncomeStatementValidationService.updateObjectValidation(valObject)
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
			def newObject = ValidCombIncomeStatement.get(object.id)
			newObject = validCombIncomeStatementValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
}
