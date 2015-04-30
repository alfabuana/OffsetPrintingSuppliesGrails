package offsetprintingsuppliesgrails

import grails.transaction.Transactional


@Transactional
class ItemService {
	ItemValidationService itemValidationService
	
		def serviceMethod() {
	
		}
		
		def getObjectById(def object){
			return Item.get(object)
		}
		def getList(){
			return Item.findAll([sort: "id", order: "desc"]){}
		}
		
		def createObject(object){
			object.isDeleted = false
			object.isTradeable = false
			object.itemType = ItemType.find{id == object.itemTypeId}
			object.uoM = UoM.find{id == object.uoMId}
			object.currency = Currency.find{id == object.currencyId}
			object.priceMutation = PriceMutation.find{id == object.priceMutationId}
			object.subType = SubType.find{id == object.subTypeId}
			object = itemValidationService.createObjectValidation(object as Item)
			if (object.errors.getErrorCount() == 0)
			{
				object = object.save()
			}
			return object
		}
		def updateObject(def object){
			def valObject = Item.read(object.id)
			valObject.itemType = ItemType.find{id == object.itemTypeId}
			valObject.sku = object.sku
			valObject.name = object.name
			valObject.description = object.description
			valObject.uoM = UoM.find{id == object.uoMId}
			if (NumberUtils.isNumber(object.quantity) ==  true)
			{
				valObject.quantity = Double.parseDouble(object.quantity)
			}
			else
			{
				valObject.quantity = null
			}
			if (NumberUtils.isNumber(object.pendingDelivery) ==  true)
			{
				valObject.pendingDelivery = Double.parseDouble(object.pendingDelivery)
			}
			else
			{
				valObject.pendingDelivery = null
			}
			if (NumberUtils.isNumber(object.pendingReceival) ==  true)
			{
				valObject.pendingReceival = Double.parseDouble(object.pendingReceival)
			}
			else
			{
				valObject.pendingReceival = null
			}
			if (NumberUtils.isNumber(object.virtual) ==  true)
			{
				valObject.virtual = Double.parseDouble(object.virtual)
			}
			else
			{
				valObject.virtual = null
			}
			if (NumberUtils.isNumber(object.minimumQuantity) ==  true)
			{
				valObject.minimumQuantity = Double.parseDouble(object.minimumQuantity)
			}
			else
			{
				valObject.minimumQuantity = null
			}
			if (NumberUtils.isNumber(object.customerQuantity) ==  true)
			{
				valObject.customerQuantity = Double.parseDouble(object.customerQuantity)
			}
			else
			{
				valObject.customerQuantity = null
			}
			if (NumberUtils.isNumber(object.customerVirtual) ==  true)
			{
				valObject.customerVirtual = Double.parseDouble(object.customerVirtual)
			}
			else
			{
				valObject.customerVirtual = null
			}
			if (NumberUtils.isNumber(object.sellingPrice) ==  true)
			{
				valObject.sellingPrice = Double.parseDouble(object.sellingPrice)
			}
			else
			{
				valObject.sellingPrice = null
			}
			valObject.currency = Currency.find{id == object.currencyId}
			valObject.priceMutation = PriceMutation.find{id == object.priceMutationId}
			if (NumberUtils.isNumber(object.avgPrice) ==  true)
			{
				valObject.avgPrice = Double.parseDouble(object.avgPrice)
			}
			else
			{
				valObject.avgPrice = null
			}
			if (NumberUtils.isNumber(object.customerAvgPrice) ==  true)
			{
				valObject.customerAvgPrice = Double.parseDouble(object.customerAvgPrice)
			}
			else
			{
				valObject.customerAvgPrice = null
			}
			if (NumberUtils.isNumber(object.priceList) ==  true)
			{
				valObject.priceList = Double.parseDouble(object.priceList)
			}
			else
			{
				valObject.priceList = null
			}
			valObject.subType = SubType.find{id == object.subTypeId}
			valObject = itemValidationService.updateObjectValidation(valObject)
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
			def newObject = Item.get(object.id)
			newObject = itemValidationService.softdeleteObjectValidation(newObject)
			if (newObject.errors.getErrorCount() == 0)
			{
				newObject.isDeleted = true
				newObject.save()
			}
			return newObject
		}
}
