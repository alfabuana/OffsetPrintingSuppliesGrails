package offsetprintingsuppliesgrails

class CoreIdentification {
	String	code
	Warehouse	warehouse
	Contact	contact
	Boolean	isInHouse
	Integer	quantity
	Date	identifiedDate
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isCompleted
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	String	nomorDisassembly
	Date	incomingRoll
	
	static hasMany = [coreIdentificationDetails : CoreIdentificationDetail]

    static constraints = {
		code(nullable:true)
		contact(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		nomorDisassembly(nullable:true)
		incomingRoll(nullable:true)
		
    }
}
