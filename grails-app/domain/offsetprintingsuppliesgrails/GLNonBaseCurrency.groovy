package offsetprintingsuppliesgrails

class GLNonBaseCurrency {
	GeneralLedgerJournal	generalLedgerJournal
	Currency	currency
	Double	amount
	Date	dateCreated
	Boolean	isDeleted
	Date	lastUpdated
	

    static constraints = {
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
