package offsetprintingsuppliesgrails

class PaymentRequestDetail {
	PaymentRequest	paymentRequest
	Account	account
	String	code
	Integer	status // 'Debit','Kredit'
	Double	amount
	Boolean	isLegacy
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = PaymentRequest
	

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
