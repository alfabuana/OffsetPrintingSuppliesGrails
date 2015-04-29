package offsetprintingsuppliesgrails

class CustomerStockMutation {
	Item	item
	Contact	contact
	CustomerItem	customerItem
	WarehouseItem	warehouseItem
	Integer	itemCase
	Integer	status
	String	sourceDocumentType
	String	sourceDocumentDetailType
	Integer	sourceDocumentId
	Integer	sourceDocumentDetailId
	Date	mutationDate
	Double	quantity
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		contact(nullable:true)
		customerItem(nullable:true)
		warehouseItem(nullable:true)
		sourceDocumentType(nullable:true)
		sourceDocumentDetailType(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
