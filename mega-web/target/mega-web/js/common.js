var localeDefault = 'vn';
var currencyDefault = 'VND';
var currencyFraction = {
	'VND' : 0,
	'USD' : 2,
	'EUR' : 2,
	'Other' : 2
};

/**
 * Check object is empty
 * 
 * @param src
 * @returns {Boolean}
 */
function isEmpty(val) {
	if (val == null || val == undefined || ('' + val).trim() == '' || ('' + val).trim().length == 0) {
		return true;
	}
	return false;
}

/**
 * Check object isn't empty
 * 
 * @param src
 * @returns {Boolean}
 */
function isNotEmpty(val) {
	if (val == null || val == undefined || val.trim() == '' || val.trim().length == 0) {
		return false;
	}
	return true;
}

function toString(val, valDef) {
	val = isEmpty(val) ? (valDef == undefined ? '' : valDef) : '' + val;
	return val
}

function toNumber(val, valDef) {
	if (val) {
		if (typeof val === 'number')
			return val;
		try {
			return $.parseNumber(new String(val), {
				locale : localeDefault
			});
		} catch (e) {
			return 0;
		}

	} else {
		return valDef == undefined ? 0 : valDef;
	}
}

function toInt(val, valDef) {
	if (val) {
		if (typeof val === 'number')
			return Math.round(val);
		val = $.parseNumber(new String(val), {
			locale : localeDefault
		});
		return Math.round(val);
	} else {
		return valDef == undefined ? 0 : valDef;
	}
}

function toBoolean(val, valDef) {
	var ret = false;
	if (isEmpty(val)) {
		ret = valDef == undefined ? false : valDef;
	} else {
		if (isNaN(val)) {
			val = new String(val);
			ret = val.toLowerCase() == 'true';
		} else {
			val = new Number(val);
			ret = val > 0;
		}
	}

	return ret;
}

function formatNumber(num, d) {
	if (num) {
		var format = '#,##0';
		if (d && d > 0) {
			format += '.';
			for (var i = 0; i < d; i++) {
				format += '0';
			}
		}

		return $.formatNumber(num, {
			locale : localeDefault,
			format : format
		});
	} else {
		return num;
	}
}

function formatCurrency(num, cur) {
	cur = cur || currencyDefault;
	if (num) {
		var format = '#,##0';
		d = currencyFraction[cur];
		if (d && d > 0) {
			format += '.';
			for (var i = 0; i < d; i++) {
				format += '0';
			}
		}
		return $.formatNumber(num, {
			locale : localeDefault,
			format : format
		});
	} else {
		return num;
	}
}

function setValue(com, value) {
	if (jQuery(com).is(":input"))
		jQuery(com).val(value);
	else
		jQuery(com).text(value);
}

function getValue(com) {
	if (jQuery(com).is(":input"))
		return jQuery(com).val();
	else
		return jQuery(com).text();
}

function sumAll() {
	var i, sum = 0;
	for (i = 0; i < arguments.length; i++) {
		sum += arguments[i];
	}
	return sum;
}

function sumValue() {
	var coms, com, i, sum = 0;
	for (i = 0; i < arguments.length; i++) {
		coms = $(arguments[i]);
		coms.each(function(i, com) {
			sum += toNumber(getValue(com), 0);
		});
	}
	return sum;
}

/**
 * Call click action
 * 
 * @param id
 */
function handleClick(id) {
	var com = comById(id);
	if (com.length > 0 && !com.prop("disabled")) {
		com[0].click();
	}
}

/**
 * Handle enter key call action
 * 
 * @param e
 * @param id
 */
function handleEnter(e, id) {
	var ENTER = 13;
	event = e ? e : window.event;
	var keyCode = event.which || event.keyCode;
	if (keyCode != ENTER) {
		return;
	}

	stopEvent(e);
	handleClick(id);
}

