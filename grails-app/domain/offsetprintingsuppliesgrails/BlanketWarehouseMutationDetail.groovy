package offsetprintingsuppliesgrails

class BlanketWarehouseMutationDetail {
	BlanketWarehouseMutation	blanketWarehouseMutation
	BlanketOrderDetail	blanketOrderDetail
	String	code
	Item	item
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = BlanketWarehouseMutation
	
    static constraints = {
		code(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
