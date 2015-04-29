package offsetprintingsuppliesgrails

class SalesAllowance {
	Contact	contact
	CashBank	cashBank
	String	code
	Date	allowanceAllocationDate
	Boolean	isGBCH
	Date	dueDate
	Boolean	isReconciled
	Date	reconciliationDate
	Double	totalAmount
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static hasMany = [salesAllowanceDetails : SalesAllowanceDetail]

    static constraints = {
		code(nullable:true)
		dueDate(nullable:true)
		reconciliationDate(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
