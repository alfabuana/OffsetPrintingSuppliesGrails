package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class CashBankAdjustmentService {
	CashBankAdjustmentValidationService cashBankAdjustmentValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return CashBankAdjustment.get(object)
		}
		def getList(){
			return CashBankAdjustment.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.cashBank = CashBank.find{id == object.cashBankId}
			object.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			object = cashBankAdjustmentValidationService.createObjectValidation(object as CashBankAdjustment)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = CashBankAdjustment.read(object.id)
			valObject.cashBank = CashBank.find{id == object.cashBankId}
			valObject.adjustmentDate = object.adjustmentDate
			if (NumberUtils.isNumber(object.amount) ==  true)
			{
				valObject.amount = Double.parseDouble(object.amount)
			}
			else
			{
				valObject.amount = null
			}
			valObject.code = object.code
			if (NumberUtils.isNumber(object.exchangeRateAmount) ==  true)
			{
				valObject.exchangeRateAmount = Double.parseDouble(object.exchangeRateAmount)
			}
			else
			{
				valObject.exchangeRateAmount = null
			}
			valObject.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			valObject = cashBankAdjustmentValidationService.updateObjectValidation(valObject)
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
			def newObject = CashBankAdjustment.get(object.id)
			newObject = cashBankAdjustmentValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = CashBankAdjustment.get(object.id)
			newObject = cashBankAdjustmentValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = CashBankAdjustment.get(object.id)
			newObject = cashBankAdjustmentValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
