package offsetprintingsuppliesgrails

class CustomerStockAdjustment {
	Warehouse	warehouse
	Contact	contact
	Date	adjustmentDate
	String	description
	String	code
	Double	total
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static hasMany = [customerStockAdjustmentDetails : CustomerStockAdjustmentDetail]

    static constraints = {
		description(nullable:true)
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
