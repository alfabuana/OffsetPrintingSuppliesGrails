package offsetprintingsuppliesgrails

class SalesDownPaymentAllocation {
	Contact	contact
	Payable	payable
	String	code
	Date	allocationDate
	Double	totalAmount
	Double	rateToIDR
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated

	static hasMany = [salesDownPaymentAllocationDetails : SalesDownPaymentAllocation]
	
    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
