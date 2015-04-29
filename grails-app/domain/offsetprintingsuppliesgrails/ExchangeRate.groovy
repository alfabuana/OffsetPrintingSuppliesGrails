package offsetprintingsuppliesgrails

class ExchangeRate {
	Currency	currency
	Date	exRateDate
	Double	rate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
