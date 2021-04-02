function inv05LoadItem(rowItem, data) {
	var itId = rowItem.find('.tableItem-itItemId');
	var itCode = rowItem.find('.tableItem-itItemCode');
	var itName = rowItem.find('.tableItem-itItemName');
	var itBrand = rowItem.find('.tableItem-itBrand');
	var itSkuId = rowItem.find('.tableItem-itSkuId');
	var itLot = rowItem.find('.tableItem-itLot');
	var itExpireDate = rowItem.find('.tableItem-itExpireDate');
	var itModel = rowItem.find('.tableItem-itModel');
	var itSerial = rowItem.find('.tableItem-itSerial');
	var itUnitId = rowItem.find('.tableItem-itUnitId');
	var itUnitName = rowItem.find('.tableItem-itUnitName');
	var itSourceQty = rowItem.find('.tableItem-itSourceQty');
	var itSourceWarehouseId = rowItem.find('.tableItem-itItemSourceWarehouseId');
	var itSourceWarehouseName = rowItem.find('.tableItem-itItemSourceWarehouseName');
	var itSourceLocationCode = rowItem.find('.tableItem-itSourceLocationCode');
	var itSourceLocationId = rowItem.find('.tableItem-itSourceLocationId');

	// Get data auto
	var itemId = data.value;
	var itemCode = data.code;
	var itemName = data.name;
	var itemBrand = data.brandName;
	var itemSkuId = data.skuId;
	var itemLot = data.lot;
	var itemExpireDate = data.expireDate;
	var itemModel = data.model;
	var itemSerial = data.serial;
	var itemUnitId = data.unitId;
	var itemUnitName = data.unitName;
	var itemSourceQty = data.stockQty;
	var itemSourceWarehouseId = data.warehouseId;
	var itemSourceWarehouseName = data.warehouseName;
	var itemLocationCode = data.locationCode;
	var itemLocationId = data.locationId;
	var itemLocationName = data.locationName;
	var itemLoacationNameFull = data.locationNameFull;

	setValue(itId, itemId);
	setValue(itCode, itemCode);
	setValue(itName, itemName);
	setValue(itBrand, itemBrand);
	setValue(itSkuId, itemSkuId);
	setValue(itLot, itemLot);
	setValue(itExpireDate, itemExpireDate);
	setValue(itModel, itemModel);
	setValue(itSerial, itemSerial);
	setValue(itUnitId, itemUnitId);
	setValue(itUnitName, itemUnitName);
	setValue(itSourceQty, itemSourceQty);
	setValue(itSourceWarehouseId, itemSourceWarehouseId);
	setValue(itSourceWarehouseName, itemSourceWarehouseName);
	setValue(itSourceLocationCode, itemLocationCode);
	setValue(itSourceLocationId, itemLocationId);

	inv05RowAmount(rowItem);
}

function inv05ChangeQty(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}

	var itQty = rowItem.find('.tableItem-itSourceQty');
	var qty = toNumber(getValue(itQty));

	var pos = itQty.getCursorPosition();
	var len = getValue(itQty).length;

	setValue(itQty, formatNumber(qty));

	inv05RowAmount(rowItem);

	qty = getValue(itQty);
	pos = getCursorPositionNew(e, pos, len, qty.length, qty);
	itQty.setCursorPosition(pos);
}

