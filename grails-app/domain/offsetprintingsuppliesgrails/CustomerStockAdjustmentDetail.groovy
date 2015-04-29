package offsetprintingsuppliesgrails

class CustomerStockAdjustmentDetail {
	CustomerStockAdjustment	customerStockAdjustment
	Item	item
	String	code
	Integer	quantity
	Double	priceMutation
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = CustomerStockAdjustment

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		isDeleted(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
