package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class PaymentVoucherService {
PaymentVoucherValidationService paymentVoucherValidationService

    def serviceMethod() {

    }
	
	def getObjectById(def object){
		return PaymentVoucher.get(object)
	}
	def getList(){
		return PaymentVoucher.findAll([sort: "id", order: "desc"]){}
	}
	
	def createObject(object){
		object.isDeleted = false
		object.isReconciled = false
		object.contact = Contact.find{id == object.contactId}
		object.cashBank = CashBank.find{id == object.cashBankId}
		object = paymentVoucherValidationService.createObjectValidation(object as PaymentVoucher)
		if (object.errors.getErrorCount() == 0)
		{
			object = object.save()
		}
		return object
	}
	def updateObject(def object){
		def valObject = PaymentVoucher.read(object.id)
		valObject.contact = Contact.find{id == object.contactId}
		valObject.cashBank = CashBank.find{id == object.cashBankId}
		valObject.code = object.code
		valObject.paymentDate = object.paymentDate
		valObject.isGBCH = object.isGBCH
		valObject.dueDate = object.dueDate
		if (NumberUtils.isNumber(object.rateToIDR) ==  true)
		{
			valObject.rateToIDR = Double.parseDouble(object.rateToIDR)
		}
		else
		{
			valObject.rateToIDR = null
		}
		if (NumberUtils.isNumber(object.totalAmount) ==  true)
		{
			valObject.totalAmount = Double.parseDouble(object.totalAmount)
		}
		else
		{
			valObject.totalAmount = null
		}
		valObject.noBukti = object.noBukti
		if (NumberUtils.isNumber(object.totalPph23) ==  true)
		{
			valObject.totalPph23 = Double.parseDouble(object.totalPph23)
		}
		else
		{
			valObject.totalPph23 = null
		}
		if (NumberUtils.isNumber(object.biayaBank) ==  true)
		{
			valObject.biayaBank = Double.parseDouble(object.biayaBank)
		}
		else
		{
			valObject.biayaBank = null
		}
		if (NumberUtils.isNumber(object.pembulatan) ==  true)
		{
			valObject.pembulatan = Double.parseDouble(object.pembulatan)
		}
		else
		{
			valObject.pembulatan = null
		}
		if (NumberUtils.isNumber(object.statusPembulatan) ==  true)
		{
			valObject.statusPembulatan = Integer.parseInt(object.statusPembulatan)
		}
		else
		{
			valObject.statusPembulatan = null
		}
		if (NumberUtils.isNumber(object.totalPph21) ==  true)
		{
			valObject.totalPph21 = Double.parseDouble(object.totalPph21)
		}
		else
		{
			valObject.totalPph21 = null
		}
		valObject.noGBCH = object.noGBCH
		valObject = paymentVoucherValidationService.updateObjectValidation(valObject)
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
		def newObject = PaymentVoucher.get(object.id)
		newObject = paymentVoucherValidationService.softdeleteObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isDeleted = true
			newObject.save()
		}
		return newObject
	}
	def confirmObject(def object){
		def newObject = PaymentVoucher.get(object.id)
		newObject = paymentVoucherValidationService.confirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = true
			newObject.confirmationDate = new Date()
			newObject.save()
		}
		return newObject
	}
	def unConfirmObject(def object){
		def newObject = PaymentVoucher.get(object.id)
		newObject = paymentVoucherValidationService.unConfirmObjectValidation(newObject)
		if (newObject.errors.getErrorCount() == 0)
		{
			newObject.isConfirmed = false
			newObject.confirmationDate = null
			newObject.save()
		}
		return newObject
	}
}
