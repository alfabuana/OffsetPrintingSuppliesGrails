package offsetprintingsuppliesgrails

class Item {
	ItemType	itemType
	String	sku
	String	name
	String	description
	Boolean	isTradeable
	UoM	uoM
	Double	quantity
	Double	pendingDelivery
	Double	pendingReceival
	Double	virtual
	Double	minimumQuantity
	Double	customerQuantity
	Double	customerVirtual
	Double	sellingPrice
	Currency	currency
	PriceMutation	priceMutation
	Double	avgPrice
	Double	customerAvgPrice
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	Double	priceList
	SubType	subType
	

    static constraints = {
		sku(nullable:true)
		name(nullable:true)
		description(nullable:true)
		currency(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		subType(nullable:true)
    }
}
