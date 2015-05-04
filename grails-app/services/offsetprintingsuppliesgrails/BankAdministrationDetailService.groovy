package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class BankAdministrationDetailService {
	BankAdministrationDetailValidationService bankAdministrationDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return BankAdministrationDetail.get(object)
		}
		def getList(){
			return BankAdministrationDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isLegacy = false
			object.bankAdministration = BankAdministration.find{id == object.bankAdministrationId}
			object.account = Account.find{id == object.accountId}
			object = bankAdministrationDetailValidationService.createObjectValidation(object as BankAdministrationDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = BankAdministration.read(object.id)
			valObject.bankAdministration = BankAdministration.find{id == object.bankAdministrationId}
			valObject.account = Account.find{id == object.accountId}
			valObject.code = object.code
			valObject.description = object.description
			if (NumberUtils.isNumber(object.status) ==  true)
			{
				valObject.status = Integer.parseInt(object.status)
			}
			else
			{
				valObject.status = null
			}
			if (NumberUtils.isNumber(object.amount) ==  true)
			{
				valObject.amount = Double.parseDouble(object.amount)
			}
			else
			{
				valObject.amount = null
			}
			valObject = bankAdministrationDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = BankAdministrationDetail.get(object.id)
			newObject = bankAdministrationDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = BankAdministrationDetail.get(object.id)
			newObject = bankAdministrationDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = BankAdministrationDetail.get(object.id)
			newObject = bankAdministrationDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
