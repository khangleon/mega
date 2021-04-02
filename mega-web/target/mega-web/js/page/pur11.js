function pur11LoadItem(rowItem, data) {
	var itItemId = rowItem.find('.tableItem-itItemId');
	var itItemCode = rowItem.find('.tableItem-itItemCode');
	var itItemName = rowItem.find('.tableItem-itItemName');
	var itItemCodeOrder = rowItem.find('.tableItem-itItemCodeOrder');
	var itItemNameOrigin = rowItem.find('.tableItem-itItemNameOrigin');
	var itBrand = rowItem.find('.tableItem-itBrand');
	var itUnitId = rowItem.find('.tableItem-itUnitId');
	var itUnitName = rowItem.find('.tableItem-itUnitName');
	var itQty = rowItem.find('.tableItem-itQty');
	var cbViewStock = rowItem.find('.tableItem-cbViewStock');

	// Get data auto
	var itemId = data.value;
	var itemCode = data.code;
	var itemName = data.name;
	var itemCodeOrder = data.codeOrder;
	var itemNameOrigin = data.nameOrigin;
	var brand = data.brandName;
	var unitId = data.unitId;
	var unitName = data.unitName;
	var qty = 1;

	setValue(itItemId, itemId);
	setValue(itItemCode, itemCode);
	setValue(itItemCodeOrder, itemCodeOrder);
	setValue(itItemName, itemName);
	setValue(itItemNameOrigin, itemNameOrigin);
	setValue(itBrand, brand);
	setValue(itUnitId, unitId);
	setValue(itUnitName, unitName);
	setValue(itQty, formatNumber(qty));

	cbViewStock.removeAttr("disabled");
	pur11RowAmount(rowItem);
}

function pur11ChangeQty(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}

	var itQty = rowItem.find('.tableItem-itQty');
	var qty = toNumber(getValue(itQty));

	var pos = itQty.getCursorPosition();
	var len = getValue(itQty).length;

	setValue(itQty, formatNumber(qty));

	pur11RowAmount(rowItem);

	qty = getValue(itQty);
	pos = getCursorPositionNew(e, pos, len, qty.length, qty);
	itQty.setCursorPosition(pos);
}

function pur11RowAmount(rowItem) {

	$('#ihViewChanged').val(true);
	pur11SumAmount(rowItem);
}

function pur11SumAmount(rowItem) {
	console.log('SumAmount');
	var tbody = rowItem.closest('tbody');
	var tfoot = rowItem.closest('table').find('tfoot');

	// get row item
	var itQty = tbody.find('.tableItem-itQty');

	// get column
	var otSumQty = tfoot.find('.tableItem-sumQty');
	setValue(otSumQty, formatNumber(sumValue(itQty), 0));
}
