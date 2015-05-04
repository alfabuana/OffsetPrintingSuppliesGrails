package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class SalesQuotationService {
	SalesQuotationValidationService salesQuotationValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return SalesQuotation.get(object)
		}
		def getList(){
			return SalesQuotation.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isConfirmed = false
			object.isApproved = false
			object.isRejected = false
			object.isSalesOrderConfirmed = false
			object.contact = Contact.find{id == object.contactId}
			object = salesQuotationValidationService.createObjectValidation(object as SalesQuotation)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = SalesQuotation.read(object.id)
			valObject.code = object.code
			valObject.versionNo = object.versionNo
			valObject.nomorSurat = object.nomorSurat
			valObject.contact = Contact.find{id == object.contactId}
			valObject.quotationDate = object.quotationDate
			if (NumberUtils.isNumber(object.totalQuotedAmount) ==  true)
			{
				valObject.totalQuotedAmount = Double.parseDouble(object.totalQuotedAmount)
			}
			else
			{
				valObject.totalQuotedAmount = null
			}
			if (NumberUtils.isNumber(object.totalRrpAmount) ==  true)
			{
				valObject.totalRrpAmount = Double.parseDouble(object.totalRrpAmount)
			}
			else
			{
				valObject.totalRrpAmount = null
			}
			if (NumberUtils.isNumber(object.costSaved) ==  true)
			{
				valObject.costSaved = Double.parseDouble(object.costSaved)
			}
			else
			{
				valObject.costSaved = null
			}
			if (NumberUtils.isNumber(object.percentageSaved) ==  true)
			{
				valObject.percentageSaved = Double.parseDouble(object.percentageSaved)
			}
			else
			{
				valObject.percentageSaved = null
			}
			valObject.catatan = object.catatan
			valObject = salesQuotationValidationService.updateObjectValidation(valObject)
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
			def newObject = SalesQuotation.get(object.id)
			newObject = salesQuotationValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = SalesQuotation.get(object.id)
			newObject = salesQuotationValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = SalesQuotation.get(object.id)
			newObject = salesQuotationValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
