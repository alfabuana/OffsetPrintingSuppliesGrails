package offsetprintingsuppliesgrails

class PurchaseReceival {
	String	code
	PurchaseOrder	purchaseOrder
	Date	receivalDate
	Warehouse	warehouse
	String	nomorSurat
	Double	exchangeRateAmount
	ExchangeRate	exchangeRate
	Double	totalCOGS
	Double	totalAmount
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isInvoiceCompleted
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	String	description
	
	static hasMany = [purchaseReceivalDetails : PurchaseReceivalDetail]

    static constraints = {
		code(nullable:true)
		nomorSurat(nullable:true)
		exchangeRate(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		description(nullable:true)
    }
}
