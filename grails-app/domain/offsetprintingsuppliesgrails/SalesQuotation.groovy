package offsetprintingsuppliesgrails

class SalesQuotation {
	String	code
	String	versionNo
	String	nomorSurat
	Contact	contact
	Date	quotationDate
	Double	totalQuotedAmount
	Double	totalRrpAmount
	Double	costSaved
	Double	percentageSaved
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isApproved
	Boolean	isRejected
	Boolean	isSalesOrderConfirmed
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	String	catatan
	
	static hasMany = [salesQuotationDetails : SalesQuotationDetail]

    static constraints = {
		code(nullable:true)
		versionNo(nullable:true)
		nomorSurat(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		catatan(nullable:true)
    }
}