function blankCascade(e, ids) {
	var len = arguments.length;
	if (isEmpty($(e).val()) && len > 0) {
		var com = null;
		for (i = 1; i < len; i++) {
			com = comById(arguments[i]);
			if (com.length > 0 && !com.prop("disabled")) {
				com.val("");
			}
		}
	}
}

/**
 * Active tab
 * 
 * @param tab
 * @param tabBar
 * @param tabDetail
 */
function activeTab(tab, tabBar, tabDetail) {
	tab = comById(tab);

	if (tabBar == undefined || tabBar == null || tabBar == '') {
		tabBar = tab.parent();
	} else {
		tabBar = comById(tabBar);
	}

	var tabBarId = tabBar.attr('id');
	var tabBarActive = comById(tabBarId + 'Active');
	var tabActiveId = tabBarActive.val();

	if (isEmpty(tabActiveId)) {
		tabActiveId = tabBar.attr('data-active');
	}

	var tabBarDetailActive = comById(tabBarId + 'DetailActive');
	var tabActiveDetailId = tabBarDetailActive.val();

	if (isEmpty(tabActiveDetailId)) {
		tabActiveDetailId = toString(tabBar.attr('data-detail-active'), tabActiveId + 'Detail');
	}

	var tabActive = comById(tabActiveId);
	var tabActiveDetail = comById(tabActiveDetailId);

	tabActive.removeClass('active');
	tabActiveDetail.removeClass('active');

	var tabId = tab.attr('id');
	tabDetail = toString(tabDetail, tabId + 'Detail')

	tabDetail = comById(tabDetail);

	tab.addClass('active');
	tabDetail.addClass('active');

	var tabDetailId = tabDetail.attr('id');
	tabBar.attr('data-active', tabId);
	tabBar.attr('data-detail-active', tabDetailId);
	tabBarActive.val(tabId);
	tabBarDetailActive.val(tabDetailId);
}

/**
 * Get component by id
 * 
 * @param id
 * @returns
 */
function comById(id) {
	if (typeof id == 'string' || id instanceof String) {
		if (id.indexOf('#') != 0 && id.indexOf('.') != 0) {
			id = '#' + id;
		}
	}
	return $(id);
}

/**
 * Prevent enter submit
 * 
 * @param e
 */
function preventEnterSubmit(e) {
	var ENTER = 13;
	event = e ? e : window.event;
	var keyCode = event.which || event.keyCode;

	if (keyCode != ENTER) {
		return true;
	}

	stopEvent(e);
	return false;
}

function getKeyCode(e) {
	e = e || window.event;
	return e.keyCode || e.which || e.charCode;
}
// Prevents event bubble up or any usage after this is called.
function stopEvent(e) {
	event = e ? e : window.event;
	if (event) {
		if (event.stopPropagation) {
			event.stopPropagation();
		}
		if (event.preventDefault) {
			event.preventDefault();
		}
		if (event.cancelBubble) {
			event.cancelBubble = true;
		}
		if (event.cancel) {
			event.cancel = true;
		}
		if (window.event) {
			event.returnValue = false;
		}
	}
}

// GET CURSOR POSITION
(function($) {
	$.fn.getCursorPosition = function() {
		var input = this.get(0);
		if (!input)
			return; // No (input) element found
		if ('selectionStart' in input) {
			// Standard-compliant browsers
			return input.selectionStart;
		} else if (document.selection) {
			// IE
			input.focus();
			var sel = document.selection.createRange();
			var selLen = document.selection.createRange().text.length;
			sel.moveStart('character', -input.value.length);
			return sel.text.length - selLen;
		}
	}
})(jQuery);

// SET CURSOR POSITION
(function($) {
	$.fn.setCursorPosition = function(start, end) {
		end = end ? (end < start ? start : end) : start;
		this.each(function(index, elem) {
			if (elem.setSelectionRange) {
				elem.setSelectionRange(start, end);
			} else if (elem.createTextRange) {
				var range = elem.createTextRange();
				range.collapse(true);
				range.moveEnd('character', end);
				range.moveStart('character', start);
				range.select();
			}
		});
		return this;
	}
})(jQuery);

