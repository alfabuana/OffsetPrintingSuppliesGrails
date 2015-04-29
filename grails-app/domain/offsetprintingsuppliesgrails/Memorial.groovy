package offsetprintingsuppliesgrails

class Memorial {
	String	description
	String	code
	Double	amount
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	String	noBukti
	
	static hasMany = [memorialDetails : MemorialDetail]
	
    static constraints = {
		description(nullable:true)
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		noBukti(nullable:true)
    }
}
