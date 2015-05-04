package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class BankAdministrationService {
	BankAdministrationValidationService bankAdministrationValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return BankAdministration.get(object)
		}
		def getList(){
			return BankAdministration.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.cashBank = CashBank.find{id == object.cashBankId}
			object.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			object = bankAdministrationValidationService.createObjectValidation(object as BankAdministration)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = BankAdministration.read(object.id)
			valObject.cashBank = CashBank.find{id == object.cashBankId}
			valObject.administrationDate = object.administrationDate
			valObject.code = object.code
			valObject.noBukti = object.noBukti
			if (NumberUtils.isNumber(object.amount) ==  true)
			{
				valObject.amount = Double.parseDouble(object.amount)
			}
			else
			{
				valObject.amount = null
			}
			if (NumberUtils.isNumber(object.exchangeRateAmount) ==  true)
			{
				valObject.exchangeRateAmount = Double.parseDouble(object.exchangeRateAmount)
			}
			else
			{
				valObject.exchangeRateAmount = null
			}
			valObject.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			valObject.description = object.description
			valObject = bankAdministrationValidationService.updateObjectValidation(valObject)
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
			def newObject = BankAdministration.get(object.id)
			newObject = bankAdministrationValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = BankAdministration.get(object.id)
			newObject = bankAdministrationValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = BankAdministration.get(object.id)
			newObject = bankAdministrationValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
