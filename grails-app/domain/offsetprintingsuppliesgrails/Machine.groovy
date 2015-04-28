package offsetprintingsuppliesgrails

class Machine {
	String	code
	String	name
	String	description
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		code(nullable:true)
		name(nullable:true)
		description(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
