package offsetprintingsuppliesgrails

class MemorialDetail {
	Memorial	memorial
	Account	account
	String	code
	Integer	status // 'Debit','Kredit'
	Double	amount
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = Memorial
	

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
