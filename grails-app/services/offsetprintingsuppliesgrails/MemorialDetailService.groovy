package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class MemorialDetailService {
	MemorialDetailValidationService memorialDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return MemorialDetail.get(object)
		}
		def getList(){
			return MemorialDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.memorial = Memorial.find{id == object.memorialId}
			object.account = Account.find{id == object.accountId}
			object = memorialDetailValidationService.createObjectValidation(object as MemorialDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = MemorialDetail.read(object.id)
			valObject.memorial = Memorial.find{id == object.memorialId}
			valObject.account = Account.find{id == object.accountId}
			valObject.code = object.code
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
			valObject = memorialDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = MemorialDetail.get(object.id)
			newObject = memorialDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = MemorialDetail.get(object.id)
			newObject = memorialDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = MemorialDetail.get(object.id)
			newObject = memorialDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
