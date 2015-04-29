package offsetprintingsuppliesgrails

class ReceiptRequest {
	Contact	contact
	String	description
	String	code
	Currency	currency
	Receivable	accountReceivable
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
	
	static hasMany = [receiptRequestDetails : ReceiptRequestDetail]

    static constraints = {
		description(nullable:true)
		code(nullable:true)
		exchangeRate(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
