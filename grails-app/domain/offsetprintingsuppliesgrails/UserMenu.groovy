package offsetprintingsuppliesgrails

class UserMenu {
	String	name
	String	groupName
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		name(nullable:true)
		groupName(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)s
		
    }
}
