package offsetprintingsuppliesgrails

class StockAdjustmentDetail {
	StockAdjustment	stockAdjustment
	Item	item
	String	code
	Double	quantity
	Double	price
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = StockAdjustment

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
