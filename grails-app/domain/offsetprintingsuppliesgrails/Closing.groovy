package offsetprintingsuppliesgrails

class Closing {
	Integer	period
	Integer	yearPeriod
	Date	beginningPeriod
	Date	endDatePeriod
	Boolean	isYear
	Boolean	isClosed
	Date	closedDate
	Date	dateCreated
	Boolean	isDeleted
	Date	lastUpdated
	

    static constraints = {
		closedDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		isDeleted(nullable:true)
		
    }
}
