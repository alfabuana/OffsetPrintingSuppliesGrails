package offsetprintingsuppliesgrails

class PurchaseDownPaymentAllocationDetail {
	PurchaseDownPaymentAllocation	purchaseDownPaymentAllocation
	Payable	payable
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
	
	static belongsTo = PurchaseDownPaymentAllocation

    static constraints = {
		code(nullable:true)
		description(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
