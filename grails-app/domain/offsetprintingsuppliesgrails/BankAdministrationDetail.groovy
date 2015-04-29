package offsetprintingsuppliesgrails

class BankAdministrationDetail {
	BankAdministration	bankAdministration
	Account	account
	String	code
	String	description
	Integer	status
	Double	amount
	Boolean	isLegacy
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = BankAdministration

    static constraints = {
		code(nullable:true)
		description(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
