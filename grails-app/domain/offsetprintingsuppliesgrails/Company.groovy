package offsetprintingsuppliesgrails

class Company {
	String	name
	String	address
	String	contactNo
	String	email
	String	logo
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	String	city
	String	npwp
	

    static constraints = {
		name(nullable:true)
		address(nullable:true)
		contactNo(nullable:true)
		email(nullable:true)
		logo(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		city(nullable:true)
		npwp(nullable:true)
		
    }
}
