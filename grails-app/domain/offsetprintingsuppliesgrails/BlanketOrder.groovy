package offsetprintingsuppliesgrails

class BlanketOrder {
	Contact	contact
	Warehouse	warehouse
	String	code
	Integer	quantityReceived
	Integer	quantityRejected
	Integer	quantityFinal
	Boolean	isConfirmed
	Boolean	hasDueDate
	Date	dueDate
	Date	confirmationDate
	Boolean	isCompleted
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	String	productionNo
	Date	orderDate
	String	notes

	static hasMany = [blanketOrderDetails : BlanketOrderDetail]
	
    static constraints = {
		code(nullable:true)
		dueDate(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		productionNo(nullable:true)
		orderDate(nullable:true)
		notes(nullable:true)
    }
}
