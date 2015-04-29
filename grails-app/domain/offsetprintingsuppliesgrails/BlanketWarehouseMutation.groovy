package offsetprintingsuppliesgrails

class BlanketWarehouseMutation {
	BlanketOrder	blanketOrder
	String	code
	Warehouse	warehouseFrom
	Warehouse	warehouseTold
	Date	mutationDate
	Double	quantity
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated

	static hasMany = [blanketWarehouseMutationDetails : BlanketWarehouseMutationDetail]
	
    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
