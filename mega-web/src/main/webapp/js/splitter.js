//$(document).ready(function() {
//	splitterInit();
//});

function splitterInit() {
	var splittersVertical = $('div.splitter.vertical');
	splittersVertical.each(function(i, splitter) {
		splitterVertical(splitter);
	});

	var splittersHorizontal = $('div.splitter.horizontal');
	splittersHorizontal.each(function(i, splitter) {
		splitterHorizontal(splitter);
	});

	// Resize window handle
	$(window).bind("resize", function() {
		splitterVerticalResizeAll();
		splitterHorizontalResizeAll();
	});
}

// Initial splitter
function splitterVerticalInit(splitter) {
	splitter = $(splitter);
	// Component left of splitter
	var comLeft = $('#' + splitter.attr('data-left'));
	// Component right of splitter
	var comRight = $('#' + splitter.attr('data-right'));
	// left,right - Position of splitter on screen
	var location = splitter.attr('data-location');
	// Position minimum of splitter
	var min = toNumber(splitter.attr('data-min'), -1);
	// Position maximum of splitter
	var max = toNumber(splitter.attr('data-max'), -1);
	// left,right - Direction move if splitter
	var direction = splitter.attr('data-direction');
	// left,right,both - Place of splitter
	var place = splitter.attr('data-place');
	// Current position of splitter
	var position = toNumber(splitter.attr('data-position'), -1);
	// Step move splitter to
	var step = toNumber(splitter.attr('data-step'), 0);

	var posLeft = comLeft.position();
	var posRight = comRight.position();

	var topLeft = posLeft.top;
	var leftLeft = posLeft.left;
	var heightLeft = comLeft.outerHeight();
	var widthLeft = comLeft.outerWidth();

	var topRight = posRight.top;
	var leftRight = posRight.left;
	var heightRight = comRight.outerHeight();
	var widthRight = comRight.outerWidth();

	var widthParent = comRight.parents().width();

	var top = topLeft > topRight ? topLeft : topRight;
	var height = heightLeft > heightRight ? heightLeft : heightRight;
	var width = splitter.outerWidth();
	var offset = 0;

	if (location == 'left') {
		place = toString(place, 'right');
		min = min < 0 ? leftLeft : min;
		max = max < min ? leftRight : max;
		offset = place == 'both' ? (width / 2) : place == 'left' ? width : 0;
		position = position < 0 ? (widthLeft > 0 ? max - offset : min) : (position < min ? min : (position > max ? max
				: position));
		direction = toString(direction, (position >= min ? 'right' : 'left'));
		step = step > 0 ? step : widthLeft;
	} else {
		place = toString(place, 'left');
		min = min < 0 ? (widthParent - (leftRight + widthRight)) : min;
		max = max < 0 ? leftRight : max;
		offset = place == 'both' ? (width / 2) : place == 'left' ? 0 : width;
		position = widthRight > 0 ? max - offset : min;
		position = position < 0 ? (widthRight > 0 ? max - offset : min) : (position < min ? min : (position > max ? max
				: position));
		direction = toString(direction, (position >= min ? 'left' : 'right'));
		step = step > 0 ? step : widthRight;
	}

	// Set position for splitter
	splitter.css('top', top + 'px');
	splitter.css('height', height + 'px');

	// Save status value
	splitter.attr('data-min', min);
	splitter.attr('data-max', max);
	splitter.attr('data-direction', direction);
	splitter.attr('data-place', place);
	splitter.attr('data-position', position);
	splitter.attr('data-step', step);
	splitter.attr('data-offset', offset);
	splitter.attr('data-top', top);
	splitter.attr('data-height', height);

	// Initial position splitter
	splitterVerticalPosition(splitter, position);
}

/**
 * Resize all splitter vertical
 */
function splitterVerticalResizeAll() {
	var splittersVertical = $('div.splitter.vertical');
	splittersVertical.each(function(i, splitter) {
		splitterVerticalResize(splitter);
	});
}
/**
 * Resize splitter vertical
 * 
 * @param splitter
 */
function splitterVerticalResize(splitter) {
	splitter = $(splitter);
	var splitterIcon = getSplitterIcon(splitter);

	// Component left of splitter
	var comLeft = $('#' + splitter.attr('data-left'));
	// Component right of splitter
	var comRight = $('#' + splitter.attr('data-right'));

	var posLeft = comLeft.position();
	var posRight = comRight.position();

	var topLeft = posLeft.top;
	var heightLeft = comLeft.outerHeight();
	var topRight = posRight.top;
	var heightRight = comRight.outerHeight();

	// Set position for splitter
	var top = topLeft > topRight ? topLeft : topRight;
	var height = heightLeft > heightRight ? heightLeft : heightRight;
	splitter.css('top', top + 'px');
	splitter.css('height', height + 'px');

	// Set position icon splitter
	var heightIcon = splitterIcon.height();
	heightIcon = heightIcon == 0 ? 24 : heightIcon;
	var topIcon = (height - heightIcon) / 2;
	splitterIcon.css('top', topIcon + 'px');

	// Save cache
	splitter.attr('data-height', height);
}

