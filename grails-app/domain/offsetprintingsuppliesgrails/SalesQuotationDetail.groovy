package offsetprintingsuppliesgrails

class SalesQuotationDetail {
	String	code
	SalesQuotation	salesQuotation
	Item	item
	Double	quantity
	Double	rrp
	Double	quotationPrice
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = SalesQuotationDetail

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
