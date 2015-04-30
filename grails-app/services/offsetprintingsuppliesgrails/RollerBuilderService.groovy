package offsetprintingsuppliesgrails

import grails.transaction.Transactional

@Transactional
class RollerBuilderService {
RollerBuilderValidationService rollerBuilderValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return RollerBuilder.get(object)
		}
		def getList(){
			return RollerBuilder.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isCrowning = false
			object.isChamfer = false
			object.machine = Machine.find{id == object.machineId}
			object.rollerType = RollerType.find{id == object.rollerTypeId}
			object.compound = Compound.find{id == object.compoundId}
			object.coreBuilder = CoreBuilder.find{id == object.coreBuilderId}
			object.rollerUsedCoreItem = Item.find{id == object.itemId}
			object.rollerNewCoreItem = Item.find{id == object.itemId}
			object.adhesive = Item.find{id == object.itemId}
			object.uoM = UoM.find{id == object.uoMId}
			object = rollerBuilderValidationService.createObjectValidation(object as RollerBuilder)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = RollerBuilder.read(object.id)
			valObject.machine = Machine.find{id == object.machineId}
			valObject.rollerType = RollerType.find{id == object.rollerTypeId}
			valObject.compound = Compound.find{id == object.compoundId}
			valObject.coreBuilder = CoreBuilder.find{id == object.coreBuilderId}
			valObject.baseSku = object.baseSku
			valObject.skuRollerUsedCore = object.skuRollerUsedCore
			valObject.skuRollerNewCore = object.skuRollerNewCore
			valObject.rollerUsedCoreItem = Item.find{id == object.itemId}
			valObject.rollerNewCoreItem = Item.find{id == object.itemId}
			valObject.adhesive = Item.find{id == object.itemId}
			valObject.uoM = UoM.find{id == object.uoMId}
			valObject.name = object.name
			valObject.description = object.description
			
			if (NumberUtils.isNumber(object.rd) ==  true)
			{
				valObject.rd = Double.parseDouble(rd)
			}
			else
			{
				valObject.rd = null
			}
			if (NumberUtils.isNumber(object.cd) ==  true)
			{
				valObject.cd = Double.parseDouble(cd)
			}
			else
			{
				valObject.cd = null
			}
			if (NumberUtils.isNumber(object.rl) ==  true)
			{
				valObject.rl = Double.parseDouble(rl)
			}
			else
			{
				valObject.rl = null
			}
			if (NumberUtils.isNumber(object.wl) ==  true)
			{
				valObject.wl = Double.parseDouble(wl)
			}
			else
			{
				valObject.wl = null
			}
			if (NumberUtils.isNumber(object.tl) ==  true)
			{
				valObject.tl = Double.parseDouble(tl)
			}
			else
			{
				valObject.tl = null
			}
			if (NumberUtils.isNumber(object.crowningSize) ==  true)
			{
				valObject.crowningSize = Integer.parseInt(crowningSize)
			}
			else
			{
				valObject.crowningSize = null
			}
			if (NumberUtils.isNumber(object.groovingWidth) ==  true)
			{
				valObject.groovingWidth = Integer.parseInt(groovingWidth)
			}
			else
			{
				valObject.groovingWidth = null
			}
			if (NumberUtils.isNumber(object.groovingDepth) ==  true)
			{
				valObject.groovingDepth = Integer.parseInt(groovingDepth)
			}
			else
			{
				valObject.groovingDepth = null
			}
			if (NumberUtils.isNumber(object.groovingPosition) ==  true)
			{
				valObject.groovingPosition = Integer.parseInt(groovingPosition)
			}
			else
			{
				valObject.groovingPosition = null
			}
			valObject = rollerBuilderValidationService.updateObjectValidation(valObject)
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
			def newObject = RollerBuilder.get(object.id)
			newObject = rollerBuilderValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
}
