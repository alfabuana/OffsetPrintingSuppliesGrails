package offsetprintingsuppliesgrails

class WarehouseMutationDetail {
	WarehouseMutation	warehouseMutation
	String	code
	Item	item
	Integer	quantity
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = WarehouseMutation

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
