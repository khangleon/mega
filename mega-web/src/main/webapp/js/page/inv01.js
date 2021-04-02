function inv01LoadItem(rowItem, data) {
	var itItemId = rowItem.find('.tableItem-itItemId');
	var itItemCode = rowItem.find('.tableItem-itItemCode');
	var itItemCodeOrigin = rowItem.find('.tableItem-itItemCodeOrigin');
	var itItemName = rowItem.find('.tableItem-itItemName');
	var itItemNameOrigin = rowItem.find('.tableItem-itItemNameOrigin');
	var itBrand = rowItem.find('.tableItem-itBrand');
	var itQty = rowItem.find('.tableItem-itQty');
	var itUnitId = rowItem.find('.tableItem-itUnitId');

	// Get data auto
	var itemId = data.value;
	var itemCode = data.code;
	var itemCodeOrigin = data.codeOrigin;
	var itemName = data.name;
	var itemNameOrigin = data.nameOrigin;
	var brand = data.brandName;
	var qty = data.qty;
	var unitId = data.unitId;
	var unitName = data.unitName;

	setValue(itItemId, itemId);
	setValue(itItemCode, itemCode);
	setValue(itItemCodeOrigin, itemCodeOrigin);
	setValue(itItemName, itemName);
	setValue(itItemNameOrigin, itemNameOrigin);
	setValue(itBrand, brand);
	setValue(itQty, formatNumber(qty));
	setValue(itUnitId, unitId);

	inv01SumAmount(rowItem);
}

function inv01ChangeQty(e, rowItem) {
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
	
	inv01SumAmount(rowItem);
}

function inv01SumAmount(rowItem) {
	var tbody = rowItem.closest('tbody');
	var tfoot = rowItem.closest('table').find('tfoot');

	var itQty = tbody.find('.tableItem-itQty');
	var otSumQty = tfoot.find('.tableItem-sumQty');

	setValue(otSumQty, formatNumber(sumValue(itQty), 0));
}
