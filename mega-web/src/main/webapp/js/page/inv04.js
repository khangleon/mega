function inv04LoadItem(rowItem, data) {
	var itWarehouseName = rowItem.find('.tableItem-itItemWarehouseName');
	var itWarehouseId = rowItem.find('.tableItem-itItemWarehouseId');
	var itLocationId = rowItem.find('.tableItem-itLocationId');
	var itLocationName = rowItem.find('.tableItem-itLocationName');
	var itSkuId = rowItem.find('.tableItem-itSkuId');
	var itItemId = rowItem.find('.tableItem-itItemId');
	var itItemCode = rowItem.find('.tableItem-itItemCode');
	var itItemCodeOrigin = rowItem.find('.tableItem-itItemCodeOrigin');
	var itItemName = rowItem.find('.tableItem-itItemName');
	var itItemNameOrigin = rowItem.find('.tableItem-itItemNameOrigin');
	var itBrand = rowItem.find('.tableItem-itBrand');
	var itUnitId = rowItem.find('.tableItem-itUnitId');
	var itUnitName = rowItem.find('.tableItem-itUnitName');
	var itQty = rowItem.find('.tableItem-itQty');
	var itLot = rowItem.find('.tableItem-itLot');
	var itModel = rowItem.find('.tableItem-itModel');
	var itSerial = rowItem.find('.tableItem-itSerial');
	var itExpireDate = rowItem.find('.tableItem-itExpireDate');
	var otCostPrice = rowItem.find('.tableItem-otCostPrice');
	var otExw = rowItem.find('.tableItem-otExw');
	var otCurrency = rowItem.find('.tableItem-otCurrency');
	var itBarcode = rowItem.find('.tableItem-itBarcode');
	var sbcInputBarcode = rowItem.closest('table').find('.sbcInputBarcode');

	// Get data auto
	var itemWarehouseTypeName = data.warehouseName;
	var itemWarehouseTypeId = data.warehouseId;
	var itemLocationId = data.locationId;
	var itemLocationName = data.locationNameFull;
	var itemLocationCode = data.locationCode;
	var itemSkuId = data.skuId;
	var itemCode = data.code;
	var itemId = data.value;
	var itemCode = data.code;
	var itemCodeOrigin = data.codeOrigin;
	var itemName = data.name;
	var itemNameOrigin = data.nameOrigin;
	var itemBrand = data.brandName;
	var itemQty = toNumber(data.stockQty);
	var itemUnitId = data.unitId;
	var itemUnitName = data.unitName;
	var itemLot = data.lot;
	var itemModel = data.model;
	var itemSerial = data.serial;
	var itemExpireDate = data.expireDate;
	var itemCostPrice = toNumber(data.costPrice);
	var itemExw = toNumber(data.exw);
	var itemCurrency = data.currency;
	var itemBarcode = data.barcode;
	var inputBarcode = sbcInputBarcode.prop('checked');

	// console.log('barcode = ', itemBarcode);
	console.log('inputBarcode new  = ', inputBarcode);
	itemQty = inputBarcode ? 1 : itemQty;

	setValue(itWarehouseName, itemWarehouseTypeName);
	setValue(itWarehouseId, itemWarehouseTypeId);
	setValue(itLocationId, itemLocationId);
	setValue(itLocationName, itemLocationCode);
	setValue(itSkuId, itemSkuId);
	setValue(itItemId, itemId);
	setValue(itItemCode, itemCode);
	setValue(itItemCodeOrigin, itemCodeOrigin);
	setValue(itItemName, itemName);
	setValue(itItemNameOrigin, itemNameOrigin);
	setValue(itBrand, itemBrand);
	setValue(itUnitId, itemUnitId);
	setValue(itUnitName, itemUnitName);
	setValue(itQty, formatNumber(itemQty));
	setValue(itLot, itemLot);
	setValue(itModel, itemModel);
	setValue(itSerial, itemSerial);
	setValue(itExpireDate, itemExpireDate);
	setValue(otCostPrice, formatNumber(itemCostPrice, 2));
	setValue(otExw, formatNumber(itemExw, 2));
	setValue(otCurrency, itemCurrency);
	setValue(itBarcode, itemBarcode);

	inv04RowAmount(rowItem);
}
var count = 0;
function inv04LoadBarcode(rowItem, data) {
	console.log('count = ', count++);
	var bodyRow = rowItem.closest('tbody').find('tr');
	$.each(bodyRow, function(index, e) {
		e = $(e);
		console.log('index: ' + index + '; row: ' + getValue(e.find('.tableItem-otIndex')) + '; code: ' + getValue(e.find('.tableItem-itItemCode')) + 'barcode: '
				+ getValue(e.find('.tableItem-itBarcode')));
	});
	inv04LoadItem(rowItem, data);
	console.log('dong: ' + getValue(rowItem.find('.tableItem-otIndex')) + '; Barcode = ' + getValue(rowItem.find('.tableItem-itBarcode')));
}

