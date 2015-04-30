package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class BlanketService {
BlanketValidationService blanketValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return Blanket.get(object)
		}
		def getList(){
			return Blanket.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isBarRequired = false
			object.hasLeftBar = false
			object.hasRightBar = false
			object.contact = Contact.find{id == object.contactId}
			object.machine = Machine.find{id == object.machineId}
			object.adhesive = Item.find{id == object.itemId}
			object.adhesive2 = Item.find{id == object.itemId}
			object.rollBlanketItem = Item.find{id == object.itemId}
			object.leftBarItem = Item.find{id == object.itemId}
			object.rightBarItem = Item.find{id == object.itemId}
			object = blanketValidationService.createObjectValidation(object as Blanket)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = Blanket.read(object.id)
			valObject.rollNo = object.rollNo
			valObject.contact = Contact.find{id == object.contactId}
			valObject.machine = Machine.find{id == object.machineId}
			valObject.adhesive = Item.find{id == object.itemId}
			valObject.adhesive2 = Item.find{id == object.itemId}
			valObject.rollBlanketItem = Item.find{id == object.itemId}
			valObject.leftBarItem = Item.find{id == object.itemId}
			valObject.rightBarItem = Item.find{id == object.itemId}
			if (NumberUtils.isNumber(object.ac) ==  true)
			{
				valObject.ac = Double.parseDouble(object.ac)
			}
			else
			{
				valObject.ac = null
			}
			if (NumberUtils.isNumber(object.ar) ==  true)
			{
				valObject.ar = Double.parseDouble(object.ar)
			}
			else
			{
				valObject.ar = null
			}
			if (NumberUtils.isNumber(object.thickness) ==  true)
			{
				valObject.thickness = Double.parseDouble(object.thickness)
			}
			else
			{
				valObject.thickness = null
			}
			if (NumberUtils.isNumber(object.ks) ==  true)
			{
				valObject.ks = Double.parseDouble(object.ks)
			}
			else
			{
				valObject.ks = null
			}
			valObject.croppingType = object.croppingType
			if (NumberUtils.isNumber(object.leftOverAc) ==  true)
			{
				valObject.leftOverAc = Double.parseDouble(object.leftOverAc)
			}
			else
			{
				valObject.leftOverAc = null
			}
			if (NumberUtils.isNumber(object.leftOverAr) ==  true)
			{
				valObject.leftOverAr = Double.parseDouble(object.leftOverAr)
			}
			else
			{
				valObject.leftOverAr = null
			}
			if (NumberUtils.isNumber(object.special) ==  true)
			{
				valObject.special = Double.parseDouble(object.special)
			}
			else
			{
				valObject.special = null
			}
			valObject.applicationCase = object.applicationCase
			valObject = blanketValidationService.updateObjectValidation(valObject)
			if (valObject.errors.getErrorCount() == 0)
			{
				valObject.save()
			}
			else
			{
				valObject.discard()
			}
			return valObject
		}
		def softDeletedObject(def object){
			def newObject = Blanket.get(object.id)
			newObject = blanketValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
}
