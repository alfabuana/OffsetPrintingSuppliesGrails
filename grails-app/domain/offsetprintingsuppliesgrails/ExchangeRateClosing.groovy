package offsetprintingsuppliesgrails

class ExchangeRateClosing {
	Closing	closing
	Currency	currency
	Double	rate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
