package offsetprintingsuppliesgrails

class RecoveryOrderDetail {
	RecoveryOrder	recoveryOrder
	CoreIdentificationDetail	coreIdentificationDetail
	RollerBuilder	rollerBuilder
	Double	totalCost
	Double	compoundUsage
	String	coreTypeCase
	Boolean	isDisassembled
	Boolean	isStrippedAndGlued
	Boolean	isWrapped
	Boolean	isVulcanized
	Boolean	isFacedOff
	Boolean	isConventionalGrinded
	Boolean	isCNCGrinded
	Boolean	isPolishedAndQC
	Boolean	isPackaged
	Boolean	isRejected
	Date	rejectedDate
	Boolean	isFinished
	Date	finishedDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	Double	accessoriesCost
	Double	coreCost
	Double	compoundCost
	Item	compoundUnderLayer
	Double	compoundUnderLayerUsage
	
	static belongsTo = RecoveryOrder

    static constraints = {
		coreTypeCase(nullable:true)
		rejectedDate(nullable:true)
		finishedDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		compoundUnderLayer(nullable:true)
    }
}
