package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class CashBankMutationService {
	CashBankMutationValidationService cashBankMutationValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return CashBankMutation.get(object)
		}
		def getList(){
			return CashBankMutation.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.sourceCashBank = CashBank.find{id == object.cashBankId}
			object.targetCashBank = CashBank.find{id == object.cashBankId}
			object.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			object = cashBankMutationValidationService.createObjectValidation(object as CashBankMutation)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = CashBankMutation.read(object.id)
			valObject.sourceCashBank = CashBank.find{id == object.cashBankId}
			valObject.targetCashBank = CashBank.find{id == object.cashBankId}
			if (NumberUtils.isNumber(object.amount) ==  true)
			{
				valObject.amount = Double.parseDouble(object.amount)
			}
			else
			{
				valObject.amount = null
			}
			valObject.code = object.code
			valObject.mutationDate = object.mutationDate
			if (NumberUtils.isNumber(object.exchangeRateAmount) ==  true)
			{
				valObject.exchangeRateAmount = Double.parseDouble(object.exchangeRateAmount)
			}
			else
			{
				valObject.exchangeRateAmount = null
			}
			valObject.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			valObject.noBukti = object.noBukti
			valObject = cashBankMutationValidationService.updateObjectValidation(valObject)
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
			def newObject = CashBankMutation.get(object.id)
			newObject = cashBankMutationValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = CashBankMutation.get(object.id)
			newObject = cashBankMutationValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = CashBankMutation.get(object.id)
			newObject = cashBankMutationValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