/**
 * Prevent change position vertical
 * 
 * @param splitter
 */
function preventChangePositionVertical(splitter) {
	splitter = $(splitter);
	var top = toNumber(splitter.attr('data-top'));
	var height = toNumber(splitter.attr('data-height'));

	// Reset position vertical
	splitter.css("top", top + 'px');
	splitter.css("height", height + 'px');
}

/**
 * Setup splitter vertical event handler
 * 
 * @param splitter
 */
function splitterVertical(splitter) {
	splitter = $(splitter);
	var splitterIcon = getSplitterIcon(splitter);
	// Initial splitter
	splitterVerticalInit(splitter);
	// Set position for icon of splitter
	splitterVerticalResize(splitter);

	splitter.click(function(event) {
		splitterVerticalClick(this);
		event.stopPropagation();
	});

	splitter.draggable({
		drag : function(event) {
			splitterVerticalDrag(this, false);
			event.stopPropagation();
		},
		stop : function(event) {
			splitterVerticalDrag(this, true);
			event.stopPropagation();
		}
	});
}

var cancelClick = false;
/**
 * Handle click splitter
 * 
 * @param splitter
 */
function splitterVerticalClick(splitter) {
	if (cancelClick) {
		cancelClick = false;
		return;
	}
	splitter = $(splitter);
	// left,right - Position of splitter on screen
	var location = splitter.attr('data-location');
	// Position minimum of splitter
	var min = toNumber(splitter.attr('data-min'));
	// Position maximum of splitter
	var max = toNumber(splitter.attr('data-max'));
	// left,right - Direction move if splitter
	var direction = splitter.attr('data-direction');
	// Current position of splitter
	var position = toNumber(splitter.attr('data-position'));
	// Step move splitter to
	var step = toNumber(splitter.attr('data-step'));
	// Time for splitter move to new position in milliseconds
	var time = toNumber(splitter.attr('data-time'), -1);

	if (time < 0) {
		time = 5000;
		splitter.attr('data-time', time);
	}

	// Calculator new position of splitter

	if (location == 'left') {
		if (direction == 'left') {
			position = position - step;
		} else {
			position = position + step;
		}
	} else {
		if (direction == 'left') {
			position = position + step;
		} else {
			position = position - step;
		}
	}
	splitterVerticalPosition(splitter, position);
	// Resize for splitter horizontal
	splitterHorizontalResizeAll();

	// var subStep = 5;
	// var count = Math.ceil(step / subStep)
	// var deplay = Math.ceil(time / count);
	//
	// var process = 0;
	// var interval = 0;
	// do {
	// process += subStep;
	//
	// if (location == 'left') {
	// if (direction == 'left') {
	// position = position - subStep;
	// ;
	// } else {
	// position = position + subStep;
	// }
	// } else {
	// if (direction == 'right') {
	// position = position + subStep;
	// } else {
	// position = position - subStep;
	// }
	// }
	//
	// setTimeout(splitterVerticalPosition(splitter, position), 10000);
	// interval += deplay;
	//		
	// //alert ('1-----' + process + " : " + position + " : " + interval);
	// } while (process < step)

}

/**
 * Handle drag splitter
 * 
 * @param splitter
 */
function splitterVerticalDrag(splitter, stoped) {
	splitter = $(splitter);
	var position = splitter.position();

	// left,right - Position of splitter on screen
	var location = splitter.attr('data-location');
	if (location == 'left') {
		splitterVerticalPosition(splitter, position.left);
	} else {
		splitterVerticalPosition(splitter, splitter.parents().width() - position.left);
	}

	if (stoped) {
		cancelClick = true;
		preventChangePositionVertical(splitter);
		// Resize for splitter horizontal
		splitterHorizontalResizeAll();
	}
}

/**
 * Set new position of splitter
 * 
 * @param splitter
 * @param step
 */
