package offsetprintingsuppliesgrails

class ServiceCost {
	Item	item
	RollerBuilder	rollerBuilder
	Double	quantity
	Double	avgPrice
	Date	dateCreated
	Date	lastUpdated
	Boolean	isDeleted
	

    static constraints = {
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
