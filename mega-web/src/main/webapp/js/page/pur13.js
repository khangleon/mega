function pur13LoadItem(rowItem, data) {
	var itItemId = rowItem.find('.tableItem-itItemId');
	var itItemCode = rowItem.find('.tableItem-itItemCode');
	var itItemCodeOrder = rowItem.find('.tableItem-itItemCodeOrder');
	var itItemName = rowItem.find('.tableItem-itItemName');
	var itUnitId = rowItem.find('.tableItem-itItemUnitId');
	var itUnitName = rowItem.find('.tableItem-itItemUnitName');
	var itQty = rowItem.find('.tableItem-itItemQty');
	var itExw = rowItem.find('.tableItem-itItemExw');
	var itExwAmount = rowItem.find('.tableItem-itItemExwAmount');
	var itCurrency = rowItem.find('.tableItem-itCurrency');
	var somCurrency = $('#somCurrency');

	// Get data auto
	var itemId = data.value;
	var itemCode = data.code;
	var itemName = data.name;
	var itemCodeOrder = data.codeOrder;
	var unitId = data.unitId;
	var unitName = data.unitName;
	var currency = data.currency;
	var exw = toNumber(data.exw, 2);
	var qty = 1;

	var salesCurrency = toString(getValue(somCurrency), currencyDefault);
	var fraction = currencyFraction[salesCurrency];
	var fraction10 = 1;
	if (fraction) {
		fraction10 = Math.pow(10, fraction);
	}
	var exwAmount = Math.ceil(qty * exw * fraction10) / fraction10;

	setValue(itItemId, itemId);
	setValue(itItemCode, itemCode);
	setValue(itItemCodeOrder, itemCodeOrder);
	setValue(itItemName, itemName);
	setValue(itUnitId, unitId);
	setValue(itUnitName, unitName);
	setValue(itQty, formatNumber(qty));
	setValue(itExw, formatNumber(exw, 2));
	setValue(itCurrency, currency);

	pur13RowAmount(rowItem);
}

function pur13ChangeQty(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}

	var itQty = rowItem.find('.tableItem-itItemQty');
	var qty = toNumber(getValue(itQty));

	var pos = itQty.getCursorPosition();
	var len = getValue(itQty).length;

	setValue(itQty, formatNumber(qty));

	pur13RowAmount(rowItem);

	qty = getValue(itQty);
	pos = getCursorPositionNew(e, pos, len, qty.length, qty);
	itQty.setCursorPosition(pos);
}

function pur13ChangeExw(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}
	var itQty = rowItem.find('.tableItem-itItemQty');
	var itExw = rowItem.find('.tableItem-itItemExw');

	var qty = toNumber(getValue(itQty));
	var exw = toNumber(getValue(itExw));

	var pos = itExw.getCursorPosition();
	var len = getValue(itExw).length;
	setValue(itExw, formatNumber(exw, 2));

	pur13RowAmount(rowItem);

	exw = getValue(itExw);
	pos = getCursorPositionNew(e, pos, len, exw.length, exw);
	itExw.setCursorPosition(pos);

}

function pur13ChangeDiscountRate(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}
	var itDiscountRate = rowItem.find('.tableItem-itItemDiscountRate');
	var discountRate = toNumber(getValue(itDiscountRate, 0));

	var pos = itDiscountRate.getCursorPosition();
	var len = getValue(itDiscountRate).length;
	setValue(itDiscountRate, formatNumber(discountRate, 2));

	pur13RowAmount(rowItem);

	exw = getValue(itDiscountRate);
	pos = getCursorPositionNew(e, pos, len, exw.length, exw);
	itDiscountRate.setCursorPosition(pos);

}

function pur13ChangeStatus(rowItem) {
	var itStatus = rowItem.find('.tableItem-itStatus');
	var status = toInt(getValue(itStatus));
	setValue(itStatus, status);
}

function pur13ChangeForwarder(rowItem, data) {
	var itemList = rowItem.closest('tbody').find('tr');
	var index = toInt(getValue(rowItem.find('.tableItem-otIndex')), 0);
	index = index > 0 ? index - 1 : 0;

	if (index == 0) {
		itemList.each(function(i, e) {
			if (getValue($(this).find('.tableItem-itItemId'))) {
				setValue($(this).find('.tableItem-itItemForwarderName'), data.label);
				setValue($(this).find('.tableItem-itItemForwarderId'), data.value);
			}
		});
	} else {
		setValue(rowItem.find('.tableItem-itItemForwarderName'), data.label);
		setValue(rowItem.find('.tableItem-itItemForwarderId'), data.value);
	}

}

function pur13ChangeTransportType(event, rowItem) {
	var itemList = rowItem.closest('tbody').find('tr');
	var transportTypeId = getValue(rowItem.find('.tableItem-itItemTransportTypeId'));
	var index = toInt(getValue(rowItem.find('.tableItem-otIndex')), 0);
	index = index > 0 ? index - 1 : 0;

	if (index == 0) {
		itemList.each(function(i, e) {
			if (getValue($(this).find('.tableItem-itItemId'))) {
				setValue($(this).find('.tableItem-itItemTransportTypeId'), transportTypeId);
			}
		});
	} else {
		setValue(rowItem.find('.tableItem-itItemTransportTypeId'), transportTypeId);
	}

	$('#ihViewChanged').val(true);
}

