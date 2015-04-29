package offsetprintingsuppliesgrails

class GeneralLedgerJournal {
	Account	account
	String	sourceDocument
	Integer	sourceDocumentId
	Integer	status
	Date	transactionDate
	Double	amount
	Date	dateCreated
	Boolean	isDeleted
	String	description
	Date	lastUpdated
	

    static constraints = {
		sourceDocument(nullable:true)
		dateCreated(nullable:true)
		description(nullable:true)
		lastUpdated(nullable:true)
    }
}
