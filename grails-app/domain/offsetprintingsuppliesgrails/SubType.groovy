package offsetprintingsuppliesgrails

class SubType {
	String	name
	ItemType	itemType
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		name(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