function pur13ChangeAwbNo(event, rowItem) {
	var itemList = rowItem.closest('tbody').find('tr');
	var index = toInt(getValue(rowItem.find('.tableItem-otIndex')), 0);
	index = index > 0 ? index - 1 : 0;
	var awbNo = getValue(rowItem.find('.tableItem-itAwbNo'));

	if (index == 0) {
		itemList.each(function(i, e) {
			if (getValue($(this).find('.tableItem-itItemId'))) {
				setValue($(this).find('.tableItem-itAwbNo'), awbNo);
			}
		});
	} else {
		setValue(rowItem.find('.tableItem-itAwbNo'), awbNo);
	}

	$('#ihViewChanged').val(true);
}

function pur13ChangePickupDate(event, rowItem) {
	var itemList = rowItem.closest('tbody').find('tr');
	var index = toInt(getValue(rowItem.find('.tableItem-otIndex')), 0);
	index = index > 0 ? index - 1 : 0;
	var pickupDate = getValue(rowItem.find('.tableItem-itItemPickupDate'));

	if (index == 0) {
		itemList.each(function(i, e) {
			if (getValue($(this).find('.tableItem-itItemId'))) {
				setValue($(this).find('.tableItem-itItemPickupDate'), pickupDate);
			}
		});
	} else {
		setValue(rowItem.find('.tableItem-itItemPickupDate'), pickupDate);
	}

	$('#ihViewChanged').val(true);
}

function pur13ChangeEstDelivery(event, rowItem) {
	var itemList = rowItem.closest('tbody').find('tr');
	var index = toInt(getValue(rowItem.find('.tableItem-otIndex')), 0);
	index = index > 0 ? index - 1 : 0;
	var estDelivery = getValue(rowItem.find('.tableItem-itItemEstDelivery'));

	if (index == 0) {
		itemList.each(function(i, e) {
			if (getValue($(this).find('.tableItem-itItemId'))) {
				setValue($(this).find('.tableItem-itItemEstDelivery'), estDelivery);
			}
		});
	} else {
		setValue(rowItem.find('.tableItem-itItemEstDelivery'), estDelivery);
	}

	$('#ihViewChanged').val(true);
}

function pur13RowAmount(rowItem) {

	var itQty = rowItem.find('.tableItem-itItemQty');
	var itExw = rowItem.find('.tableItem-itItemExw');
	var itExwAmount = rowItem.find('.tableItem-itItemExwAmount');
	var itDiscountRate = rowItem.find('.tableItem-itItemDiscountRate');
	var itDiscountAmount = rowItem.find('.tableItem-itItemDiscountAmount');
	var itTotalAmount = rowItem.find('.tableItem-itTotalAmount');
	var somCurrency = $('#somCurrency');

	var salesCurrency = toString(getValue(somCurrency), currencyDefault);
	var fraction = currencyFraction[salesCurrency];
	var fraction10 = 1;
	if (fraction) {
		fraction10 = Math.pow(10, fraction);
	}

	var qty = toNumber(getValue(itQty));
	var exw = toNumber(getValue(itExw));
	var discountRate = toNumber(getValue(itDiscountRate));

	var exwAmount = 0;
	var discountAmount = 0;
	var totalAmount = 0;

	exwAmount = qty * exw;
	discountAmount = exwAmount * discountRate;

	discountAmount = Math.round(((discountAmount / 100) * fraction10) / fraction10, 2);
	totalAmount = exwAmount - discountAmount;
	setValue(itExwAmount, formatNumber(exwAmount, 2)); //
	setValue(itDiscountRate, formatNumber(discountRate, 2));
	setValue(itDiscountAmount, formatNumber(discountAmount, 2));
	setValue(itTotalAmount, formatNumber(totalAmount, 2));

	$('#ihViewChanged').val(true);

	pur13SumAmount(rowItem);
}

function pur13SumAmount(rowItem) {
	var tbody = rowItem.closest('tbody');
	var tfoot = rowItem.closest('table').find('tfoot');

	var itExwAmount = tbody.find('.tableItem-itItemExwAmount');
	var itDiscountAmount = tbody.find('.tableItem-itItemDiscountAmount');
	var itTotalAmount = tbody.find('.tableItem-itTotalAmount');

	var otSumExwAmount = tfoot.find('.tableItem-itSumExwAmount');
	var otSumDiscountAmount = tfoot.find('.tableItem-sumDiscountAmount');
	var otSumTotalAmount = tfoot.find('.tableItem-sumTotalAmount');

	setValue(otSumExwAmount, formatNumber(sumValue(itExwAmount), 2));
	setValue(otSumDiscountAmount, formatNumber(sumValue(itDiscountAmount), 2));
	setValue(otSumTotalAmount, formatNumber(sumValue(itTotalAmount), 2));
}
