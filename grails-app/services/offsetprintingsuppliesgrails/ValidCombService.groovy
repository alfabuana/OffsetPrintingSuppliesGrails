package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class ValidCombService {
	ValidCombValidationService validCombValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return ValidComb.get(object)
		}
		def getList(){
			return ValidComb.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.account = Account.find{id == object.accountId}
			object.closing = Closing.find{id == object.closingId}
			object = validCombValidationService.createObjectValidation(object as ValidComb)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = ValidComb.read(object.id)
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
			valObject = validCombValidationService.updateObjectValidation(valObject)
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
			def newObject = ValidComb.get(object.id)
			newObject = validCombValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
	
}
