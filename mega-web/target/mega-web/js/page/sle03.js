function sle03LoadItem(rowItem, data) {
	var itItemId = rowItem.find('.tableItem-itItemId');
	var itItemCode = rowItem.find('.tableItem-itItemCode');
	var itItemName = rowItem.find('.tableItem-itItemName');
	var itItemCodeOrigin = rowItem.find('.tableItem-itItemCodeOrigin');
	var itItemNameOrigin = rowItem.find('.tableItem-itItemNameOrigin');
	var itBrand = rowItem.find('.tableItem-itBrand');
	var itUnitId = rowItem.find('.tableItem-itUnitId');
	var itUnitName = rowItem.find('.tableItem-itUnitName');
	var itQty = rowItem.find('.tableItem-itQty');
	var itStandardPrice = rowItem.find('.tableItem-itStandardPrice');
	var itAdjustRate = rowItem.find('.tableItem-itAdjustRate');
	var itPrice = rowItem.find('.tableItem-itPrice');
	var itVatRate = rowItem.find('.tableItem-itVatRate');
	var itVatPrice = rowItem.find('.tableItem-itVatPrice');
	var itIncenRate = rowItem.find('.tableItem-itIncenRate');
	var itComissionRate = rowItem.find('.tableItem-itComissionRate');
	var itExw = rowItem.find('.tableItem-itExw');
	var itCurrency = rowItem.find('.tableItem-itCurrency');
	var itCostPrice = rowItem.find('.tableItem-itCostPrice');
	var cbViewStock = rowItem.find('.tableItem-cbViewStock');
	var somCurrency = $('#somCurrency');

	// Get data auto
	var itemId = data.value;
	var itemCode = data.code;
	var itemName = data.name;
	var itemCodeOrigin = data.codeOrigin;
	var itemNameOrigin = data.nameOrigin;
	var brand = data.brandName;
	var unitId = data.unitId;
	var unitName = data.unitName;
	var standardPrice = toNumber(data.standardPrice);
	var price = toNumber(data.standardPrice);
	var vatRate = toNumber(data.vatRate);
	var incenRate = toNumber(data.incenRate);
	var comissionRate = toNumber(data.comissionRate);
	var currency = data.currency;
	var exw = toNumber(data.exw, currency);
	var costPrice = toNumber(data.costPrice);

	var qty = 1;

	var salesCurrency = toString(getValue(somCurrency), currencyDefault);
	var fraction = currencyFraction[salesCurrency];
	var fraction10 = 1;
	if (fraction) {
		fraction10 = Math.pow(10, fraction);
	}
	var vatPrice = Math.ceil((price * (100.0 + vatRate) / 100.0) * fraction10) / fraction10;

	setValue(itItemId, itemId);
	setValue(itItemCode, itemCode);
	setValue(itItemCodeOrigin, itemCodeOrigin);
	setValue(itItemName, itemName);
	setValue(itBrand, brand);
	setValue(itUnitId, unitId);
	setValue(itUnitName, unitName);
	setValue(itQty, formatNumber(qty));
	setValue(itStandardPrice, formatCurrency(standardPrice));
	setValue(itPrice, formatCurrency(price));
	setValue(itVatRate, formatNumber(vatRate));
	setValue(itVatPrice, formatCurrency(vatPrice));
	setValue(itIncenRate, formatNumber(incenRate, 2));
	setValue(itComissionRate, formatNumber(comissionRate, 2));
	setValue(itExw, formatNumber(exw, 2));
	setValue(itCurrency, currency);
	setValue(itCostPrice, formatCurrency(costPrice));

	cbViewStock.removeAttr("disabled");
	sle03RowAmount(rowItem);
}

function sle03ChangeQty(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}

	var itQty = rowItem.find('.tableItem-itQty');
	var qty = toNumber(getValue(itQty));

	var pos = itQty.getCursorPosition();
	var len = getValue(itQty).length;

	setValue(itQty, formatNumber(qty));

	console.log("Qty formatNumber = ", formatNumber(qty));

	sle03RowAmount(rowItem);

	qty = getValue(itQty);
	pos = getCursorPositionNew(e, pos, len, qty.length, qty);
	itQty.setCursorPosition(pos);
}

