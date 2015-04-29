package offsetprintingsuppliesgrails

class UserAccess {
	UserMenu	userMenu
	UserAccount	userAccount
	Boolean	allowView
	Boolean	allowCreate
	Boolean	allowEdit
	Boolean	allowDelete
	Boolean	allowConfirm
	Boolean	allowUnConfirm
	Boolean	allowPaid
	Boolean	allowUnpaid
	Boolean	allowReconcile
	Boolean	allowUnreconcile
	Boolean	allowProcess
	Boolean	allowPrint
	Boolean	allowUndelete
	Boolean	allowSpecialPricing
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
