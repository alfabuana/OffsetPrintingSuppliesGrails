package offsetprintingsuppliesgrails

class UserAccount {
	String	username
	String	password
	String	name
	String	description
	Boolean	isAdmin
	Boolean	isDeleted
	Date	lastUpdated
	Date	dateCreated
	

    static constraints = {
		username(nullable:true)
		password(nullable:true)
		name(nullable:true)
		description(nullable:true)
		lastUpdated(nullable:true)
		dateCreated(nullable:true)
    }
}
