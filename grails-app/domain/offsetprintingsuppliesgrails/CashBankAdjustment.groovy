package offsetprintingsuppliesgrails

class CashBankAdjustment {
	CashBank	cashBank
	Date	adjustmentDate
	Double	amount
	String	code
	Double	exchangeRateAmount
	ExchangeRate	exchangeRate
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		code(nullable:true)
		exchangeRate(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