(function($) {
	$.fn.positiveOnly = function() {
		return this.each(function() {
			$(this).keydown(function(event) {
				_keydownPositiveOnly(event);
			});
		});
	};

	function _keydownPositiveOnly(event) {
		event = event || window.event;
		// Allow: backspace(46), delete(8), tab(9), escape(27), enter(13)
		keyCode = event.keyCode || event.which || event.charCode;
		if (keyCode == 46 || keyCode == 8 || keyCode == 9 || keyCode == 27 || keyCode == 13 ||
		// Allow: decimal point(110), comma(188), period(190)
		keyCode == 110 || keyCode == 188 || keyCode == 190 ||
		// Allow: Ctrl+A, Ctrl+C, Ctrl+V, Ctrl+X
		((keyCode == 65 || keyCode == 67 || keyCode == 86 || keyCode == 88) && event.ctrlKey === true) ||
		// Allow: home, end, left, right
		(keyCode >= 32 && keyCode <= 40) || (keyCode >= 97 && keyCode <= 105)) {
			// let it happen, don't do anything
			return;
		} else {
			// Ensure that it is a number and stop the keypress
			if (event.shiftKey || (keyCode < 48 || keyCode > 57) && (keyCode < 96 || keyCode > 105)) {
				stopEvent(event);
			}
		}
	}

	$.fn.numericOnly = function() {
		return this.each(function() {
			$(this).keydown(function(event) {
				_keydownNumericOnly(event);
			});
		});
	};

	function _keydownNumericOnly(event) {
		event = event || window.event;
		keyCode = event.keyCode || event.which || event.charCode;

		// Allow: backspace(46), delete(8), tab(9), escape(27), enter(13)
		if (keyCode == 46 || keyCode == 8 || keyCode == 9 || keyCode == 27 || keyCode == 13 ||
		// Allow: subtract(109), decimal point(110), comma(188),
		// dash(189,173(Firefox)), period(190)
		keyCode == 109 || keyCode == 110 || keyCode == 188 || keyCode == 189 || keyCode == 173 || keyCode == 190 ||
		// Allow: Ctrl+A, Ctrl+C, Ctrl+V, Ctrl+X
		((keyCode == 65 || keyCode == 67 || keyCode == 86 || keyCode == 88) && event.ctrlKey === true) ||
		// Allow: home, end, left, right
		(keyCode >= 32 && keyCode <= 40) || (keyCode >= 97 && keyCode <= 105)) {
			// let it happen, don't do anything
			return;
		} else {
			// Ensure that it is a number and stop the keypress
			if (event.shiftKey || (keyCode < 48 || keyCode > 57) && (keyCode < 96 || keyCode > 105)) {
				stopEvent(event);
			}
		}
	}

	$.fn.integerOnly = function() {
		return this.each(function() {
			$(this).keydown(function(event) {
				_keydownIntegerOnly(event);
			});
		});
	};

	function _keydownIntegerOnly(event) {
		event = event || window.event;
		keyCode = event.keyCode || event.which || event.charCode;

		// Allow: delete, backspace, tab, escape, enter
		if (keyCode == 46 || keyCode == 8 || keyCode == 9 || keyCode == 27 || keyCode == 13
		// Allow: subtract(109), dash(189,173(Firefox))
		|| keyCode == 109 || keyCode == 189 || keyCode == 173 ||
		// Allow: Ctrl+A, Ctrl+C, Ctrl+V, Ctrl+X
		((keyCode == 65 || keyCode == 67 || keyCode == 86 || keyCode == 88) && event.ctrlKey === true) ||
		// Allow: home, end, left, right
		(keyCode >= 32 && keyCode <= 40) || (keyCode >= 97 && keyCode <= 105)) {
			// let it happen, don't do anything
			return;
		} else {
			// Ensure that it is a number and stop the keypress
			if (event.shiftKey || (keyCode < 48 || keyCode > 57) && (keyCode < 96 || keyCode > 105)) {
				stopEvent(event);
			}
		}
	}
})(jQuery);

