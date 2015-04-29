package offsetprintingsuppliesgrails

class SalesOrderDetail {
	String	code
	String	orderCode
	SalesOrder	salesOrder
	Item	item
	Double	quantity
	Boolean	isAlDelivered
	Double	pendingDeliveryQuantity
	Double	price
	Boolean	isService
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = SalesOrder

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
