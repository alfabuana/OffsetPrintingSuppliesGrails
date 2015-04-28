package offsetprintingsuppliesgrails

class Employee {
	String	name
	String	address
	String	contactNo
	String	email
	String	description
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		name(nullable:true)
		address(nullable:true)
		contactNo(nullable:true)
		email(nullable:true)
		description(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
