package offsetprintingsuppliesgrails

class WarehouseItem {
	Warehouse	warehouse
	Item	item
	Double	quantity
	Double	pendingDelivery
	Double	pendingReceival
	Double	customerQuantity
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
