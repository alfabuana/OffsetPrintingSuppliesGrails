package offsetprintingsuppliesgrails

class RetailPurchaseInvoiceDetail {
	Item	item
	PriceMutation	priceMutation
	RetailPurchaseInvoice	retailPurchaseInvoice
	String	code
	Integer	quantity
	Double	cogs
	Double	amount
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = RetailPurchaseInvoice

    static constraints = {
		code(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
