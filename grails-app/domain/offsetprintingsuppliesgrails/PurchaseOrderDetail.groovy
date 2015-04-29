package offsetprintingsuppliesgrails

class PurchaseOrderDetail {
	String	code
	PurchaseOrder	purchaseOrder
	Item	item
	Double	quantity
	Boolean	isAllreceived
	Double	pendingReceivalQuantity
	Double	price
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = PurchaseOrder
	

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
