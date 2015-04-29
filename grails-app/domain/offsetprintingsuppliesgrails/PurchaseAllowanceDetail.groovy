package offsetprintingsuppliesgrails

class PurchaseAllowanceDetail {
	PurchaseAllowance	purchaseAllowance
	Payable	payable
	String	code
	Double	amount
	String	description
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = PurchaseAllowance

    static constraints = {
		code(nullable:true)
		description(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
