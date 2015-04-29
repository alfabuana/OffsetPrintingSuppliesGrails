package offsetprintingsuppliesgrails

class CashBank {
	String	name
	String	description
	Currency	currency
	Double	amount
	Boolean	isBank
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	String	code
	

    static constraints = {
		name(nullable:true)
		description(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		code
		
    }
}
