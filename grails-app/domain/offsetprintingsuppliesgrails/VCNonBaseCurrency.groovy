package offsetprintingsuppliesgrails

class VCNonBaseCurrency {
	ValidComb	validComb
	Double	amount
	Date	dateCreated
	Date	lastUpdated
	Boolean	isDeleted
	

    static constraints = {
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