function splitterVerticalPosition(splitter, position) {
	splitter = $(splitter);
	// Component left of splitter
	var comLeft = $('#' + splitter.attr('data-left'));
	// Component right of splitter
	var comRight = $('#' + splitter.attr('data-right'));
	// Position minimum of splitter
	var min = toNumber(splitter.attr('data-min'));
	// Position maximum of splitter
	var max = toNumber(splitter.attr('data-max'));
	// left,right - Position of splitter on screen
	var location = splitter.attr('data-location');
	// left,right - Direction move if splitter
	var direction = splitter.attr('data-direction');
	// Current position of splitter
	var currPos = toNumber(splitter.attr('data-position'));
	var offset = toNumber(splitter.attr('data-offset'), 0);

	// Calculate new position
	var isMin = false;
	var isMax = false;
	if (position <= min) {
		isMin = true;
		position = min;
		direction = location == 'left' ? 'right' : 'left';
	} else if (position >= max) {
		isMax = true;
		position = max;
		direction = location == 'left' ? 'left' : 'right';
	} else {
		if (position > currPos) {
			direction = location == 'left' ? 'right' : 'left';
		} else if (position < currPos) {
			direction = location == 'left' ? 'left' : 'right';
		}
	}

	// Show/Hide
	splitterShowHide(splitter, isMin, isMax);

	// Set new position
	if (location == 'left') {
		comLeft.css("width", position + 'px');
		comRight.css("left", (position + offset) + 'px');
		splitter.css("left", position + 'px');
	} else {
		comRight.css("width", position + 'px');
		comLeft.css("right", (position + offset) + 'px');
		splitter.css("right", position + 'px');
	}

	// Save new status splitter
	splitter.attr('data-direction', direction);
	splitter.attr('data-position', position);
}

// Initial splitter horizontal
function splitterHorizontalInit(splitter) {
	splitter = $(splitter);
	// Component above of splitter
	var comAbove = $('#' + splitter.attr('data-above'));
	// Component bottom of splitter
	var comUnder = $('#' + splitter.attr('data-under'));
	// top,bottom - Position of splitter on screen
	var location = splitter.attr('data-location');
	// Position minimum of splitter
	var min = toNumber(splitter.attr('data-min'), -1);
	// Position maximum of splitter
	var max = toNumber(splitter.attr('data-max'), -1);
	// up,down - Direction move if splitter
	var direction = splitter.attr('data-direction');
	// above,under,both - Place of splitter
	var place = splitter.attr('data-place');
	// Current position of splitter

	var splitterId = splitter.attr('id');
	var splittePosition = $('#' + splitterId + 'Position');
	var positionCache = toNumber(splittePosition.val(), -1);
	var position = 0;
	if (positionCache < 0) {
		position = toNumber(splitter.attr('data-position'), -1);
	} else {
		position = positionCache;
	}

	// Step move splitter to
	var step = toNumber(splitter.attr('data-step'), 0);

	var posAbove = comAbove.position();
	var posUnder = comUnder.position();

	var topAbove = posAbove.top;
	var leftAbove = posAbove.left;
	var heightAbove = comAbove.outerHeight();
	var widthAbove = comAbove.outerWidth();

	var topUnder = posUnder.top;
	var leftUnder = posUnder.left;
	var heightUnder = comUnder.outerHeight();
	var widthUnder = comUnder.outerWidth();

	var heightParent = comUnder.parents().height();

	var left = leftAbove > leftUnder ? leftAbove : leftUnder;
	var width = widthAbove > widthUnder ? widthAbove : widthUnder;
	var height = splitter.outerHeight();
	var offset = 0;

	if (location == 'top') {
		place = toString(place, 'under');
		min = min < 0 ? topAbove : min;
		max = max < min ? topUnder : max;
		offset = place == 'both' ? (height / 2) : place == 'top' ? height : 0;
		position = position < 0 ? (heightAbove > 0 ? max - offset : min) : (position < min ? min
				: (position > max ? max : position));
		direction = toString(direction, (position >= min ? 'down' : 'up'));
		step = step > 0 ? step : heightAbove;
	} else {
		place = toString(place, 'above');
		min = min < 0 ? (heightParent - (topUnder + heightUnder)) : min;
		max = max < 0 ? topUnder : max;
		offset = place == 'both' ? (height / 2) : place == 'top' ? 0 : height;
		position = heightUnder > 0 ? max - offset : min;
		position = position < 0 ? (heightUnder > 0 ? max - offset : min) : (position < min ? min
				: (position > max ? max : position));
		direction = toString(direction, (position >= min ? 'up' : 'down'));
		step = step > 0 ? step : heightUnder;
	}

	// Set position for splitter
	splitter.css('left', left + 'px');
	splitter.css('width', width + 'px');

	// Save status value
	splitter.attr('data-min', min);
	splitter.attr('data-max', max);
	splitter.attr('data-direction', direction);
	splitter.attr('data-place', place);
	splitter.attr('data-position', position);
	splitter.attr('data-step', step);
	splitter.attr('data-offset', offset);
	splitter.attr('data-left', left);
	splitter.attr('data-width', width);

	// Initial position splitter
	splittePosition.val(position);
	splitterHorizontalPosition(splitter, position);
}

