package offsetprintingsuppliesgrails

class SalesInvoice {
	DeliveryOrder	deliveryOrder
	String	description
	String	code
	String	nomorSurat
	Currency	currency
	Double	exchangeRateAmount
	Double	totalCOS
	Double	amountReceivable
	Double	discount
	Double	tax
	ExchangeRate	exchangeRate
	Date	invoiceDate
	Date	dueDate
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	Double	dpp
	
	static hasMany = [salesInvoiceDetails : SalesInvoiceDetail]

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
