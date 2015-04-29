package offsetprintingsuppliesgrails

class Blanket {
	String	rollNo
	Contact	contact
	Machine	machine
	Item	adhesive
	Item	adhesive2
	Item	rollBlanketItem
	Item	leftBarItem
	Item	rightBarItem
	Double	ac
	Double	ar
	Double	thickness
	Double	ks
	Boolean	isBarRequired
	Boolean	hasLeftBar
	Boolean	hasRightBar
	String	croppingType // 'Normal', 'Special'
	Double	leftOverAc
	Double	leftOverAr
	Double	special
	String	applicationCase //'Sheetfed', 'Web','Both'
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		rollNo(nullable:true)
		adhesive(nullable:true)
		adhesive2(nullable:true)
		leftBarItem(nullable:true)
		rightBarItem(nullable:true)
		croppingType(nullable:true)
		applicationCase(nullable:true)
		isDeleted(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
