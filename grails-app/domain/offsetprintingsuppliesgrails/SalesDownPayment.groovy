package offsetprintingsuppliesgrails

class SalesDownPayment {
	Contact	contact
	Receivable	receivable
	Payable	payable
	String	code
	Date	downPaymentDate
	Date	dueDate
	Currency	currency
	Double	exchangeRateAmount
	ExchangeRate	exchangeRate
	Double	totalAmount
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated

    static constraints = {
		receivable(nullable:true)
		payable(nullable:true)
		code(nullable:true)
		dueDate(nullable:true)
		exchangeRate(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
