package offsetprintingsuppliesgrails

class DeliveryOrder {
	String	code
	SalesOrder	salesOrder
	Date	deliveryDate
	Warehouse	warehouse
	String	nomorSurat
	Double	totalCOGC
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isInvoiceCompleted
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	Double	exchangeRateAmount
	ExchangeRate	exchangeRate
	String	remark
	
	static hasMany = [deliveryOrderDetails : DeliveryOrderDetail]

    static constraints = {
		code(nullable:true)
		nomorSurat(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		exchangeRate(nullable:true)
		remark(nullable:true)
    }
}
