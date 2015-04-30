package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class CoreBuilderService {
	CoreBuilderValidationService coreBuilderValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return CoreBuilder.get(object)
		}
		def getList(){
			return CoreBuilder.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.usedCoreItem = Item.find{id == object.itemId}
			object.newCoreItem = Item.find{id == object.itemId}
			object.uoM = UoM.find{id == object.uoMId}
			object.machine = Machine.find{id == object.machineId}
			object = coreBuilderValidationService.createObjectValidation(object as CoreBuilder)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = CoreBuilder.read(object.id)
			valObject.baseSku = object.baseSku
			valObject.skuUsedCore = object.skuUsedCore
			valObject.skuNewCore = object.skuNewCore
			valObject.usedCoreItem = Item.find{id == object.itemId}
			valObject.newCoreItem = Item.find{id == object.itemId}
			valObject.uoM = UoM.find{id == object.uoMId}
			valObject.machine = Machine.find{id == object.machineId}
			valObject.coreBuilderTypeCase = object.coreBuilderTypeCase
			valObject.name = object.name
			valObject.description = object.description
			if (NumberUtils.isNumber(object.cd) ==  true)
			{
				valObject.cd = Double.parseDouble(object.cd)
			}
			else
			{
				valObject.cd = null
			}
			if (NumberUtils.isNumber(object.tl) ==  true)
			{
				valObject.tl = Double.parseDouble(object.tl)
			}
			else
			{
				valObject.tl = null
			}
			valObject = coreBuilderValidationService.updateObjectValidation(valObject)
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
			def newObject = CoreBuilder.get(object.id)
			newObject = coreBuilderValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
}
