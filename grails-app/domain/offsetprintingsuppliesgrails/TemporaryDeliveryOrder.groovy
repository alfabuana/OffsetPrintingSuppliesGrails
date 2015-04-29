package offsetprintingsuppliesgrails

class TemporaryDeliveryOrder {
	String	code
	Integer	orderType // 'Trial Order','Sample Order','Consignment'
	VirtualOrder	virtualOrder
	DeliveryOrder	deliveryOrder
	Date	deliveryDate
	Warehouse	warehouse
	String	nomorSurat
	Double	totalWasteCOGS
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeliveryCompleted
	Boolean	isReconciled
	Date	pushDate
	Boolean	isPushed
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static hasMany = [temporaryDeliveryOrderDetails : TemporaryDeliveryOrderDetail]

    static constraints = {
		code(nullable:true)
		virtualOrder(nullable:true)
		deliveryOrder(nullable:true)
		warehouse(nullable:true)
		nomorSurat(nullable:true)
		confirmationDate(nullable:true)
		pushDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
