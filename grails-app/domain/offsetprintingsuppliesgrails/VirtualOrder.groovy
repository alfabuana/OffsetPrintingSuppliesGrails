package offsetprintingsuppliesgrails

class VirtualOrder {
	String	code
	Contact	contact
	Integer	orderType // 'Trial Order','Sample Order','Consignment'
	Date	orderDate
	String	nomorSurat
	Currency	currency
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeliveryCompleted
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static hasMany = [virtualOrderDetails : VirtualOrderDetail]

    static constraints = {
		code(nullable : true)
		nomorSurat(nullable : true)
		confirmationDate(nullable : true)
		dateCreated(nullable : true)
		lastUpdated(nullable : true)
		
    }
}
