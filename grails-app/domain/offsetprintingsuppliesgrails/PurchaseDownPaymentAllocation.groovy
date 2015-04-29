package offsetprintingsuppliesgrails

class PurchaseDownPaymentAllocation {
	Contact	contact
	Receivable	receivable
	String	code
	Date	allocationDate
	Double	totalAmount
	Double	rateToIDR
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static hasMany = [purchaseDownPaymentAllocationDetails : PurchaseDownPaymentAllocationDetail]

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