function sle03ChangePrice(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}

	var itStandardPrice = rowItem.find('.tableItem-itStandardPrice');
	var itAdjustRate = rowItem.find('.tableItem-itAdjustRate');
	var itPrice = rowItem.find('.tableItem-itPrice');
	var itVatRate = rowItem.find('.tableItem-itVatRate');
	var itVatPrice = rowItem.find('.tableItem-itVatPrice');
	var somCurrency = $('#somCurrency');

	var standardPrice = toNumber(getValue(itStandardPrice));
	var price = toNumber(getValue(itPrice));
	var vatRate = toNumber(getValue(itVatRate));
	var adjustRate = standardPrice > 0 ? 100.0 * (price - standardPrice) / standardPrice : 0;

	var salesCurrency = toString(getValue(somCurrency), currencyDefault);
	var fraction = currencyFraction[salesCurrency];
	var fraction10 = 1;
	if (fraction) {
		fraction10 = Math.pow(10, fraction);
	}
	var vatPrice = Math.ceil((price * (100.0 + vatRate) / 100.0) * fraction10) / fraction10;

	var pos = itPrice.getCursorPosition();
	var len = getValue(itPrice).length;

	setValue(itPrice, formatCurrency(price));
	setValue(itAdjustRate, formatNumber(adjustRate, 2));
	setValue(itVatPrice, formatCurrency(vatPrice));

	sle03RowAmount(rowItem);

	price = getValue(itPrice);
	pos = getCursorPositionNew(e, pos, len, price.length, price);
	itPrice.setCursorPosition(pos);
}

function sle03ChangeAdjustRate(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}

	var itStandardPrice = rowItem.find('.tableItem-itStandardPrice');
	var itAdjustRate = rowItem.find('.tableItem-itAdjustRate');
	var itPrice = rowItem.find('.tableItem-itPrice');
	var itVatRate = rowItem.find('.tableItem-itVatRate');
	var itVatPrice = rowItem.find('.tableItem-itVatPrice');
	var somCurrency = $('#somCurrency');

	var standardPrice = toNumber(getValue(itStandardPrice));
	var adjustRate = toNumber(getValue(itAdjustRate));
	var vatRate = toNumber(getValue(itVatRate));

	var salesCurrency = toString(getValue(somCurrency), currencyDefault);
	var fraction = currencyFraction[salesCurrency];
	var fraction10 = 1;
	if (fraction) {
		fraction10 = Math.pow(10, fraction);
	}

	var price = Math.ceil((standardPrice + standardPrice * adjustRate / 100.0) * fraction10) / fraction10;
	var vatPrice = Math.ceil((price * (100.0 + vatRate) / 100) * fraction10) / fraction10;

	var pos = itAdjustRate.getCursorPosition();
	var len = getValue(itAdjustRate).length;

	var keyCode = getKeyCode(e);
	if (getValue(itAdjustRate).startsWith('-') && adjustRate == 0) {
		// Input negative subtract(109), dash(189,173(Firefox))
		setValue(itAdjustRate, '-' + formatNumber(adjustRate, 2).substr(1));
	} else {
		setValue(itAdjustRate, formatNumber(adjustRate, 2));
	}
	setValue(itPrice, formatCurrency(price));
	setValue(itVatPrice, formatCurrency(vatPrice));

	sle03RowAmount(rowItem);

	adjustRate = getValue(itAdjustRate);
	pos = getCursorPositionNew(e, pos, len, adjustRate.length, adjustRate);
	itAdjustRate.setCursorPosition(pos);
}

function sle03ChangeVatRate(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}

	var itPrice = rowItem.find('.tableItem-itPrice');
	var itVatRate = rowItem.find('.tableItem-itVatRate');
	var itVatPrice = rowItem.find('.tableItem-itVatPrice');
	var somCurrency = $('#somCurrency');

	var price = toNumber(getValue(itPrice));
	var vatRate = toNumber(getValue(itVatRate));

	var salesCurrency = toString(getValue(somCurrency), currencyDefault);
	var fraction = currencyFraction[salesCurrency];
	var fraction10 = 1;
	if (fraction) {
		fraction10 = Math.pow(10, fraction);
	}
	var vatPrice = Math.ceil((price * (100.0 + vatRate) / 100) * fraction10) / fraction10;

	var pos = itVatRate.getCursorPosition();
	var len = getValue(itVatRate).length;

	setValue(itVatPrice, formatCurrency(vatPrice));
	setValue(itVatRate, formatCurrency(vatRate));

	sle03RowAmount(rowItem);

	vatRate = getValue(itVatRate);
	pos = getCursorPositionNew(e, pos, len, vatRate.length, vatRate);
	itVatRate.setCursorPosition(pos);
}

