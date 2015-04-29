package offsetprintingsuppliesgrails

class RecoveryAccessoryDetail {
	RecoveryOrderDetail	recoveryOrderDetail
	Item	item
	Integer	quantity
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
