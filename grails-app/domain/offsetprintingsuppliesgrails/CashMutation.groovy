package offsetprintingsuppliesgrails

class CashMutation {
	CashBank	cashBank
	Integer	status
	String	sourceDocumentType
	Integer	sourceDocumentId
	String	sourceDocumentCode
	Double	amount
	Date	mutationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		sourceDocumentType(nullable:true)
		sourceDocumentCode(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