function inv04ChangeSku(rowItem, data) {
	console.log("ChangeSKu = ");
	var itWarehouseName = rowItem.find('.tableItem-itItemWarehouseName');
	var itWarehouseId = rowItem.find('.tableItem-itItemWarehouseId');
	var itLocationId = rowItem.find('.tableItem-itLocationId');
	var itLocationName = rowItem.find('.tableItem-itLocationName');
	var itSkuId = rowItem.find('.tableItem-itSkuId');
	var itItemId = rowItem.find('.tableItem-itItemId');
	var itItemCode = rowItem.find('.tableItem-itItemCode');
	var itItemCodeOrigin = rowItem.find('.tableItem-itItemCodeOrigin');
	var itItemName = rowItem.find('.tableItem-itItemName');
	var itItemNameOrigin = rowItem.find('.tableItem-itItemNameOrigin');
	var itBrand = rowItem.find('.tableItem-itBrand');
	var itUnitId = rowItem.find('.tableItem-itUnitId');
	var itUnitName = rowItem.find('.tableItem-itUnitName');
	var itQty = rowItem.find('.tableItem-itQty');
	var itLot = rowItem.find('.tableItem-itLot');
	var itModel = rowItem.find('.tableItem-itModel');
	var itSerial = rowItem.find('.tableItem-itSerial');
	var itExpireDate = rowItem.find('.tableItem-itExpireDate');
	var otCostPrice = rowItem.find('.tableItem-otCostPrice');
	var otExw = rowItem.find('.tableItem-otExw');
	var otCurrency = rowItem.find('.tableItem-otCurrency');
	var itBarcode = rowItem.find('.tableItem-itBarcode');

	// Get data auto
	var itemWarehouseTypeName = data.warehouseName;
	var itemWarehouseTypeId = data.warehouseId;
	var itemLocationId = data.locationId;
	var itemLocationName = data.locationNameFull;
	var itemLocationCode = data.locationCode;
	var itemSkuId = data.skuId;
	var itemCode = data.code;
	var itemId = data.value;
	var itemCode = data.code;
	var itemCodeOrigin = data.codeOrigin;
	var itemName = data.name;
	var itemNameOrigin = data.nameOrigin;
	var itemBrand = data.brandName;
	var itemQty = toNumber(data.stockQty);
	var itemUnitId = data.unitId;
	var itemUnitName = data.unitName;
	var itemLot = data.lot;
	var itemModel = data.model;
	var itemSerial = data.serial;
	var itemExpireDate = data.expireDate;
	var itemCostPrice = toNumber(data.costPrice);
	var itemExw = toNumber(data.exw);
	var itemCurrency = data.currency;
	var itemBarcode = data.barcode;

	setValue(itWarehouseName, itemWarehouseTypeName);
	setValue(itWarehouseId, itemWarehouseTypeId);
	setValue(itLocationId, itemLocationId);
	setValue(itLocationName, itemLocationCode);
	setValue(itSkuId, itemSkuId);
	setValue(itItemId, itemId);
	setValue(itItemCode, itemCode);
	setValue(itItemCodeOrigin, itemCodeOrigin);
	setValue(itItemName, itemName);
	setValue(itItemNameOrigin, itemNameOrigin);
	setValue(itBrand, itemBrand);
	setValue(itUnitId, itemUnitId);
	setValue(itUnitName, itemUnitName);
	setValue(itQty, formatNumber(itemQty));
	setValue(itLot, itemLot);
	setValue(itModel, itemModel);
	setValue(itSerial, itemSerial);
	setValue(itExpireDate, itemExpireDate);
	setValue(otCostPrice, formatNumber(itemCostPrice, 2));
	setValue(otExw, formatNumber(itemExw, 2));
	setValue(otCurrency, itemCurrency);
	setValue(itBarcode, itemBarcode);

	inv04RowAmount(rowItem);
}

