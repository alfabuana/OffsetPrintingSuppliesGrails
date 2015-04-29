package offsetprintingsuppliesgrails

class CoreBuilder {
	String	baseSku
	String	skuUsedCore
	String	skuNewCore
	Item	usedCoreItem
	Item	newCoreItem
	UoM	uoM
	Machine	machine
	String	coreBuilderTypeCase // 'Hollow', 'Shaft'
	String	name
	String	description
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	Double	cd
	Double	tl
	
    static constraints = {
		baseSku(nullable:true)
		skuUsedCore(nullable:true)
		skuNewCore(nullable:true)
		coreBuilderTypeCase(nullable:true)
		name(nullable:true)
		description(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
