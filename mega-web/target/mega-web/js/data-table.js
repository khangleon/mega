$(document).ready(function() {
	initDataTable();
});

function initDataTable() {
	var divTables = $('div.table');
	divTables.each(function(i, divTable) {
		divTable = $(divTable);
		divTable.scroll(function() {
			var div = $(this);
			var colHeader = div.find('table.table > thead > tr > th > div.col-header');
			var rowLeft = div.find('table.table > tbody > tr > td > div.row-header-left');
			// var colLeftLast = div.find('table.table > thead > tr > th >
			// div.row-header-left.last');
			// var rowLeftLast = div.find('table.table > tbody > tr > td >
			// div.row-header-left.last');
			var colRowHeader = divTable.find('table.table > thead > tr > th > div.col-header.row-header-left');

			// Fix position header
			colHeader.css('top', this.scrollTop + 'px');
			rowLeft.css('left', this.scrollLeft + 'px');
			// colLeftLast.css('left', this.scrollLeft + 'px');
			// rowLeftLast.css('left', this.scrollLeft + 'px');

			var curScrollLeft = this.scrollLeft;
			colRowHeader.each(function(i, el) {
				el = $(el);
				el.css('left', (curScrollLeft + toNumber(el.attr('data-position'), 0)) + 'px');

			});

			// Set border for last
			if (this.scrollLeft > 0) {
				// colLeftLast.css('border-right-width', '1px');
				// rowLeftLast.css('border-right-width', '1px');
				rowLeft.css('border-right-width', '1px');
				colRowHeader.css('border-right-width', '1px');
			} else {
				// colLeftLast.css('border-right-width', '0px');
				// rowLeftLast.css('border-right-width', '0px');
				rowLeft.css('border-right-width', '0px');
				colRowHeader.css('border-right-width', '0px');
			}
		});

		if (isNotEmpty(divTable.attr('data-current-hidden'))) {
			hideColumnTable(divTable, divTable.attr('data-current-hidden'), false);
		} else {
			syncHeaderTable(divTable);
		}
		
		// Setup next focus
		nextFocusTable(divTable);
	});
}

function syncHeaderTable(divTable) {
	divTable = comById(divTable);
	// Synchronize height header
	var colHeader = divTable.find('table.table > thead > tr > th > div.col-header');
	var rowLeft = divTable.find('table.table > tbody > tr > td > div.row-header-left');

	var colRowHeader = divTable.find('table.table > thead > tr > th > div.col-header.row-header-left');

	var max = 0;
	var wTable = 0;
	colHeader.each(function(i, el) {
		el = $(el);
		var h = el.height();
		max = max < h ? h : max;

		if (!el.hasClass("hide")) {
			wTable += el.width();
		}

	});

	var offset = 0;
	colHeader.css("height", max + 'px');
	divTable.find('table.table > thead').css("height", (max + offset) + 'px');
	divTable.find('table.table').attr("width", wTable);

	rowLeft.each(function(i, el) {
		el = $(el);

		// Cache position default
		var pos = toNumber(el.attr('data-position'), 0);
		pos = pos > 0 ? pos : el.position().left;
		el.attr('data-position', pos);

		var h = el.parent().height();
		h = h < 19 ? 19 : h;
		el.css("height", h + 'px');
	});

	colRowHeader.each(function(i, el) {
		el = $(el);
		// Cache position default
		var pos = toNumber(el.attr('data-position'), 0);
		pos = pos > 0 ? pos : el.position().left;
		el.attr('data-position', pos);

	});
}

function hideColumnTable(divTable, classname, showHidden) {
	divTable = comById(divTable);
	var colHead = divTable.find('table.table > thead > tr > th > .' + classname);
	var colBody = divTable.find('table.table > tbody > tr > td > .' + classname);
	var colFoot = divTable.find('table.table > tfoot > tr > td > .' + classname);

	showHidden = toBoolean(showHidden, true);
	if (showHidden) {
		showColumnTable(divTable, divTable.attr('data-current-hidden'));
	}

	divTable.attr('data-current-hidden', classname);

	colHead.each(function(i, el) {
		el = $(el);
		el.addClass('hide');
		el.parent().addClass('hide');
	});

	colBody.each(function(i, el) {
		el = $(el);
		el.addClass('hide');
		el.parent().addClass('hide');
	});

	colFoot.each(function(i, el) {
		el = $(el);
		el.addClass('hide');
		el.parent().addClass('hide');
	});

	syncHeaderTable(divTable);
}

function showColumnTable(divTable, classname) {
	divTable = comById(divTable);
	classname = toString(classname, divTable.attr('data-current-hidden'));

	var colHead = divTable.find('table.table > thead > tr > th > .' + classname);
	var colBody = divTable.find('table.table > tbody > tr > td > .' + classname);
	var colFoot = divTable.find('table.table > tfoot > tr > td > .' + classname);

	colHead.each(function(i, el) {
		el = $(el);
		el.removeClass('hide');
		el.parent().removeClass('hide');
	});

	colBody.each(function(i, el) {
		el = $(el);
		el.removeClass('hide');
		el.parent().removeClass('hide');
	});

	colFoot.each(function(i, el) {
		el = $(el);
		el.removeClass('hide');
		el.parent().removeClass('hide');
	});

	syncHeaderTable(divTable);
}

function nextFocusTable(divTable) {
	divTable = $(divTable);
	var divId = divTable.attr('id');
	var rowFocus = $('#' + divId + 'RowFocus');
	var colFocus = $('#' + divId + 'ColFocus');
	
	var rowIndexFocus = toNumber(rowFocus.val(), 0);
	var colIndexFocus = toNumber(colFocus.val(), 0);
	
	var rows = divTable.find('table.table > tbody > tr');
	var cols = null;
	
	var rowLen = rows.length;
	var colLen = 0;
	var input = null;
	var row = null;
	var col = null;
	for (var i = 0; i < rowLen; i++) {
		row = $(rows[i]);
		cols = row.find('td');
		colLen = cols.length;
		
		for (var j = 0; j < colLen; j++) {
			col = $(cols[j]);
			input = col.find('input');
			
			input.bind( "focus", function() {
				rowFocus.val(i);
				colFocus.val(j);
				
			});
		}
		
	}
}