function inv04ChangeItemWarehouse(rowItem) {
	var itWarehouseId = rowItem.find('.tableItem-itItemWarehouseId');
	var itName = rowItem.find('.tableItem-itItemName');
	var itLocationId = rowItem.find('.tableItem-itLocationId');
	var itLocationName = rowItem.find('.tableItem-itLocationName');

	var warehouseId = toInt(getValue(itWarehouseId));
	var locationId = toInt(getValue(itLocationId));
	var locationName = toInt(getValue(itLocationName));

	setValue(itWarehouseId, warehouseId);
	setValue(itLocationId, null);
	setValue(itLocationName, null);
}

function inv04StorageLocation(rowItem, data) {
	var itLocationId = rowItem.find('.tableItem-itLocationId');
	var itLocationName = rowItem.find('.tableItem-itLocationName');
	var itSkuId = rowItem.find('.tableItem-itSkuId');
	var itItemWarehouse = rowItem.find(".tableItem-itItemWarehouse");
	var itItemId = rowItem.find('.tableItem-itItemId');
	var itItemCode = rowItem.find('.tableItem-itItemCode');
	var itItemCodeOrigin = rowItem.find('.tableItem-itItemCodeOrigin');
	var itItemName = rowItem.find('.tableItem-itItemName');
	var itItemNameOrigin = rowItem.find('.tableItem-itItemNameOrigin');
	var itBrand = rowItem.find('.tableItem-itBrand');
	var itUnitId = rowItem.find('.tableItem-itUnitId');
	var itUnitName = rowItem.find('.tableItem-itUnitName');
	var itQty = rowItem.find('.tableItem-itQty');
	var itLot = rowItem.find('.tableItem-itLot');
	var itModel = rowItem.find('.tableItem-itModel');
	var itSerial = rowItem.find('.tableItem-itSerial');
	var itExpireDate = rowItem.find('.tableItem-itExpireDate');
	// var itPrice = rowItem.find('.tableItem-itPrice');
	var itCostPrice = rowItem.find('.tableItem-otCostPrice');
	var itCostAmount = rowItem.find('.tableItem-itCostAmount');
	var itExw = rowItem.find('.tableItem-itExw');
	var itCurrency = rowItem.find('.tableItem-itCurrency');

	// Get data auto
	var itemLocationId = data.locationId;
	var itemLocationCode = data.locationCode;
	var itemLocationName = data.locationNameFull;
	var itemSkuId = data.skuId;
	var itemCode = data.code;
	var itemId = data.value;
	var itemCode = data.code;
	var itemCodeOrigin = data.codeOrigin;
	var itemName = data.name;
	var itemNameOrigin = data.nameOrigin;
	var itemBrand = data.brandName;
	var itemQty = toNumber(data.qty);
	var itemUnitId = data.unitId;
	var itemUnitName = data.unitName;
	var itemLot = data.lot;
	var itemModel = data.model;
	var itemSerial = data.serial;
	var itemExpireDate = data.expireDate;
	var itemCurrency = data.currency;
	// var itemPrice = toNumber(data.price);
	var itemCostPrice = toNumber(data.costPrice);
	var itemExw = toNumber(data.exw);

	setValue(itLocationId, itemLocationId);
	setValue(itLocationName, itemLocationCode);
	setValue(itSkuId, itemSkuId);
	setValue(itItemId, itemId);
	setValue(itItemCode, itemCode);
	setValue(itItemCodeOrigin, itemCodeOrigin);
	setValue(itItemName, itemName);
	setValue(itItemNameOrigin, itemNameOrigin);
	setValue(itBrand, itemBrand);
	setValue(itUnitId, itemUnitId);
	setValue(itUnitName, itemUnitName);
	setValue(itQty, formatNumber(itemQty));
	setValue(itLot, itemLot);
	setValue(itModel, itemModel);
	setValue(itSerial, itemSerial);
	setValue(itExpireDate, itemExpireDate);
	setValue(itPrice, itemPrice);
	setValue(itCostPrice, itemCostPrice);
	setValue(itExw, itemExw);
	setValue(itemExw, itemExw);

	itLocationName.prop("title", itemLocationName);

	inv04RowAmount(rowItem);
}

function inv04ChangeQty(e, rowItem) {
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

	inv04RowAmount(rowItem);
}

function inv04RowAmount(rowItem) {
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

	inv04SumAmount(rowItem);
}

function inv04SumAmount(rowItem) {
	var tbody = rowItem.closest('tbody');
	var tfoot = rowItem.closest('table').find('tfoot');

	var itQty = tbody.find('.tableItem-itQty');
	var otSumQty = tfoot.find('.tableItem-sumQty');

	setValue(otSumQty, formatNumber(sumValue(itQty), 0));
}