/**
 * Skip process when control key
 * 
 * @param event
 * @returns {Boolean}
 */
function isNumbericKeypad(event) {
	event = event || window.event;
	var keyCode = event.keyCode || event.which || event.charCode;
	// numeric keypad
	if ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 96 && keyCode <= 105)
	// delete, backspace, tab, escape, enter
	|| keyCode == 46 || keyCode == 8 || keyCode == 9 || keyCode == 27 || keyCode == 13
	// subtract(109), decimal point(110), comma(188),
	// dash(189,173(Firefox)),
	// period(190)
	|| keyCode == 109 || keyCode == 110 || keyCode == 188 || keyCode == 189 || keyCode == 173 || keyCode == 190
	// Ctrl+A, Ctrl+C, Ctrl+V, Ctrl+X
	|| ((keyCode == 65 || keyCode == 67 || keyCode == 86 || keyCode == 88) && event.ctrlKey === true)) {
		return true;
	}

	return false;
}

/**
 * Get cursor position after format number input
 * 
 * @param pos
 * @param lenBefore
 * @param lenAfter
 * @returns
 */
function getCursorPositionNew(event, pos, lenBefore, lenAfter, numValue) {
	if (numValue && toNumber(numValue) == 0) {
		return 1;
	}

	var newPos = pos;
	event = event || window.event;
	var keyCode = event.keyCode || event.which || event.charCode;
	if (lenAfter > lenBefore) {
		if (keyCode != 8) {
			newPos = pos + 1;
		}
	} else if (lenAfter < lenBefore && (keyCode == 8 || keyCode == 46)) {
		if (pos < lenAfter) {
			newPos = pos - 1;
		} else {
			newPos = lenAfter;
		}
	}

	// console.log("keyCode=" + keyCode, " pos=" + pos, " lenBefore=" +
	// lenBefore, " lenAfter=" + lenAfter, " newPos ="
	// + newPos)
	return newPos;
}

/**
 * Synchronize width of components
 * 
 * @param className
 */
function syncWidth(cn) {
	var coms = $(cn);
	var w = 0;
	var max = 0;
	coms.each(function(i) {
		w = $(this).width();
		max = max < w ? w : max;
	});
	coms.width(max);
}

function syncHeight(cn) {
	var coms = $(cn);
	var h = 0;
	var max = 0;
	coms.each(function(i) {
		h = $(this).height();
		max = max < h ? h : max;
	});
	coms.height(max);
}

$(window).resize(function() {
	try {
		splitterVerticalResizeAll();
		splitterHorizontalResizeAll();
	} catch (e) {
		;
	}
});

/**
 * Sync width
 */
$(window).bind("resize", function() {
	fullWidth();
});

function fullWidth(cn) {
	if (cn == undefined || cn == null || cn == '') {
		cn = 'div.full-width-sync';
	}

	var coms = $(cn);
	var offset = 1;
	coms.each(function(i) {
		var com = $(this);
		var prev = com.prev();
		var parent = com.parent();

		var wPrev = prev.width();
		var lPrev = prev.position().left;
		var wParent = parent.width();
		var lParent = parent.position().left;
		var w = wParent - (lPrev - lParent + wPrev) - offset;
		com.width(w);
	});
}

function showComboItem(combo) {
	combo = $(combo);
	var isShow = toBoolean(combo.attr('data-show'));
	var block = null;
	if (isShow) {
		isShow = false;
		combo.removeClass('show-combo-item');

		block = hideDivBlock();
		block.unbind('click', hideComboItem);
	} else {
		hideComboItem();
		isShow = true;
		combo.addClass('show-combo-item');

		block = showDivBlock();
		block.bind('click', hideComboItem);
	}
	combo.attr('data-show', isShow);

}