/**
 * Resize all splitter horizontal
 */
function splitterHorizontalResizeAll() {
	var splittersHorizontal = $('div.splitter.horizontal');
	splittersHorizontal.each(function(i, splitter) {
		splitterHorizontalResize(splitter);
	});
}

/**
 * Resize splitter horizontal
 * 
 * @param splitter
 */
function splitterHorizontalResize(splitter) {
	splitter = $(splitter);
	var splitterIcon = getSplitterIcon(splitter);

	// Component above of splitter
	var comAbove = $('#' + splitter.attr('data-above'));
	// Component bottom of splitter
	var comUnder = $('#' + splitter.attr('data-under'));

	var posAbove = comAbove.position();
	var posUnder = comUnder.position();

	var leftAbove = posAbove.left;
	var widthAbove = comAbove.outerWidth();
	var leftUnder = posUnder.left;
	var widthUnder = comUnder.outerWidth();

	// Set position for splitter
	var left = leftAbove > leftUnder ? leftAbove : leftUnder;
	var width = widthAbove > widthUnder ? widthAbove : widthUnder;
	splitter.css('left', left + 'px');
	splitter.css('width', width + 'px');

	// Set position for icon of splitter
	var width = splitter.width();
	var widthIcon = splitterIcon.width();
	widthIcon = widthIcon == 0 ? 24 : widthIcon;

	var left = (width - widthIcon) / 2;
	splitterIcon.css('left', left + 'px');

	// Save cache
	splitter.attr('data-width', width);
}

/**
 * Prevent change position horizontal
 * 
 * @param splitter
 */
function preventChangePositionHorizontal(splitter) {
	splitter = $(splitter);
	var left = toNumber(splitter.attr('data-left'));
	var width = toNumber(splitter.attr('data-width'));

	// Reset position vertical
	splitter.css("left", left + 'px');
	splitter.css("width", width + 'px');
}

/**
 * Setup splitter horizontal event handler
 * 
 * @param splitter
 */
function splitterHorizontal(splitter) {
	splitter = $(splitter);
	var splitterIcon = getSplitterIcon(splitter);
	// Initial splitter
	splitterHorizontalInit(splitter);
	// Set position for icon of splitter
	splitterHorizontalResize(splitter);

	splitter.click(function(event) {
		splitterHorizontalClick(this);
		event.stopPropagation();
	});

	splitter.draggable({
		drag : function(event) {
			splitterHorizontalDrag(this, false);
			event.stopPropagation();
		},
		stop : function(event) {
			splitterHorizontalDrag(this, true);
			event.stopPropagation();
		}
	});
}

/**
 * Handle click splitter
 * 
 * @param splitter
 */
function splitterHorizontalClick(splitter) {
	if (cancelClick) {
		cancelClick = false;
		return;
	}
	splitter = $(splitter);
	// left,right - Position of splitter on screen
	var location = splitter.attr('data-location');
	// Position minimum of splitter
	var min = toNumber(splitter.attr('data-min'));
	// Position maximum of splitter
	var max = toNumber(splitter.attr('data-max'));
	// left,right - Direction move if splitter
	var direction = splitter.attr('data-direction');
	// Current position of splitter
	var position = toNumber(splitter.attr('data-position'));
	// Step move splitter to
	var step = toNumber(splitter.attr('data-step'));
	// Time for splitter move to new position in milliseconds
	var time = toNumber(splitter.attr('data-time'), -1);

	if (time < 0) {
		time = 5000;
		splitter.attr('data-time', time);
	}

	// Calculator new position of splitter
	if (location == 'top') {
		if (direction == 'up') {
			position = position - step;
		} else {
			position = position + step;
		}
	} else {
		if (direction == 'up') {
			position = position + step;
		} else {
			position = position - step;
		}
	}
	splitterHorizontalPosition(splitter, position);
	// Resize for splitter vertical
	splitterVerticalResizeAll();
}

/**
 * Handle drag splitter
 * 
 * @param splitter
 */
