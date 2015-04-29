package offsetprintingsuppliesgrails

class PaymentVoucher {
	Contact	contact
	CashBank	cashBank
	String	code
	Date	paymentDate
	Boolean	isGBCH
	Date	dueDate
	Boolean	isReconciled
	Date	reconciliationDate
	Double	rateToIDR
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
	Double	totalPph21
	String	noGBCH
	
	static hasMany = [paymentVoucherDetails : PaymentVoucherDetail]
	

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
