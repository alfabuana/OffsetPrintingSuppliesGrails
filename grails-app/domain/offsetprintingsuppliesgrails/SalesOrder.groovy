package offsetprintingsuppliesgrails

class SalesOrder {
	String	code
	Integer	orderType
	String	orderCode
	Contact	contact
	Date	salesDate
	String	nomorSurat
	Currency	currency
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeliveryCompleted
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	Employee	employee
	
	static hasMany = [salesOrderDetails : SalesOrderDetail]

    static constraints = {
		code(nullable:true)
		orderCode(nullable:true)
		nomorSurat(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		employee(nullable:true)
		
    }
}
