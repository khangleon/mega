function pur12LoadItem(rowItem, data) {
	var itItemId = rowItem.find('.tableItem-itItemId');
	var itItemCode = rowItem.find('.tableItem-itItemCode');
	var itItemName = rowItem.find('.tableItem-itItemName');
	var itItemCodeOrder = rowItem.find('.tableItem-itItemCodeOrder');
	var itBrand = rowItem.find('.tableItem-itItemBrand');
	var itUnitId = rowItem.find('.tableItem-itItemUnitId');
	var itUnitName = rowItem.find('.tableItem-itItemUnitName');
	var itQty = rowItem.find('.tableItem-itItemQty');
	var itItemReqPoCode = rowItem.find('.tableItem-itItemReqPoCode');
	var itItemReqPoId = rowItem.find('.tableItem-itItemReqPoId');
	var itItemReqPoItemId = rowItem.find('.tableItem-itItemReqPoItemId');

	var itItemRequestDate = rowItem.find('.tableItem-itItemRequestDate');
	var itItemRequesterName = rowItem.find('.tableItem-itItemRequesterName');
	var itItemNotes = rowItem.find('.tableItem-itItemNotes');

	// Get data auto
	var itemId = data.value;
	var itemCode = data.code;
	var itemName = data.name;
	var itemCodeOrder = data.codeOrder;
	var itemBrand = data.brand;
	var unitId = data.unitId;
	var unitName = data.unitName;
	var qty = data.qty;
	var itemPoCode = data.reqPoCode;
	var itemPoId = data.reqPoId;
	var itemPoItemId = data.reqPoItemId;
	var itemRequestDate = data.requestDate;
	var itemRequestName = data.requestName;
	var itemNotes = data.notes;

	setValue(itItemId, itemId);
	setValue(itItemCode, itemCode);
	setValue(itItemCodeOrder, itemCodeOrder);
	setValue(itItemName, itemName);
	setValue(itUnitId, unitId);
	setValue(itUnitName, unitName);
	setValue(itQty, formatNumber(qty));
	setValue(itBrand, itemBrand);
	setValue(itItemReqPoCode, itemPoCode);
	setValue(itItemReqPoId, itemPoId);
	setValue(itItemReqPoItemId, itemPoItemId);
	setValue(itItemRequestDate, itemRequestDate);
	setValue(itItemRequesterName, itemRequestName);
	setValue(itItemNotes, itemNotes);

	pur12RowAmount(rowItem);
}

function pur12ChangeQty(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}

	var itQty = rowItem.find('.tableItem-itItemQty');
	var qty = toNumber(getValue(itQty));

	var pos = itQty.getCursorPosition();
	var len = getValue(itQty).length;

	setValue(itQty, formatNumber(qty));

	pur12RowAmount(rowItem);

	qty = getValue(itQty);
	pos = getCursorPositionNew(e, pos, len, qty.length, qty);
	itQty.setCursorPosition(pos);
}

function pur12RowAmount(rowItem) {

	$('#ihViewChanged').val(true);
	pur12SumAmount(rowItem);
}

function pur12SumAmount(rowItem) {
	console.log('SumAmount');
}
