package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class PaymentVoucherDetailService {
	PaymentVoucherDetailValidationService paymentVoucherDetailValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return PaymentVoucherDetail.get(object)
		}
		def getList(){
			return PaymentVoucherDetail.findAll([sort: "id", order: "desc"]){}
		
		}
		def createObject(object){
			object.isDeleted = false
			object.paymentVoucher = PaymentVoucher.find{id == object.paymentVoucher}
			object.payable = Payable.find{id == object.payableId}
			object = paymentVoucherDetailValidationService.createObjectValidation(object as PaymentVoucherDetail)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = PaymentVoucherDetail.read(object.id)
			valObject.paymentVoucher = PaymentVoucher.find{id == object.paymentVoucher}
			valObject.payable = Payable.find{id == object.payableId}
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
			if (NumberUtils.isNumber(object.pph21) ==  true)
			{
				valObject.pph21 = Double.parseDouble(object.pph21)
			}
			else
			{
				valObject.pph21 = null
			}
			if (NumberUtils.isNumber(object.pph23) ==  true)
			{
				valObject.pph23 = Double.parseDouble(object.pph23)
			}
			else
			{
				valObject.pph23 = null
			}
			valObject = paymentVoucherDetailValidationService.updateObjectValidation(valObject)
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
			def newObject = PaymentVoucherDetail.get(object.id)
			newObject = paymentVoucherDetailValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
		
		def confirmObject(def object){
			def newObject = PaymentVoucherDetail.get(object.id)
			newObject = paymentVoucherDetailValidationService.confirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = true
				newObject.confirmationDate = new Date()
				newObject.save()
			}
			return newObject
		}
		def unConfirmObject(def object){
			def newObject = PaymentVoucherDetail.get(object.id)
			newObject = paymentVoucherDetailValidationService.unConfirmObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isConfirmed = false
				newObject.confirmationDate = null
				newObject.save()
			}
			return newObject
		}
}
