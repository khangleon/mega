function inv02LoadItem(rowItem, data) {
	var itItemId = rowItem.find('.tableItem-itItemId');
	var itItemCode = rowItem.find('.tableItem-itItemCode');
	var itItemName = rowItem.find('.tableItem-itItemName');
	var itItemCodeOrigin = rowItem.find('.tableItem-itItemCodeOrigin');
	var itItemNameOrigin = rowItem.find('.tableItem-itItemNameOrigin');
	var itBrand = rowItem.find('.tableItem-itBrand');
	var itUnitId = rowItem.find('.tableItem-itUnitId');
	var itUnitName = rowItem.find('.tableItem-itUnitName');
	var itQty = rowItem.find('.tableItem-itQty');
	var otCostPrice = rowItem.find('.tableItem-otCostPrice');
	var otExw = rowItem.find('.tableItem-otExw');
	var otCurrency = rowItem.find('.tableItem-otCurrency');

	var itSkuId = rowItem.find('.tableItem-itSkuId');
	var itLot = rowItem.find('.tableItem-itLot');
	var itModel = rowItem.find('.tableItem-itModel');
	var itSerial = rowItem.find('.tableItem-itSerial');
	var itExpireDate = rowItem.find('.tableItem-itExpireDate');

	// Get data auto
	var itemId = data.value;
	var itemCode = data.code;
	var itemName = data.name;
	var itemCodeOrigin = data.codeOrigin;
	var itemNameOrigin = data.nameOrigin;
	var brand = data.brandName;
	var unitId = data.unitId;
	var unitName = data.unitName;
	var qty = data.qty;
	var costPrice = data.costPrice;
	var exw = data.exw;
	var currency = data.currency;

	var inventoryType = toInt(getValue($("#somType")), -1);
	console.log("inventoryType = ", inventoryType)
	if (inventoryType == 3 || inventoryType == 4 || inventoryType == 5
			|| inventoryType == 6) {
		var itemSkuId = data.skuId;
		var itemLot = data.lot;
		var itemModel = data.model;
		var itemSerial = data.serial;
		var itemExpireDate = data.expireDate;

		console.log("itemSkuId = ", itemSkuId);

		setValue(itSkuId, itemSkuId);
		setValue(itLot, itemLot);
		setValue(itModel, itemModel);
		setValue(itSerial, itemSerial);
		setValue(itExpireDate, itemExpireDate);
	}

	setValue(itItemId, itemId);
	setValue(itItemCode, itemCode);
	setValue(itItemCodeOrigin, itemCodeOrigin);
	setValue(itItemName, itemName);
	setValue(itItemNameOrigin, itemNameOrigin);
	setValue(itBrand, brand);
	setValue(itUnitId, unitId);
	setValue(itUnitName, unitName);
	setValue(itQty, formatNumber(qty));
	setValue(otCostPrice, formatNumber(costPrice, 2));
	setValue(otExw, formatNumber(exw, 2));
	setValue(otCurrency, currency);

	inv02RowAmount(rowItem);
}

function inv02ChangeItemWarehouse(event, rowItem) {
	// var itWarehouseId = rowItem.find('.tableItem-itItemWarehouseId');
	var itLocationId = rowItem.find('.tableItem-itLocationId');
	var itLocationName = rowItem.find('.tableItem-itLocationName');

	var itemList = rowItem.closest('tbody').find('tr');
	var itemWarehouseId = getValue(rowItem.find('.tableItem-itItemWarehouseId'));
	var index = toInt(getValue(rowItem.find('.tableItem-otIndex')), 0);
	index = index > 0 ? index - 1 : 0;

	if (index == 0) {
		itemList.each(function(i, e) {
			setValue($(this).find('.tableItem-itItemWarehouseId'),
					itemWarehouseId);
		});
	} else {
		setValue(rowItem.find('.tableItem-itItemWarehouseId'), itemWarehouseId);
	}

	setValue(itLocationId, null);
	setValue(itLocationName, '');

	$('#ihViewChanged').val(true);
}

function inv02StorageLocation(rowItem, data) {
	var itLocationId = rowItem.find('.tableItem-itLocationId');
	var itLocationName = rowItem.find('.tableItem-itLocationName');

	// Get data auto
	var locationId = data.value;
	var locationName = data.label;
	var locationCode = data.code;

	setValue(itLocationId, locationId);
	setValue(itLocationName, locationCode);

	itLocationName.prop("title", locationName);

	$('#ihViewChanged').val(true);
}

function inv02ChangeSku(rowItem, data) {
	var itSkuId = rowItem.find('.tableItem-itSkuId');
	var itSkuCode = rowItem.find('.tableItem-itSkuCode');
	var itLot = rowItem.find('.tableItem-itLot');
	var itMode = rowItem.find('.tableItem-itModel');
	var itSerial = rowItem.find('.tableItem-itSerial');
	var itExpireDate = rowItem.find('.tableItem-itExpireDate');

	// Get data auto
	var locationId = data.value;
	var locationName = data.label;
	var lot = data.lot;
	var model = data.model;
	var serial = data.serial;
	var expireDate = data.expireDate;

	setValue(itSkuId, locationId);
	setValue(itLot, lot);
	setValue(itMode, model);
	setValue(itSerial, serial);
	setValue(itExpireDate, expireDate);

	inv02RowAmount(rowItem);
}

function inv02ChangeQty(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}

	var itQty = rowItem.find('.tableItem-itQty');
	var qty = toNumber(getValue(itQty));

	var pos = itQty.getCursorPosition();
	var len = getValue(itQty).length;

	setValue(itQty, formatNumber(qty));

	qty = getValue(itQty);
	pos = getCursorPositionNew(e, pos, len, qty.length, qty);
	itQty.setCursorPosition(pos);

	inv02RowAmount(rowItem);
}

function inv02RowAmount(rowItem) {
	var itQty = rowItem.find('.tableItem-itQty');
	var otCostPrice = rowItem.find('.tableItem-otCostPrice');
	var otCostAmount = rowItem.find('.tableItem-otCostAmount');
	var otExw = rowItem.find('.tableItem-otExw');
	var otExwAmount = rowItem.find('.tableItem-otExwAmount');

	var qty = toInt(getValue(itQty), 0);
	var costPrice = toInt(getValue(otCostPrice), 0);
	var exw = toInt(getValue(otExw), 0);

	var costAmount = 0;
	var exwAmount = 0;

	costAmount = qty * costPrice;
	exwAmount = qty * exw;

	setValue(otCostAmount, formatNumber(costAmount, 2));
	setValue(otExwAmount, formatNumber(exwAmount, 2));

	$('#ihViewChanged').val(true);
	inv02SumAmount(rowItem);
}

function inv02SumAmount(rowItem) {
	var tbody = rowItem.closest('tbody');
	var tfoot = rowItem.closest('table').find('tfoot');

	// get row item
	var qty = tbody.find('.tableItem-itQty');
	var costAmount = tbody.find('.tableItem-otSumCostAmount');
	var exwAmount = tbody.find('.tableItem-otSumExwAmount');

	// get column
	var otSumQty = tfoot.find('.tableItem-sumQty');
	var otSumCostAmount = rowItem.find('.tableItem-otSumCostAmount');
	var otSumExwAmount = rowItem.find('.tableItem-otSumExwAmount');

	setValue(otSumQty, formatNumber(sumValue(qty), 0));
	setValue(otSumCostAmount, formatNumber(sumValue(qty), 2));
	setValue(otSumExwAmount, formatNumber(sumValue(qty), 2));
}
