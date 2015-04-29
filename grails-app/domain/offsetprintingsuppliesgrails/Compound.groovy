package offsetprintingsuppliesgrails

class Compound {
	String	compoundType
	String	batchNo
	Date	expiryDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		compoundType(nullable:true)
		batchNo(nullable:true)
		
    }
}
