package offsetprintingsuppliesgrails

class PriceMutation {
	Item	item
	Double	amount
	Boolean	isActive
	Date	deactivatedDate
	Date	dateCreated
	Boolean	isDeleted
	Date	lastUpdated
	

    static constraints = {
		deactivatedDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
