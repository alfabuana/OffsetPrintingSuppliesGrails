package offsetprintingsuppliesgrails

class VirtualOrderDetail {
	String	code
	VirtualOrder	virtualOrder
	Item	item
	Double	quantity
	Boolean	isAllDelivered
	Double	pendingDeliveryQuantity
	Double	price
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = VirtualOrder

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
