package offsetprintingsuppliesgrails

class BlanketOrderDetail {
	BlanketOrder	blanketOrder
	Blanket	blanket
	Double	totalCost
	Boolean	isCut
	Boolean	isSideSealed
	Boolean	isBarPrepared
	Boolean	isAdhesiveTapeApplied
	Boolean	isBarMounted
	Boolean	isBarHeatPressed
	Boolean	isBarPullOffTested
	Boolean	isQCAndMarked
	Boolean	isPackaged
	Boolean	isRejected
	Date	rejectedDate
	Boolean	isJobScheduled
	Boolean	isFinished
	Date	finishedDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	Double	barCost
	Double	adhesiveCost
	Double	rollBlanketCost
	Double	rollBlanketUsage
	Double	rollBlanketDefect
	
	static belongsTo = BlanketOrder

    static constraints = {
		rejectedDate(nullable:true)
		finishedDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
    }
}
