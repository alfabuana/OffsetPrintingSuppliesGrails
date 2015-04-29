package offsetprintingsuppliesgrails

class Account {
	String	code
	String	name
	Integer	group // 'Asset','Expense','Liability','Equity','Revenue'
	Integer	level // 'Level 1','Level 2','Level 3','Level 4','Level 5'
	Integer	parentId
	Boolean	isLegacy
	Boolean	isLeaf
	Boolean	isCashBankAccount
	String	legacyCode
	Date	dateCreated
	Boolean	isDeleted
	Date	lastUpdated
	Integer	parseCode
	Boolean	isPayableReceivable
	
    static constraints = {
		code(nullable:true)
		name(nullable:true)
		parentId(nullable:true)
		legacyCode(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
