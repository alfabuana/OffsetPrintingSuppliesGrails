package offsetprintingsuppliesgrails

class PurchaseInvoiceMigration {
	String	nomorSurat
	Contact	contact
	Currency	currency
	Double	rate
	Double	amountPayable
	Double	dpp
	Double	tax
	Date	invoiceDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		nomorSurat(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
