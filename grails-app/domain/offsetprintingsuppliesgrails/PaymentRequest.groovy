package offsetprintingsuppliesgrails

class PaymentRequest {
	Contact	contact
	String	description
	String	code
	Currency	currency
	Payable	accountPayable
	Double	amount
	Double	exchangeRateAmount
	Date	requestDate
	Date	dueDate
	ExchangeRate	exchangeRate
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	String	noBukti
	
	static hasMany = [paymentRequestDetails : PaymentRequestDetail]
	

    static constraints = {
		description(nullable:true)
		code(nullable:true)
		exchangeRate(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		noBukti(nullable:true)
		
    }
}
