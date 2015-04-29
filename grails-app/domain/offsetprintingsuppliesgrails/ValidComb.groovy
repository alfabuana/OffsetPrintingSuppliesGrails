package offsetprintingsuppliesgrails

class ValidComb {
	Account	account
	Closing	closing
	Double	amount
	Date	dateCreated
	Date	lastUpdated
	Boolean	isDeleted
	

    static constraints = {
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
