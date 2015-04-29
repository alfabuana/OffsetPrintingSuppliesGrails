package offsetprintingsuppliesgrails

class CoreIdentificationDetail {
	CoreIdentification coreIdentification
	Integer	detailId
	Integer	materialCase
	CoreBuilder	coreBuilder
	RollerType	rollerType
	Machine	machine
	Integer	repairRequestCase
	Double	rd
	Double	cd
	Double	rl
	Double	wl
	Double	tl
	Boolean	isJobScheduled
	Boolean	isDelivered
	Boolean	isRollerBuilt
	Boolean	isConfirmed
	Date	confirmationDate
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	String	rollerNo
	Double	gl
	Double	grooveLength
	Double	grooveQty
	
	static hasMany = [coreAccessoryDetails : CoreAccessoryDetail]
	
	static belongsTo = CoreIdentification
	

    static constraints = {
		confirmationDate(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		rollerNo(nullable:true)
		
    }
}
