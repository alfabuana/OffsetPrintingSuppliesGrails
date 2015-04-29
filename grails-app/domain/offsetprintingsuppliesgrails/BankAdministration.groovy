package offsetprintingsuppliesgrails

class BankAdministration {
	CashBank	cashBank
	Date	administrationDate
	String	code
	String	noBukti
	Double	amount
	Double	exchangeRateAmount
	ExchangeRate	exchangeRate
	String	description
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static hasMany = [bankAdministrationDetails : BankAdministration]

    static constraints = {
		code(nullable:true)
		noBukti(nullable:true)
		exchangeRate(nullable:true)
		description(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
