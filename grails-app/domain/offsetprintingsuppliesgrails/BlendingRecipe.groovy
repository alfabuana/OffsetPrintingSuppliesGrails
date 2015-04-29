package offsetprintingsuppliesgrails

class BlendingRecipe {
	String	name
	String	description
	Item	targetItem
	Double	targetQuantity
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static hasMany = [blendingRecipes : BlendingRecipe]
	
    static constraints = {
		name(nullable:true)
		description(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
