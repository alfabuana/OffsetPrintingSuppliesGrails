package offsetprintingsuppliesgrails

class RetailSalesInvoiceDetail {
	Item	item
	PriceMutation	priceMutation
	RetailSalesInvoice	retailSalesInvoice
	String	code
	Integer	quantity
	Double	cogs
	Double	amount
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = RetailSalesInvoice

    static constraints = {
		code(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
