package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class SalesOrderService {
	SalesOrderValidationService salesOrderValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return SalesOrder.get(object)
		}
		def getList(){
			return SalesOrder.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isDeliveryCompleted = false
			object.contact = Contact.find{id == object.contactId}
			object.currency = Currency.find{id == object.currencyId}
			object.employee = Employee.find{id == object.employeeId}
			object = salesOrderValidationService.createObjectValidation(object as SalesOrder)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = SalesOrder.read(object.id)
			valObject.code = object.code
			if (NumberUtils.isNumber(object.orderType) ==  true)
			{
				valObject.orderType = Double.parseDouble(object.orderType)
			}
			else
			{
				valObject.orderType = null
			}
			valObject.orderCode = object.orderCode
			valObject.contact = Contact.find{id == object.contactId}
			valObject.salesDate = object.salesDate
			valObject.nomorSurat = object.nomorSurat
			valObject.currency = Currency.find{id == object.currencyId}
			valObject.employee = Employee.find{id == object.employeeId}
			valObject = salesOrderValidationService.updateObjectValidation(valObject)
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
			def newObject = SalesOrder.get(object.id)
			newObject = salesOrderValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		def confirmObject(def object){
			def newObject = SalesOrder.get(object.id)
			newObject = salesOrderValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = SalesOrder.get(object.id)
			newObject = salesOrderValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
