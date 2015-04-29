package offsetprintingsuppliesgrails

class CoreAccessoryDetail {
	CoreIdentificationDetail coreIdentificationDetail
	Item	item
	Integer	quantity
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	
	static belongsTo = CoreIdentificationDetail
	
    static constraints = {
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		
    }
}
