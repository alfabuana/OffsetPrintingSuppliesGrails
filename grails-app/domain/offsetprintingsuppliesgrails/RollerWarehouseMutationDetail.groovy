package offsetprintingsuppliesgrails

class RollerWarehouseMutationDetail {
	RollerWarehouseMutation	rollerWarehouseMutation
	RecoveryOrderDetail	recoveryOrderDetail
	String	code
	Item	item
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = RollerWarehouseMutation

    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
