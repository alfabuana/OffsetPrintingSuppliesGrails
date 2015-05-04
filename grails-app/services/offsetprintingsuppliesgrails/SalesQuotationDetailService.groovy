package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class SalesQuotationDetailDetailService {
	SalesQuotationDetailValidationService salesQuotationDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return SalesQuotationDetail.get(object)
		}
		def getList(){
			return SalesQuotationDetail.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isConfirmed = false
			object.salesQuotation = SalesQuotation.find{id == object.salesQuotationId}
			object.item = Item.find{id == object.itemId}
			object = salesQuotationDetailValidationService.createObjectValidation(object as SalesQuotationDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = SalesQuotationDetail.read(object.id)
			valObject.code = object.code
			valObject.salesQuotation = SalesQuotation.find{id == object.salesQuotationId}
			valObject.item = Item.find{id == object.itemId}
			if (NumberUtils.isNumber(object.quantity) ==  true)
			{
				valObject.quantity = Double.parseDouble(object.quantity)
			}
			else
			{
				valObject.quantity = null
			}
			if (NumberUtils.isNumber(object.rrp) ==  true)
			{
				valObject.rrp = Double.parseDouble(object.rrp)
			}
			else
			{
				valObject.rrp = null
			}
			if (NumberUtils.isNumber(object.quotationPrice) ==  true)
			{
				valObject.quotationPrice = Double.parseDouble(object.quotationPrice)
			}
			else
			{
				valObject.quotationPrice = null
			}
			valObject = salesQuotationDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = SalesQuotationDetail.get(object.id)
			newObject = salesQuotationDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = SalesQuotationDetail.get(object.id)
			newObject = salesQuotationDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = SalesQuotationDetail.get(object.id)
			newObject = salesQuotationDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
