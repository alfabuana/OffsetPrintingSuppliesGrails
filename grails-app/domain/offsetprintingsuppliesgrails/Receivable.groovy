package offsetprintingsuppliesgrails

class Receivable {
	Contact	contact
	String	receivableSource
	Integer	receivableSourceId
	String	code
	Integer	currency
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
		receivableSource(nullable:true)
		code(nullable:true)
		dueDate(nullable:true)
		completionDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
