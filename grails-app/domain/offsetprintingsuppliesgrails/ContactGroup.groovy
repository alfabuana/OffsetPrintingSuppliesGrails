package offsetprintingsuppliesgrails

class ContactGroup {
	String	name
	String	description
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
