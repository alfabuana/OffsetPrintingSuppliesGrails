package offsetprintingsuppliesgrails

class WarehouseMutation {
	String	code
	Warehouse	warehouseFrom
	Warehouse	warehouseTold
	Date	mutationDate
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated

	static hasMany = [warehouseMutationDetails : WarehouseMutationDetail]
	
    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
