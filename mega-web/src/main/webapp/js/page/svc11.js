function svc11LoadItem(rowItem, data) {
	var itItemId = rowItem.find('.tableItem-itItemId');
	var itItemCode = rowItem.find('.tableItem-itItemCode');
	var itItemName = rowItem.find('.tableItem-itItemName');
	var itDeviceId = rowItem.find('.tableItem-itDeviceId');
	var itItemDeviceId = rowItem.find('.tableItem-itDeviceId');
	var itUnitId = rowItem.find('.tableItem-itItemUnitId');
	var itUnitName = rowItem.find('.tableItem-itItemUnitName');
	var itQty = rowItem.find('.tableItem-itItemQty');
	var itItemLot = rowItem.find('.tableItem-itLot');
	var itItemExpireDate = rowItem.find('.tableItem-itExpireDate');
	var itItemModel = rowItem.find('.tableItem-itModel');
	var itItemSerial = rowItem.find('.tableItem-itSerial');

	// Get data auto
	var itemId = data.value;
	var itemCode = data.code;
	var itemName = data.name;
	var itemDeviceId = data.value;
	var unitId = data.unitId;
	var unitName = data.unitName;
	var qty = data.qty;
	var itemLot = data.lot;
	var itemExpireDate = data.expireDate;
	var itemModel = data.model;
	var itemSerial = data.serial;

	setValue(itItemId, itemId);
	setValue(itItemCode, itemCode);
	setValue(itItemName, itemName);
	setValue(itItemDeviceId, itemDeviceId);
	setValue(itUnitId, unitId);
	setValue(itUnitName, unitName);
	setValue(itQty, formatNumber(qty));
	setValue(itItemLot, itemLot);
	setValue(itItemExpireDate, itemExpireDate);
	setValue(itItemModel, itemModel);
	setValue(itItemSerial, itemSerial);

	svc11RowAmount(rowItem);
}

function svc11ChangeQty(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}

	var itQty = rowItem.find('.tableItem-itItemQty');
	var qty = toNumber(getValue(itQty));

	var pos = itQty.getCursorPosition();
	var len = getValue(itQty).length;

	setValue(itQty, formatNumber(qty));

	svc11RowAmount(rowItem);

	qty = getValue(itQty);
	pos = getCursorPositionNew(e, pos, len, qty.length, qty);
	itQty.setCursorPosition(pos);
}

function svc11RowAmount(rowItem) {

	$('#ihViewChanged').val(true);
	svc11SumAmount(rowItem);
}

function svc11SumAmount(rowItem) {
	console.log('SumAmount1');
}
