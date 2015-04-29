package offsetprintingsuppliesgrails

class StockMutation {
	Item	item
	Warehouse	warehouse
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
		warehouse(nullable:true)
		warehouseItem(nullable:true)
		sourceDocumentType(nullable:true)
		sourceDocumentDetailType(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
