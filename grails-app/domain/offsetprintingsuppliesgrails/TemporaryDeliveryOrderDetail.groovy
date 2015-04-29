package offsetprintingsuppliesgrails

class TemporaryDeliveryOrderDetail {
	String	code
	TemporaryDeliveryOrder	temporaryDeliveryOrder
	Item	item
	Double	quantity
	Boolean	isReconciled
	Boolean	isAllCompleted
	Double	wasteCOGS
	Double	wasteQuantity
	Double	restockQuantity
	Double	sellingPrice
	SalesOrderDetail	salesOrderDetail
	VirtualOrderDetail	virtualOrderDetail
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = TemporaryDeliveryOrder

    static constraints = {
		code(nullable:true)
		salesOrderDetail(nullable:true)
		virtualOrderDetail(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