function hideComboItem() {
	var allCombo = $("div.combo-item-box");
	allCombo.each(function(i, el) {
		el = $(el);
		if (toBoolean(el.attr('data-show'))) {
			el.removeClass('show-combo-item');
			el.attr('data-show', false);
		}
	});

	var block = hideDivBlock();
	block.unbind('click', hideComboItem);
}

function selectComboItem(id, label, value, stage, addMore) {
	var combo = comById(id);
	var comValue = comById(id + 'Value');
	var comStage = comById(id + 'Stage');
	var comAddMore = comById(id + 'AddMore');

	combo.val(label);
	comValue.val(value);
	comStage.val(stage);
	comAddMore.val(addMore);
}

function showDivBlock() {
	var block = $('#div_blocking');
	if (block == null || block.length == 0) {
		block = $('<div id="div_blocking" style="display:block; position: absolute; top: 0px; left:0px;right:0px;bottom:0px; z-index:40000;"></div>');
		$('body').append(block);
	} else {
		block.css('display', 'block');
	}
	return block;
}

function hideDivBlock() {
	var block = $('#div_blocking');
	block.css('display', 'none');
	return block;
}

/**
 * Focus next control
 * 
 * @param e
 * @param idDown
 * @param idUp
 * @param idNext
 * @param idBack
 */
function nextFocus(e, idDown, idUp, idNext, idBack) {
	var ENTER = 13;
	var UP_ARROW = 38;
	var DOWN_ARROW = 40;
	var TAB = 9;

	e = e ? e : window.event;
	var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;

	var com = null;
	if ((keyCode == ENTER && !e.shiftKey) || keyCode == DOWN_ARROW) {
		com = comById(idDown);
	} else if ((keyCode == ENTER && e.shiftKey) || keyCode == UP_ARROW) {
		com = comById(idUp);
	} else if (keyCode == TAB && !e.shiftKey) {
		com = comById(idNext);
	} else if (keyCode == TAB && e.shiftKey) {
		com = comById(idBack);
	}

	if (com.length > 0) {
		com[0].focus();
	}

	stopEvent(e);
}

function setFocus(id) {
	var e = document.getElementById(id);
	if (e && e.focus) {
		e.focus();
	}
}

function nextCellFocus(e, thisCell, nextCell, backCell) {
	var ENTER = 13;
	var UP_ARROW = 38;
	var DOWN_ARROW = 40;
	var TAB = 9;

	e = e ? e : window.event;
	var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;

	var comCell = $(thisCell);
	var row = comCell.parentsUntil("tbody");
	var comNext = null;
	if ((keyCode == ENTER && !e.shiftKey) || keyCode == DOWN_ARROW) {
		row = row.next();
		comNext = row.find(thisCell);
	} else if ((keyCode == ENTER && e.shiftKey) || keyCode == UP_ARROW) {
		row = row.prev();
		comNext = row.find(thisCell);
	} else if (keyCode == TAB && !e.shiftKey) {
		comNext = row.find(nextCell);
	} else if (keyCode == TAB && e.shiftKey) {
		comNext = row.find(backCell);
	}

	if (comNext != null && comNext.length > 0 && comNext[0].focus) {
		comNext[0].focus();
	}

	stopEvent(e);
}

/**
 * Open new window
 * 
 * @param url
 */
function openNewWindow(parentWindow, srcURL, windowName, handleReturn, isModal, height, width) {
	var options = "";
	options += 'location=no,';
	options += 'dependent=yes';
	options += 'menubar=no,';
	options += 'status=yes,';
	options += 'titlebar=no,';
	options += 'resizable=yes,';
	options += 'scrollbars=no,';
	options += 'left=0px,';
	options += 'top=0px';

	if (height > 0) {
		options += 'height=' + height + 'px';
	}

	if (width > 0) {
		options += 'width=' + width + 'px';
	}

	// alert("1 = " + srcURL);

	// open new window, but wait for parent focus block DIV
	setTimeout(function() {
		var childWindow = findWindow(windowName);
		if (childWindow == null || childWindow.closed) {
			windows[name] = window.open(srcURL, windowName, options);
		} else {
			childWindow.focus();
		}
	}, 100);
}

