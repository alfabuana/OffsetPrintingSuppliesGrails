package offsetprintingsuppliesgrails

class PurchaseOrder {
	String	code
	Contact	contact
	Date	purchaseDate
	String	nomorSurat
	Currency	currency
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isReceivalCompleted
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	String	description
	Boolean	allowEditDetail
	
	static hasMany = [purchaseOrderDetails : PurchaseOrderDetail]
	
    static constraints = {
		code(nullable:true)
		nomorSurat(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		description(nullable:true)
		
    }
}
