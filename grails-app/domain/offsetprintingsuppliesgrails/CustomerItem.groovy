package offsetprintingsuppliesgrails

class CustomerItem {
	Contact	contact
	WarehouseItem	warehouseItem
	Double	quantity
	Double	virtual
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		warehouseItem(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