/**
 * 
 * @param handleReturn
 */
function returnFromDialog(handleReturn) {
	handleClick(handleReturn);
}

function visibleBlocking() {

}

var windows = {};
function openWindow1(url, name, features) {
	windows[name] = window.open(url, name, features);
	return windows[name];
}

function findWindow(name) {
	return windows[name];
}

function closeWindow(name) {
	var window = windows[name];
	if (window) {
		window.close();
		delete windows[name];
	}
}

function showPrompt(title, defaultValue, id, callback) {
	var inputPrompt = prompt(title, defaultValue);

	if (inputPrompt != null) {
		if (typeof id == 'string' || id instanceof String) {
			if (id.indexOf('#') != 0 && id.indexOf('.') != 0) {
				id = '#' + id;
			}
		}
		$(id).val(inputPrompt);

		alert(inputPrompt);

		if (callback) {
			callback(inputPrompt);
		}
		return true;
	}

	return false;
}

function showDropdownMenu(e, menu, trigger) {
	menu = $(menu);
	var isShow = toBoolean(menu.attr('data-show'));
	var block = null;
	if (isShow) {
		isShow = false;
		menu.addClass('hide');

		block = hideDivBlock();
		block.unbind('click', hideDropdownMenu);
	} else {
		hideDropdownMenu();

		isShow = true;
		menu.removeClass('hide');

		block = showDivBlock();
		block.bind('click', hideDropdownMenu);
	}

	menu.attr('data-show', isShow);
	stopEvent(e);
}

function hideDropdownMenu(e) {
	var allMenu = $(".dropdown-menu");
	allMenu.each(function(i, el) {
		el = $(el);
		if (toBoolean(el.attr('data-show'))) {
			el.addClass('hide');
			el.attr('data-show', false);
		}
	});

	var block = hideDivBlock();
	block.unbind('click', hideDropdownMenu);
	stopEvent(e);
}

/**
 * Initial editor
 */
function initEditor(id, options) {
	var textEditor = comById(id);
	if (textEditor == null || textEditor.lenght == 0) {
		return;
	}

	try {
		settings = {
			mode : "none",
			statusbar : false,
			setup : function(editor) {
				editor.on('change', function() {
					editor.save();
				});
			},
			formats : {
				bold : {
					inline : 'b'
				},
				italic : {
					inline : 'i'
				}
			},
			autoresize_bottom_margin : "20",
			plugins : [ "advlist autolink lists link image charmap preview anchor", "searchreplace fullscreen fullpage autoresize", "table contextmenu paste" ],
			toolbar : "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | fontsizeselect | forecolor backcolor"
		}

		if (options) {
			var i, l, name, value;
			for (name in options) {
				if (settings.hasOwnProperty(name)) {
					value = options[name];
					if (value !== undefined) {
						settings[name] = value;
					}
				}
			}
		}

		tinymce.init(settings);

		var editor = tinymce.get(id);
		console.log("initEditor editor = ", editor);
		if (!editor) {
			tinymce.execCommand('mceAddEditor', false, id);

			// Jsf ajax reload
			jsf.ajax.addOnEvent(function(data) {
				switch (data.status) {
				case "begin":
					try {
						var editor = tinymce.get(id);
						if (editor) {
							tinymce.execCommand('mceRemoveEditor', false, id);
						}
					} catch (e) {
						;
					}
					break;

				case "complete":
					break;

				case "success":
					try {
						tinymce.execCommand('mceAddEditor', false, id);
					} catch (e) {
						;
					}
					break;
				}
			});
		}
	} catch (e) {
		console.log("Error = ", e);
	}
}

