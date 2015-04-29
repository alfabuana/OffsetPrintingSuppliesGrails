package offsetprintingsuppliesgrails

class TemporaryDeliveryOrderClearance {
	String	code
	TemporaryDeliveryOrder	temporaryDeliveryOrder
	Date	clearanceDate
	Double	totalWasteCOGS
	Boolean	isWaste
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static hasMany = [temporaryDeliveryOrderClearanceDetails : TemporaryDeliveryOrderClearanceDetail]

    static constraints = {
		code(nullable:true)
		temporaryDeliveryOrder(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
