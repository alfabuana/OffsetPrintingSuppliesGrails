package offsetprintingsuppliesgrails

class ContactDetail {
	Contact	contact
	String	name
	String	contactNo
	String	email
	String	address
	String	description
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = Contact

    static constraints = {
		name(nullable:true)
		contactNo(nullable:true)
		email(nullable:true)
		address(nullable:true)
		description(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
