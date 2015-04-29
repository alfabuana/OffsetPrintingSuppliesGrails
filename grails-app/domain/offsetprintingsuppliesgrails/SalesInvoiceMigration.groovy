package offsetprintingsuppliesgrails

class SalesInvoiceMigration {
	String	nomorSurat
	Contact	contact
	Currency	currency
	Double	rate
	Double	amountReceivable
	Double	tax
	Double	dpp
	Date	invoiceDate
	Date	dateCreated
	Date	lastUpdated
	

    static constraints = {
		nomorSurat(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
