package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class ReceiptVoucherDetailService {
	ReceiptVoucherDetailValidationService receiptVoucherDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return ReceiptVoucherDetail.get(object)
		}
		def getList(){
			return ReceiptVoucherDetail.findAll([sort: "id", order: "desc"]){}
		
		}
		def createObject(object){
			object.isDeleted = false
			object.receiptVoucher = ReceiptVoucher.find{id == object.receiptVoucher}
			object.receivable = Receivable.find{id == object.receivableId}
			object = receiptVoucherDetailValidationService.createObjectValidation(object as ReceiptVoucherDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = ReceiptVoucherDetail.read(object.id)
			valObject.receiptVoucher = ReceiptVoucher.find{id == object.receiptVoucher}
			valObject.receivable = Receivable.find{id == object.receivableId}
			valObject.code = object.code
			if (NumberUtils.isNumber(object.rate) ==  true)
			{
				valObject.rate = Double.parseDouble(object.rate)
			}
			else
			{
				valObject.rate = null
			}
			if (NumberUtils.isNumber(object.amount) ==  true)
			{
				valObject.amount = Double.parseDouble(object.amount)
			}
			else
			{
				valObject.amount = null
			}
			if (NumberUtils.isNumber(object.amountPaid) ==  true)
			{
				valObject.amountPaid = Double.parseDouble(object.amountPaid)
			}
			else
			{
				valObject.amountPaid = null
			}
			valObject.description = object.description
			if (NumberUtils.isNumber(object.pph23) ==  true)
			{
				valObject.pph23 = Double.parseDouble(object.pph23)
			}
			else
			{
				valObject.pph23 = null
			}
			valObject = receiptVoucherDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = ReceiptVoucherDetail.get(object.id)
			newObject = receiptVoucherDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = ReceiptVoucherDetail.get(object.id)
			newObject = receiptVoucherDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = ReceiptVoucherDetail.get(object.id)
			newObject = receiptVoucherDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}

}
