package offsetprintingsuppliesgrails

class Repacking {
	String	code
	BlendingRecipe	blendingRecipe
	String	description
	Date	repackingDate
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
