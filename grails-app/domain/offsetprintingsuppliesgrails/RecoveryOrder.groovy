package offsetprintingsuppliesgrails

class RecoveryOrder {
	CoreIdentification	coreIdentification
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
	
	static hasMany = [recoveryOrderDetails : RecoveryOrderDetail]
	

    static constraints = {
		code(nullable:true)
		dueDate(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
