package offsetprintingsuppliesgrails

class CashBankMutation {
	CashBank	sourceCashBank
	CashBank	targetCashBank
	Double	amount
	String	code
	Date	mutationDate
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	Double	exchangeRateAmount
	ExchangeRate	exchangeRate
	String	noBukti
	

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		exchangeRate(nullable:true)
		noBukti(nullable:true)
    }
}
