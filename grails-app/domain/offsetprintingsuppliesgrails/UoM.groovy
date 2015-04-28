package offsetprintingsuppliesgrails

class UoM {
	String	name
	Boolean	isDeleted
	Boolean	dateCreated
	Boolean	lastUpdated
	

    static constraints = {
		name(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
