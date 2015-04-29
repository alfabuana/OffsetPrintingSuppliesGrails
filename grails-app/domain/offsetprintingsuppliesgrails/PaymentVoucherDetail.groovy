package offsetprintingsuppliesgrails

class PaymentVoucherDetail {
	PaymentVoucher	paymentVoucher
	Payable	payable
	String	code
	Double	rate
	Double	amount
	Double	amountPaid
	String	description
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	Double	pph21
	Double	pph23
	
	static belongsTo = PaymentVoucher
	

    static constraints = {
		code(nullable:true)
		description(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
