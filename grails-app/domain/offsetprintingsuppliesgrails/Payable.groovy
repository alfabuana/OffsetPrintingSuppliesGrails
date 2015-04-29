package offsetprintingsuppliesgrails

class Payable {
	Contact	contact
	String	payableSource
	Integer	payableSourceId
	String	code
	Currency	currency
	Double	rate
	Date	dueDate
	Double	amount
	Double	remainingAmount
	Double	pendingClearanceAmount
	Boolean	isCompleted
	Date	completionDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		payableSource(nullable:true)
		code(nullable:true)
		dueDate(nullable:true)
		completionDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
