package offsetprintingsuppliesgrails

class RollerBuilder {
	Machine	machine
	RollerType	rollerType
	Compound	compound
	CoreBuilder	coreBuilder
	String	baseSku
	String	skuRollerUsedCore
	String	skuRollerNewCore
	Item	rollerUsedCoreItem
	Item	rollerNewCoreItem
	Item	adhesive
	UoM	uoM
	String	name
	String	description
	Double	rd
	Double	cd
	Double	rl
	Double	wl
	Double	tl
	Boolean	isCrowning
	Integer	crowningSize
	Boolean	isGrooving
	Integer	groovingWidth
	Integer	groovingDepth
	Integer	groovingPosition
	Boolean	isChamfer
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		baseSku(nullable:true)
		skuRollerUsedCore(nullable:true)
		skuRollerNewCore(nullable:true)
		name(nullable:true)
		description(nullable:true)
		crowningSize(nullable:true)
		groovingWidth(nullable:true)
		groovingDepth(nullable:true)
		groovingPosition(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