function sle03ChangeVatPrice(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}

	var itStandardPrice = rowItem.find('.tableItem-itStandardPrice');
	var itAdjustRate = rowItem.find('.tableItem-itAdjustRate');
	var itPrice = rowItem.find('.tableItem-itPrice');
	var itVatRate = rowItem.find('.tableItem-itVatRate');
	var itVatPrice = rowItem.find('.tableItem-itVatPrice');
	var sbcRoundVatPrice = rowItem.closest('table').find('.tableItem-roundVatPrice');
	var somCurrency = $('#somCurrency');

	var standardPrice = toNumber(getValue(itStandardPrice));
	var vatPrice = toNumber(getValue(itVatPrice));
	var vatRate = toNumber(getValue(itVatRate));
	var roundVatPrice = toBoolean(getValue(sbcRoundVatPrice));

	var salesCurrency = toString(getValue(somCurrency), currencyDefault);
	var fraction = currencyFraction[salesCurrency];
	var fraction10 = 1;
	if (fraction) {
		fraction10 = Math.pow(10, fraction);
	}

	var price = 0;
	if (roundVatPrice) {
		price = vatPrice - Math.ceil((vatPrice * vatRate / (100.0 + vatRate)) * fraction10) / fraction10;
	} else {
		price = Math.ceil((vatPrice * 100.0 / (100.0 + vatRate)) * fraction10) / fraction10;
		vatPrice = Math.ceil((price * (100.0 + vatRate) / 100.0) * fraction10) / fraction10;
	}
	var adjustRate = standardPrice > 0 ? 100.0 * (price - standardPrice) / standardPrice : 0;

	var pos = itVatPrice.getCursorPosition();
	var len = getValue(itVatPrice).length;

	setValue(itAdjustRate, formatNumber(adjustRate, 2));
	setValue(itPrice, formatCurrency(price));
	setValue(itVatPrice, formatCurrency(vatPrice));

	sle03RowAmount(rowItem);

	vatPrice = getValue(itVatPrice);
	pos = getCursorPositionNew(e, pos, len, vatPrice.length, vatPrice);
	itVatPrice.setCursorPosition(pos);
}

function sle03ChangeIncenRate(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}

	var itAmount = rowItem.find('.tableItem-itAmount');
	var itIncenRate = rowItem.find('.tableItem-itIncenRate');
	var itIncenAmount = rowItem.find('.tableItem-itIncenAmount');
	var itComissionAmount = rowItem.find('.tableItem-itComissionAmount');
	var itPayoutAmount = rowItem.find('.tableItem-itPayoutAmount');

	var incenRate = toNumber(getValue(itIncenRate));
	var amount = toNumber(getValue(itAmount));
	var incenAmount = amount * incenRate / 100.0;
	var comissionAmount = toNumber(getValue(itComissionAmount));
	var payoutAmount = incenAmount + comissionAmount;

	var pos = itIncenRate.getCursorPosition();
	var len = getValue(itIncenRate).length;

	setValue(itIncenRate, formatNumber(incenRate, 2));
	setValue(itIncenAmount, formatCurrency(incenAmount));
	setValue(itPayoutAmount, formatCurrency(payoutAmount));

	$('#ihViewChanged').val(true);
	sle03SumAmount(rowItem);

	incenRate = getValue(itIncenRate);
	pos = getCursorPositionNew(e, pos, len, incenRate.length, incenRate);
	itIncenRate.setCursorPosition(pos);
}

function sle03ChangeComissionRate(e, rowItem) {
	if (!isNumbericKeypad(e)) {
		return;
	}

	var itAmount = rowItem.find('.tableItem-itAmount');
	var itComissionRate = rowItem.find('.tableItem-itComissionRate');
	var itComissionAmount = rowItem.find('.tableItem-itComissionAmount');
	var itIncenAmount = rowItem.find('.tableItem-itIncenAmount');
	var itPayoutAmount = rowItem.find('.tableItem-itPayoutAmount');

	var comissionRate = toNumber(getValue(itComissionRate));
	var amount = toNumber(getValue(itAmount));
	var comissionAmount = amount * comissionRate / 100.0;
	var incenAmount = toNumber(getValue(itIncenAmount));
	var payoutAmount = incenAmount + comissionAmount;

	var pos = itComissionRate.getCursorPosition();
	var len = getValue(itComissionRate).length;

	setValue(itComissionRate, formatNumber(comissionRate, 2));
	setValue(itComissionAmount, formatCurrency(comissionAmount));
	setValue(itPayoutAmount, formatCurrency(payoutAmount));

	$('#ihViewChanged').val(true);
	sle03SumAmount(rowItem);

	comissionRate = getValue(itComissionRate);
	pos = getCursorPositionNew(e, pos, len, comissionRate.length, comissionRate);
	itComissionRate.setCursorPosition(pos);
}

