package offsetprintingsuppliesgrails

class SalesInvoiceDetail {
	SalesInvoice	SalesInvoice
	DeliveryOrderDetail	deliveryOrderDetail
	String	code
	Double	quantity
	Double	cos
	Double	amount
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = SalesInvoice

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
