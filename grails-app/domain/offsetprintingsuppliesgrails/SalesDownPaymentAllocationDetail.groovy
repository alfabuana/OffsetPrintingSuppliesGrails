package offsetprintingsuppliesgrails

class SalesDownPaymentAllocationDetail {
	SalesDownPaymentAllocation	salesDownPaymentAllocation
	Receivable	receivable
	String	code
	Double	amount
	Double	amountPaid
	Double	rate
	String	description
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = SalesDownPaymentAllocation
	
    static constraints = {
		code(nullable:true)
		description(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
