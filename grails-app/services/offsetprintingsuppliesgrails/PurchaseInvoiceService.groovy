package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class PurchaseInvoiceService {
	PurchaseInvoiceValidationService purchaseInvoiceValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return PurchaseInvoice.get(object)
		}
		def getList(){
			return PurchaseInvoice.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isTaxable = false
			object.purchaseReceival = PurchaseReceival.find{id == object.purchaseReceivalId}
			object.currency = Currency.find{id == object.currencyId}
			object.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			object = purchaseInvoiceValidationService.createObjectValidation(object as PurchaseInvoice)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = PurchaseInvoice.read(object.id)
			valObject.purchaseReceival = PurchaseReceival.find{id == object.purchaseReceivalId}
			valObject.description = object.description
			valObject.code = object.code
			valObject.nomorSurat = object.nomorSurat
			valObject.currency = Currency.find{id == object.currencyId}
			if (NumberUtils.isNumber(object.exchangeRateAmount) ==  true)
			{
				valObject.exchangeRateAmount = Double.parseDouble(object.exchangeRateAmount)
			}
			else
			{
				valObject.exchangeRateAmount = null
			}
			valObject.exchangeRate = ExchangeRate.find{id == object.exchangeRateId}
			if (NumberUtils.isNumber(object.amountPayable) ==  true)
			{
				valObject.amountPayable = Double.parseDouble(object.amountPayable)
			}
			else
			{
				valObject.amountPayable = null
			}
			if (NumberUtils.isNumber(object.discount) ==  true)
			{
				valObject.discount = Double.parseDouble(object.discount)
			}
			else
			{
				valObject.discount = null
			}
			if (NumberUtils.isNumber(object.tax) ==  true)
			{
				valObject.tax = Double.parseDouble(object.tax)
			}
			else
			{
				valObject.tax = null
			}
			valObject.invoiceDate = object.invoiceDate
			valObject.dueDate = object.dueDate
			valObject = purchaseInvoiceValidationService.updateObjectValidation(valObject)
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
			def newObject = PurchaseInvoice.get(object.id)
			newObject = purchaseInvoiceValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = PurchaseInvoice.get(object.id)
			newObject = purchaseInvoiceValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = PurchaseInvoice.get(object.id)
			newObject = purchaseInvoiceValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
