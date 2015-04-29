package offsetprintingsuppliesgrails

class Contact {
	String	name
	String	address
	String	deliveryAddress
	String	npwp
	String	description
	String	contactNo
	String	pic
	String	picContactNo
	String	email
	Boolean	isTaxable
	String	taxCode
	Boolean	isDeleted
	Date	dateCreated
	Date	lastUpdated
	String	contactType // 'Customer', 'Supplier'
	Integer	defaultPaymentTerm
	String	namaFakturPajak
	ContactGroup	contactGroup
	
	static hasMany = [contactDetails: ContactDetail]
	

    static constraints = {
		name(nullable:true)
		address(nullable:true)
		deliveryAddress(nullable:true)
		npwp(nullable:true)
		description(nullable:true)
		contactNo(nullable:true)
		pic(nullable:true)
		picContactNo(nullable:true)
		email(nullable:true)
		taxCode(nullable:true)
		dateCreated(nullable:true)
		lastUpdated(nullable:true)
		contactType(nullable:true)
		namaFakturPajak(nullable:true)
		contactGroup(nullable:true)
		
    }
}
