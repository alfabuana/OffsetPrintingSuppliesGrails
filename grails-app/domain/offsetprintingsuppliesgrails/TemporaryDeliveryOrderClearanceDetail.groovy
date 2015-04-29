package offsetprintingsuppliesgrails

class TemporaryDeliveryOrderClearanceDetail {
	String	code
	TemporaryDeliveryOrderClearance	temporaryDeliveryOrderClearance
	Double	quantity
	Double	wasteCOGS
	Double	sellingPrice
	TemporaryDeliveryOrderDetail	temporaryDeliveryOrderDetail
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = TemporaryDeliveryOrderClearance

    static constraints = {
		code(nullable:true)
		temporaryDeliveryOrderDetail(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
