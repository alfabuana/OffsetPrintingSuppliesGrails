package offsetprintingsuppliesgrails

class RollerWarehouseMutation {
	RecoveryOrder	recoveryOrder
	String	code
	Warehouse	warehouseFrom
	Warehouse	warehouseTold
	Date	mutationDate
	Integer	quantity
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static hasMany = [rollerWarehouseMutationDetails : RollerWarehouseMutationDetail]
	

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