function sle03ChangeCheckAll(e, rowItem) {
	/*
	 * var itemList = rowItem.closest('tbody').find('tr');
	 * 
	 * var index = toInt(getValue(rowItem.find('.tableItem-otIndex')), 0); index =
	 * index > 0 ? index - 1 : 0;
	 * 
	 * var scbCheckBox = getValue(rowItem.find('.tableItem-cbcCheckBox'));
	 * 
	 * console.log("scbCheckBox = ", scbCheckBox);
	 * 
	 * if (index == 0) { itemList.each(function(i, e) { if
	 * (getValue($(this).find('.tableItem-itItemId'))) { console.log(e); if
	 * (scbCheckBox){ setValue($(this).find('.tableItem-cbcCheckBox'), true);
	 * }else{ setValue($(this).find('.tableItem-cbcCheckBox'), false); }
	 *  } }); }
	 * 
	 * if (scbCheckBox){ setValue(rowItem.find('.tableItem-cbcCheckBox'),
	 * false); }else{ setValue(rowItem.find('.tableItem-cbcCheckBox'), true); }
	 * 
	 * $('#ihViewChanged').val(true);
	 */
}

function sle03RowAmount(rowItem) {
	var itQty = rowItem.find('.tableItem-itQty');
	var itStandardPrice = rowItem.find('.tableItem-itStandardPrice');
	var itPrice = rowItem.find('.tableItem-itPrice');
	var itVatRate = rowItem.find('.tableItem-itVatRate');
	var itVatPrice = rowItem.find('.tableItem-itVatPrice');
	var itAdjustAmount = rowItem.find('.tableItem-itAdjustAmount');
	var itAmount = rowItem.find('.tableItem-itAmount');
	var itVatAmount = rowItem.find('.tableItem-itVatAmount');
	var itTotalAmount = rowItem.find('.tableItem-itTotalAmount');
	var itStandardAmount = rowItem.find('.tableItem-itStandardAmount');
	var itIncenRate = rowItem.find('.tableItem-itIncenRate');
	var itIncenAmount = rowItem.find('.tableItem-itIncenAmount');
	var itComissionRate = rowItem.find('.tableItem-itComissionRate');
	var itComissionAmount = rowItem.find('.tableItem-itComissionAmount');
	var itCurrency = rowItem.find('.tableItem-itCurrency');
	var itExw = rowItem.find('.tableItem-itExw');
	var itExwAmount = rowItem.find('.tableItem-itExwAmount');
	var itCostPrice = rowItem.find('.tableItem-itCostPrice');
	var itCostAmount = rowItem.find('.tableItem-itCostAmount');
	var itPayoutAmount = rowItem.find('.tableItem-itPayoutAmount');
	var sbcRoundVatPrice = rowItem.closest('table').find('.tableItem-roundVatPrice');
	var somCurrency = $('#somCurrency');

	var qty = toNumber(getValue(itQty));
	var standardPrice = toNumber(getValue(itStandardPrice));
	var price = toNumber(getValue(itPrice));
	var vatRate = toNumber(getValue(itVatRate));
	var vatPrice = toNumber(getValue(itVatPrice));
	var incenRate = toNumber(getValue(itIncenRate));
	var comissionRate = toNumber(getValue(itComissionRate));
	var currency = getValue(itCurrency);
	var exw = toNumber(getValue(itExw), 2);
	var costPrice = toNumber(getValue(itCostPrice));
	var roundVatPrice = sbcRoundVatPrice.prop('checked');

	console.log('incentive = ', incenRate);
	console.log('comissionRate = ', comissionRate);

	var salesCurrency = toString(getValue(somCurrency), currencyDefault);
	var fraction = currencyFraction[salesCurrency];
	var fraction10 = 1;
	if (fraction) {
		fraction10 = Math.pow(10, fraction);
	}

	var standardAmount = standardPrice * qty;
	var amount = 0;
	var vatAmount = 0;
	var totalAmount = 0;
	if (roundVatPrice) {
		totalAmount = qty * vatPrice;
		vatAmount = Math.ceil((totalAmount * vatRate / (100.0 + vatRate)) * fraction10) / fraction10;
		amount = totalAmount - vatAmount;
	} else {
		amount = qty * price;
		vatAmount = Math.ceil((amount * vatRate / 100.0) * fraction10) / fraction10;
		totalAmount = amount + vatAmount;
	}

	var adjustAmount = amount - standardAmount;
	var incenAmount = amount * incenRate / 100.0;
	var comissionAmount = amount * comissionRate / 100.0;
	var exwAmount = qty * exw;
	var costAmount = qty * costPrice;
	var payoutAmount = incenAmount + comissionAmount;

	setValue(itAdjustAmount, formatCurrency(adjustAmount));
	setValue(itAmount, formatCurrency(amount));
	setValue(itVatAmount, formatCurrency(vatAmount));
	setValue(itTotalAmount, formatCurrency(totalAmount));
	setValue(itStandardAmount, formatCurrency(standardAmount));
	setValue(itIncenAmount, formatCurrency(incenAmount));
	setValue(itComissionAmount, formatCurrency(comissionAmount));
	setValue(itExw, formatNumber(exw, 2));
	setValue(itExwAmount, formatCurrency(exwAmount, 2));
	setValue(itCostPrice, formatNumber(costPrice));
	setValue(itCostAmount, formatNumber(costAmount));
	setValue(itPayoutAmount, formatNumber(payoutAmount));

	$('#ihViewChanged').val(true);
	sle03SumAmount(rowItem);
}

