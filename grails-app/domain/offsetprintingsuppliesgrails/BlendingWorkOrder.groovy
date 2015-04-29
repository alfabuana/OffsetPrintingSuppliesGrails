package offsetprintingsuppliesgrails

class BlendingWorkOrder {
	String	code
	BlendingRecipe	blendingRecipe
	String	description
	Date	blendingDate
	Warehouse	warehouse
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		code(nullable:true)
		description(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
