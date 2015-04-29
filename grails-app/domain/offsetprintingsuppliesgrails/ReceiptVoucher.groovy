package offsetprintingsuppliesgrails

class ReceiptVoucher {
	Contact	contact
	CashBank	cashBank
	String	code
	Date	receiptDate
	Double	rateToIDR
	Boolean	isGBCH
	Date	dueDate
	Boolean	isReconciled
	Date	reconciliationDate
	Double	totalAmount
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	String	noBukti
	Double	totalPph23
	Double	biayaBank
	Double	pembulatan
	Integer	statusPembulatan // 'Debit','Kredit'
	String	noGBCH
	
	static hasMany = [receiptVoucherDetails : ReceiptVoucherDetail]

    static constraints = {
		code(nullable:true)
		dueDate(nullable:true)
		reconciliationDate(nullable:true)
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		noBukti(nullable:true)
		noGBCH(nullable:true)
    }
}
