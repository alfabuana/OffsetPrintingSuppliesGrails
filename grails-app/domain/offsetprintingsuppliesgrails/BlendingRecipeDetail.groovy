package offsetprintingsuppliesgrails

class BlendingRecipeDetail {
	BlendingRecipe	blendingRecipe
	Item	item
	Double	quantity
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = BlendingRecipe

    static constraints = {
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
