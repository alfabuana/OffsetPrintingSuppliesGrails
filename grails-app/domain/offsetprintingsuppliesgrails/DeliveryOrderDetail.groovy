package offsetprintingsuppliesgrails

class DeliveryOrderDetail {
	String	code
	Integer	orderType
	String	orderCode
	DeliveryOrder	deliveryOrder
	Item	item
	Double	quantity
	Boolean	isAllInvoiced
	Double	pendingInvoicedQuantity
	Integer	salesOrderDetail
	Double	cogs
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	Contact	contact
	
	static belongsTo = DeliveryOrder

    static constraints = {
		code(nullable:true)
		orderCode(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		contact(nullable:true)
		
    }
}