function splitterHorizontalDrag(splitter, stoped) {
	splitter = $(splitter);
	var position = splitter.position();

	// left,right - Position of splitter on screen
	var location = splitter.attr('data-location');
	if (location == 'top') {
		splitterHorizontalPosition(splitter, position.top);
	} else {
		splitterHorizontalPosition(splitter, splitter.parents().height() - position.top);
	}

	if (stoped) {
		cancelClick = true;
		preventChangePositionHorizontal(splitter);
		// Resize for splitter vertical
		splitterVerticalResizeAll();
	}
}

/**
 * Set new position of splitter
 * 
 * @param splitter
 * @param step
 */
function splitterHorizontalPosition(splitter, position) {
	splitter = $(splitter);
	// Component above of splitter
	var comAbove = $('#' + splitter.attr('data-above'));
	// Component under of splitter
	var comUnder = $('#' + splitter.attr('data-under'));
	// Position minimum of splitter
	var min = toNumber(splitter.attr('data-min'));
	// Position maximum of splitter
	var max = toNumber(splitter.attr('data-max'));
	// top,bottom - Position of splitter on screen
	var location = splitter.attr('data-location');
	// up,down - Direction move of splitter
	var direction = splitter.attr('data-direction');
	// above,under,both - Place of splitter
	var place = splitter.attr('data-place');
	// Current position of splitter
	var splitterId = splitter.attr('id');
	var splittePosition = $('#' + splitterId + 'Position');
	var positionCache = toNumber(splittePosition.val());
	var currPos = 0;
	if (positionCache < 0) {
		currPos = toNumber(splitter.attr('data-position'));
	} else {
		currPos = positionCache;
	}

	var offset = toNumber(splitter.attr('data-offset'), 0);

	// Calculate new position
	var isMin = false;
	var isMax = false;
	if (position <= min) {
		isMin = true;
		position = min;
		direction = location == 'top' ? 'down' : 'up';
	} else if (position >= max) {
		isMax = true;
		position = max;
		direction = location == 'top' ? 'up' : 'down';
	} else {
		if (position > currPos) {
			direction = location == 'top' ? 'down' : 'up';
		} else if (position < currPos) {
			direction = location == 'top' ? 'up' : 'down';
		}
	}

	// Show/Hide
	splitterShowHide(splitter, isMin, isMax);

	// Set new position
	if (location == 'top') {
		comAbove.css("height", position + 'px');
		comUnder.css("top", (position + offset) + 'px');
		splitter.css("top", position + 'px');
	} else {
		comUnder.css("height", position + 'px');
		comAbove.css("bottom", (position + offset) + 'px');
		splitter.css("bottom", position + 'px');
	}

	// Save new status splitter
	splitter.attr('data-direction', direction);
	splitter.attr('data-position', position);
	splittePosition.val(position);
}

/**
 * Create icon for splitter
 * 
 * @param splitter
 * @returns
 */
function getSplitterIcon(splitter) {
	splitter = $(splitter);
	var splitterIcon = splitter.children('div.splitter-icon');
	if (splitterIcon == null || splitterIcon.length == 0) {
		splitterIcon = $('<div style="display:none;"></div>');
		splitterIcon.addClass("splitter-icon");
		splitterIcon.append('<div class="splitter-icon-inner"></div>', '<div class="splitter-icon-inner"></div>',
				'<div class="splitter-icon-inner"></div>');

		// Add icon for splitter
		splitter.append(splitterIcon);
	} else {
		var splitterIconInner = splitterIcon.children('div.splitter-icon-inner');
		if (splitterIconInner == null || splitterIconInner.length == 0) {
			splitterIcon.append('<div class="splitter-icon-inner"></div>', '<div class="splitter-icon-inner"></div>',
					'<div class="splitter-icon-inner"></div>');
		}
	}

	var showIcon = toBoolean(splitter.attr('data-show-icon'));
	if (showIcon) {
		splitterIcon.css("display", "none");
	}

	return splitterIcon;
}

function splitterShowHide(splitter, isMin, isMax) {
	splitter = $(splitter);
	var showHideIds = toString(splitter.attr('data-showhide'), '').split(' ');
	var showShowIds = toString(splitter.attr('data-showshow'), '').split(' ');

	var len = showHideIds.length;
	for (var i = 0; i < len; i++) {
		var com = comById(showHideIds[i]);
		if (isMin) {
			com.css("visibility", "visible");
		} else {
			com.css("visibility", "hidden");
		}
	}

	len = showShowIds.length;
	for (var i = 0; i < len; i++) {
		var com = comById(showShowIds[i]);
		if (isMax) {
			com.css("visibility", "visible");
		} else {
			com.css("visibility", "hidden");
		}
	}
}
