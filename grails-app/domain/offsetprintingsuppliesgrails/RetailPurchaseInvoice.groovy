package offsetprintingsuppliesgrails

class RetailPurchaseInvoice {
	String	code
	Date	purchaseDate
	String	description
	Date	dueDate
	Boolean	isGroupPricing
	Contact	contact
	Currency	currency
	Double	discount
	Double	tax
	Boolean	isConfirmed
	Boolean	isGBCH
	String	noGBCH
	Date	dueDateGBCH
	Double	amountPaid
	Double	exchangeRateAmount
	Boolean	isBank
	Boolean	isFullPayment
	Boolean	isPaid
	CashBank	cashBank
	Warehouse	warehouse
	Double	total
	Double	cogs
	Date	dateCreated
	Boolean	isDeleted
	Date	lastUpdated
	Date	confirmationDate
	
	static hasMany = [retailPurchaseInvoiceDetails : RetailPurchaseInvoiceDetail]

    static constraints = {
		code(nullable:true)
		description(nullable:true)
		dueDate(nullable:true)
		noGBCH(nullable:true)
		dueDateGBCH(nullable:true)
		amountPaid(nullable:true)
		lastUpdated(nullable:true)
		confirmationDate(nullable:true)
    }
}