function initEditorReadonly(id, options) {
	var textEditor = comById(id);
	if (textEditor == null || textEditor.lenght == 0) {
		return;
	}

	try {
		settings = {
			mode : "none",
			readonly : true,
			toolbar : false,
			menubar : false,
			statusbar : false,
			autoresize_bottom_margin : "10",
			plugins : [ "fullscreen fullpage autoresize" ]
		};

		if (options) {
			var i, l, name, value;
			for (name in options) {
				if (settings.hasOwnProperty(name)) {
					value = options[name];
					if (value !== undefined) {
						settings[name] = value;
					}
				}
			}
		}

		tinymce.init(settings);

		var editor = tinymce.get(id);
		if (!editor) {
			tinymce.execCommand('mceAddEditor', false, id);
			jsf.ajax.addOnEvent(function(data) {
				switch (data.status) {
				case "begin":
					try {
						var editor = tinymce.get(id);
						if (editor) {
							tinymce.execCommand('mceRemoveEditor', false, id);
						}
					} catch (e) {
						;
					}
					break;
				case "complete":
					break;
				case "success":
					try {
						tinymce.execCommand('mceAddEditor', false, id);
					} catch (e) {
						;
					}
					break;
				}
			});
		}
	} catch (e) {
		;
	}
}

var _ajaxStarDate = 0;
function monitorAjaxPerformance() {
	jsf.ajax.addOnEvent(function(data) {
		switch (data.status) {
		case "begin":
			_ajaxStarDate = (new Date()).getTime();
			console.log("monitorAjaxPerformance begin: " + new Date());
			break;
		case "complete":
			console.log("monitorAjaxPerformance complete: " + ((new Date()).getTime() - _ajaxStarDate));
			break;
		case "success":
			console.log("monitorAjaxPerformance success: " + ((new Date()).getTime() - _ajaxStarDate));
			break;
		}
	});
}

$(function() {
	monitorAjaxPerformance();
});

function warehouseType(id, op) {
	$(id + " option").each(function() {
		var item = $(this);
		if (item.val() == 3 || item.val() == 4 || item.val() == 5 || item.val() == 6 || (op == 'out' && item.val() == 9)) {
			item.css('color', '#f00');
		} else {
			item.css('color', '#00f');
		}
	});
	$(id).prop("title", " Màu đỏ: Kho logic \r\n Màu xanh: Kho vật lý");
}

/* This all select */
$(function() {
	// set all select on focus
	$(document).on('focus', 'input[type=text]', function() {
		$(this).select();
	});
	
	// set resize all menu autocomplete
	jQuery.ui.autocomplete.prototype._resizeMenu = function () {
		var myAutocomplete = this.menu.element;
		myAutocomplete.outerWidth(this.element.outerWidth() > 200 ? this.element.outerWidth() : 500);	
		myAutocomplete.css({
						'max-height': '300px', 
						'overflow-y':'auto',
						'overflow-x':'hidden'
				});
			};
});

/* Prevent double submit */
function preventDoubleSubmit() {
	$("form").bind("submit", function() {
		showBlocking();
	});

	jsf.ajax.addOnEvent(function(data) {
		switch (data.status) {
		case "begin":
			showBlocking();
			break;
		case "complete":

			break;
		case "success":
			hideBlocking();
			break;
		}
	});
}

showBlocking();
$(function() {
	preventDoubleSubmit();
	hideBlocking();
});

function showBlocking() {
	var blocking = $("#_submitBlocking");
	blocking = blocking.length == 0 ? createBlocking() : blocking;
	blocking.addClass("blocking");
}

function hideBlocking() {
	var blocking = $("#_submitBlocking");
	blocking.removeClass("blocking");
}

function createBlocking() {
	var blocking = $('<div id="_submitBlocking" class="prevent-double-submit"><div class="block-waiting"><span></span></div></div>');
	$("body").append(blocking);
	return blocking;
}
