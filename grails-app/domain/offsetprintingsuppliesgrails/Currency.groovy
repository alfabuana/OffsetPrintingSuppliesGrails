package offsetprintingsuppliesgrails

class Currency {
	String	name
	String	description
	Boolean	isBase
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		name(nullable:true)
		description(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
