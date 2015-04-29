package offsetprintingsuppliesgrails

class ReceiptVoucherDetail {
	ReceiptVoucher	receiptVoucher
	Receivable	receivable
	String	code
	Double	rate
	Double	amount
	Double	amoutPaid
	String	description
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	Double	pph23
	
	static belongsTo = ReceiptVoucher
	

    static constraints = {
		code(nullable:true)
		description(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
