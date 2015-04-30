package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class CustomerItemValidationService {

    def serviceMethod() {

    }
	def createObjectValidation(def object)
	{
		return object
	}
	def updateObjectValidation(def object)
	{
		return object
	}
	def softdeleteObjectValidation(object)
	{
		return object
	}
}