function sle03SumAmount(rowItem) {
	var tbody = rowItem.closest('tbody');
	var tfoot = rowItem.closest('table').find('tfoot');
	var itCurrency = rowItem.find('.tableItem-itCurrency');

	var itAdjustAmount = tbody.find('.tableItem-itAdjustAmount');
	var itAmount = tbody.find('.tableItem-itAmount');
	var itVatAmount = tbody.find('.tableItem-itVatAmount');
	var itTotalAmount = tbody.find('.tableItem-itTotalAmount');
	var itStandardAmount = tbody.find('.tableItem-itStandardAmount');
	var itIncenAmount = tbody.find('.tableItem-itIncenAmount');
	var itComissionAmount = tbody.find('.tableItem-itComissionAmount');
	var itExwAmount = tbody.find('.tableItem-itExwAmount');
	var itCostAmount = tbody.find('.tableItem-itCostAmount');
	var itPayoutAmount = tbody.find('.tableItem-itPayoutAmount');

	var otSumAdjustAmount = tfoot.find('.tableItem-sumAdjustAmount');
	var otSumAmount = tfoot.find('.tableItem-sumAmount');
	var otSumVatAmount = tfoot.find('.tableItem-sumVatAmount');
	var otSumTotalAmount = tfoot.find('.tableItem-sumTotalAmount');
	var otSumStandardAmount = tfoot.find('.tableItem-sumStandardAmount');
	var otSumIncenAmount = tfoot.find('.tableItem-sumIncenAmount');
	var otSumComissionAmount = tfoot.find('.tableItem-sumComissionAmount');
	var otSumExwAmount = tfoot.find('.tableItem-sumExwAmount');
	var otSumCostAmount = tfoot.find('.tableItem-sumCostAmount');
	var otSumPayoutAmount = tfoot.find('.tableItem-sumPayoutAmount');

	setValue(otSumAdjustAmount, formatCurrency(sumValue(itAdjustAmount)));
	setValue(otSumAmount, formatCurrency(sumValue(itAmount)));
	setValue(otSumVatAmount, formatCurrency(sumValue(itVatAmount)));
	setValue(otSumTotalAmount, formatCurrency(sumValue(itTotalAmount)));
	setValue(otSumStandardAmount, formatCurrency(sumValue(itStandardAmount)));
	setValue(otSumIncenAmount, formatCurrency(sumValue(itIncenAmount)));
	setValue(otSumComissionAmount, formatCurrency(sumValue(itComissionAmount)));
	setValue(otSumExwAmount, formatNumber(sumValue(itExwAmount), 2));
	setValue(otSumCostAmount, formatCurrency(sumValue(itCostAmount)));
	setValue(otSumPayoutAmount, formatCurrency(sumValue(itPayoutAmount)));
}
