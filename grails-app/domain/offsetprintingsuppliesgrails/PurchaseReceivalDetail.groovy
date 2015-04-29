package offsetprintingsuppliesgrails

class PurchaseReceivalDetail {
	String	code
	PurchaseReceival	purchaseReceival
	Item	item
	Double	quantity
	Boolean	isAllInvoiced
	Double	pendingInvoicedQuantity
	Integer	purchaseOrderDetail
	Double	cogs
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = PurchaseReceival
	

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
