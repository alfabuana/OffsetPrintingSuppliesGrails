package offsetprintingsuppliesgrails

class ItemType {
	String	name
	String	description
	Boolean	isLegacy
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
//	Account	account
	

    static constraints = {
		name(nullable:true)
		description(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
//		account(nullable:true)
		
    }
}
