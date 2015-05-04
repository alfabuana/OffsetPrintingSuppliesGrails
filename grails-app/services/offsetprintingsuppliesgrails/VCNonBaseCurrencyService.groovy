package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class VCNonBaseCurrencyService {
	VCNonBaseCurrencyValidationService vcNonBaseCurrencyValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return VCNonBaseCurrency.get(object)
		}
		def getList(){
			return VCNonBaseCurrency.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.validComb = ValidComb.find{id == object.validCombId}
			object = vcNonBaseCurrencyValidationService.createObjectValidation(object as VCNonBaseCurrency)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = VCNonBaseCurrency.read(object.id)
			valObject.validComb = ValidComb.find{id == object.validCombId}
			if (NumberUtils.isNumber(object.amount) ==  true)
			{
				valObject.amount = Double.parseDouble(object.amount)
			}
			else
			{
				valObject.amount = null
			}
			valObject = vcNonBaseCurrencyValidationService.updateObjectValidation(valObject)
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
			def newObject = VCNonBaseCurrency.get(object.id)
			newObject = vcNonBaseCurrencyValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
}