function inv05ChangeSku(rowItem, data) {
	var itId = rowItem.find('.tableItem-itItemId');
	var itCode = rowItem.find('.tableItem-itItemCode');
	var itName = rowItem.find('.tableItem-itItemName');
	var itBrand = rowItem.find('.tableItem-itBrand');
	var itSkuId = rowItem.find('.tableItem-itSkuId');
	var itLot = rowItem.find('.tableItem-itLot');
	var itExpireDate = rowItem.find('.tableItem-itExpireDate');
	var itModel = rowItem.find('.tableItem-itModel');
	var itSerial = rowItem.find('.tableItem-itSerial');
	var itUnitId = rowItem.find('.tableItem-itUnitId');
	var itUnitName = rowItem.find('.tableItem-itUnitName');
	var itSourceQty = rowItem.find('.tableItem-itSourceQty');
	var itSourceWarehouseId = rowItem.find('.tableItem-itItemSourceWarehouseId');
	var itSourceWarehouseName = rowItem.find('.tableItem-itItemSourceWarehouseName');
	var itSourceLocationCode = rowItem.find('.tableItem-itSourceLocationCode');
	var itSourceLocationId = rowItem.find('.tableItem-itSourceLocationId');

	// Get data auto
	var itemId = data.value;
	var itemCode = data.code;
	var itemName = data.name;
	var itemBrand = data.brandName;
	var itemSkuId = data.skuId;
	var itemLot = data.lot;
	var itemExpireDate = data.expireDate;
	var itemModel = data.model;
	var itemSerial = data.serial;
	var itemUnitId = data.unitId;
	var itemUnitName = data.unitName;
	var itemSourceQty = data.stockQty;
	var itemSourceWarehouseId = data.warehouseId;
	var itemSourceWarehouseName = data.warehouseName;
	var itemLocationCode = data.locationCode;
	var itemLocationId = data.locationId;
	var itemLocationName = data.locationName;
	var itemLoacationNameFull = data.locationNameFull;

	console.log("itemSourceWarehouseId = ", itemSourceWarehouseId);
	console.log("itemLocationCode = ", itemLocationCode);
	console.log("itemLocationId = ", itemLocationId);

	setValue(itId, itemId);
	setValue(itCode, itemCode);
	setValue(itName, itemName);
	setValue(itBrand, itemBrand);
	setValue(itSkuId, itemSkuId);
	setValue(itLot, itemLot);
	setValue(itExpireDate, itemExpireDate);
	setValue(itModel, itemModel);
	setValue(itSerial, itemSerial);
	setValue(itUnitId, itemUnitId);
	setValue(itUnitName, itemUnitName);
	setValue(itSourceQty, itemSourceQty);
	setValue(itSourceWarehouseId, itemSourceWarehouseId);
	setValue(itSourceWarehouseName, itemSourceWarehouseName);
	setValue(itSourceLocationCode, itemLocationCode);
	setValue(itSourceLocationId, itemLocationId);

	inv05RowAmount(rowItem);
}

function inv05ChangeTargetWarehouse(event, rowItem) {
	var itemList = rowItem.closest('tbody').find('tr');
	var sourceWarehouseId = getValue(rowItem.find('.tableItem-itTargetItemWarehouse'));
	var index = toInt(getValue(rowItem.find('.tableItem-otIndex')), 0);
	index = index > 0 ? index - 1 : 0;

	console.log("id = ", sourceWarehouseId);

	if (index == 0) {
		itemList.each(function(i, e) {
			if (getValue($(this).find('.tableItem-itItemId'))) {
				setValue($(this).find('.tableItem-itTargetItemWarehouse'), sourceWarehouseId);

				// set Target Location
				setValue($(this).find('.tableItem-itTargetLocationId'), null);
				setValue($(this).find('.tableItem-itTargetLocationCode'), '');
			}
		});
	} else {
		setValue(rowItem.find('.tableItem-itTargetItemWarehouse'), sourceWarehouseId);

		// set Target Location
		setValue(rowItem.find('.tableItem-itTargetLocationId'), null);
		setValue(rowItem.find('.tableItem-itTargetLocationCode'), '');
	}

	$('#ihViewChanged').val(true);
}

function inv05ChangeTargetLocation(rowItem, data) {
	var itemList = rowItem.closest('tbody').find('tr');
	var index = toInt(getValue(rowItem.find('.tableItem-otIndex')), 0);
	index = index > 0 ? index - 1 : 0;

	if (index == 0) {
		itemList.each(function(i, e) {
			if (getValue($(this).find('.tableItem-itItemId'))) {
				setValue($(this).find('.tableItem-itTargetLocationCode'), data.code);
				setValue($(this).find('.tableItem-itTargetLocationId'), data.value);
			}
		});
	} else {
		setValue(rowItem.find('.tableItem-itTargetLocationCode'), data.code);
		setValue(rowItem.find('.tableItem-itTargetLocationId'), data.value);
	}

	$('#ihViewChanged').val(true);
}

function inv05RowAmount(rowItem) {

	console.log('ihViewChanged1 = ', $('#ihViewChanged').val());
	$('#ihViewChanged').val(true);
	console.log('ihViewChanged2 = ', $('#ihViewChanged').val());

	inv05SumAmount(rowItem);
}

function inv05SumAmount(rowItem) {
	var tbody = rowItem.closest('tbody');
	var tfoot = rowItem.closest('table').find('tfoot');

	var itQty = tbody.find('.tableItem-itSourceQty');
	var otSumQty = tfoot.find('.tableItem-itSumSourceQty');

	setValue(otSumQty, formatNumber(sumValue(itQty), 2));
}
