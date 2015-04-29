package offsetprintingsuppliesgrails

class PurchaseInvoice {
	String	description
	String	code
	String	nomorSurat
	Currency	currency
	Double	exchangeRateAmount
	ExchangeRate	exchangeRate
	Double	amountPayable
	Double	discount
	Boolean	isTaxable
	Double	tax
	Date	invoiceDate
	Date	dueDate
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated

	static hasMany = [purchaseInvoiceDetails : PurchaseInvoiceDetail]	

    static constraints = {
		description(nullable:true)
		code(nullable:true)
		nomorSurat(nullable:true)
		exchangeRate(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
