package offsetprintingsuppliesgrails

class ReceiptRequestDetail {
	ReceiptRequest	receiptRequest
	Account	account
	String	code
	Integer	status
	Double	amount
	Boolean	isLegacy
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = ReceiptRequest

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
