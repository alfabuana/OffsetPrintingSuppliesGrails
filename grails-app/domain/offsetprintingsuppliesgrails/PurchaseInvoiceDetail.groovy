package offsetprintingsuppliesgrails

class PurchaseInvoiceDetail {
	PurchaseInvoice	purchaseInvoice
	PurchaseReceivalDetail	purchaseReceivalDetail
	String	code
	Double	quantity
	Double	amount
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = PurchaseInvoice

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
