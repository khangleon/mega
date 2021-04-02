package org.mega.dto.wrk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.mega.common.Lib;
import org.mega.constant.Constant;
import org.mega.dao.entity.Event;
import org.mega.dto.core.Dto;
import org.mega.dto.core.SelectItem;

public class WRK011Dto extends Dto implements Serializable {
	private static final long serialVersionUID = -354892763367944187L;

	public WRK011Dto() {
		super();
	}

	private String eventGuestValue;
	private List<SelectItem> visibilityItems;
	private List<SelectItem> statusItems;
	private List<SelectItem> priorityItems;
	private List<SelectItem> eventTypeItems;
	private List<SelectItem> repeatTypeItems;
	private List<SelectItem> timeUnitItems;
	private List<SelectItem> repeatByItems;
	private List<SelectItem> repeatEndItems;
	private Event event;

	private boolean selectGuest = false;

	public String getEventGuestValue() {
		return eventGuestValue;
	}

	public void setEventGuestValue(String eventGuestValue) {
		this.eventGuestValue = eventGuestValue;
	}

	public String getVisibilityItem(Integer value) {
		String name = null;
		List<SelectItem> items = getVisibilityItems();
		if (value != null) {
			for (SelectItem e : items) {
				if (value.equals(e.getValue())) {
					name = e.getLabel();
					break;
				}
			}
		}
		return name;
	}

	public List<SelectItem> getVisibilityItems() {
		if (visibilityItems == null) {
			visibilityItems = new ArrayList<SelectItem>();
			visibilityItems.add(new SelectItem(Event.VISIBILITY_INTERNAL, Lib.getResource(getLocale(), "event.visibility.1")));
			visibilityItems.add(new SelectItem(Event.VISIBILITY_MANAGER, Lib.getResource(getLocale(), "event.visibility.2")));
			visibilityItems.add(new SelectItem(Event.VISIBILITY_GROUP, Lib.getResource(getLocale(), "event.visibility.3")));
			visibilityItems.add(new SelectItem(Event.VISIBILITY_DEPARTMENT, Lib.getResource(getLocale(), "event.visibility.4")));
			visibilityItems.add(new SelectItem(Event.VISIBILITY_BRANCH, Lib.getResource(getLocale(), "event.visibility.5")));
			visibilityItems.add(new SelectItem(Event.VISIBILITY_COMPANY, Lib.getResource(getLocale(), "event.visibility.6")));
		}
		return visibilityItems;
	}

	public void setVisibilityItems(List<SelectItem> visibilityItems) {
		this.visibilityItems = visibilityItems;
	}

	public String getStatusName(Integer value) {
		return getLabel(getStatusItems(), value);
	}

	public List<SelectItem> getStatusItems() {
		if (statusItems == null) {
			statusItems = new ArrayList<SelectItem>();
			statusItems.add(new SelectItem(Event.STATUS_NEW, Lib.getResource(getLocale(), "event.status.1")));
			statusItems.add(new SelectItem(Event.STATUS_PROCESSING, Lib.getResource(getLocale(), "event.status.6")));
			statusItems.add(new SelectItem(Event.STATUS_DONE, Lib.getResource(getLocale(), "event.status.8")));
			statusItems.add(new SelectItem(Event.STATUS_CANCELED, Lib.getResource(getLocale(), "event.status.-1")));

		}
		return statusItems;
	}

	public void setStatusItems(List<SelectItem> statusItems) {
		this.statusItems = statusItems;
	}

	public String getPriorityName(Integer value) {
		return getLabel(getPriorityItems(), value);
	}

	public List<SelectItem> getPriorityItems() {
		if (statusItems == null) {
			priorityItems = new ArrayList<SelectItem>();
			priorityItems.add(new SelectItem(Event.PRIORITY_URGENT, Lib.getResource(getLocale(), "event.priority.0")));
			priorityItems.add(new SelectItem(Event.PRIORITY_HIGH, Lib.getResource(getLocale(), "event.priority.1")));
			priorityItems.add(new SelectItem(Event.PRIORITY_NORMAL, Lib.getResource(getLocale(), "event.priority.2")));
			priorityItems.add(new SelectItem(Event.PRIORITY_LOW, Lib.getResource(getLocale(), "event.priority.3")));
		}
		return priorityItems;
	}

	public void setPriorityItems(List<SelectItem> priorityItems) {
		this.priorityItems = priorityItems;
	}

	public String getEventTypeName(Integer value) {
		return getLabel(getEventTypeItems(), value);
	}

	public List<SelectItem> getEventTypeItems() {
		if (eventTypeItems == null) {
			eventTypeItems = new ArrayList<SelectItem>();
			eventTypeItems.add(new SelectItem(Event.TYPE_EVENT, Lib.getResource(getLocale(), "event.type.1")));
			eventTypeItems.add(new SelectItem(Event.TYPE_MEETING, Lib.getResource(getLocale(), "event.type.2")));
			eventTypeItems.add(new SelectItem(Event.TYPE_TASK, Lib.getResource(getLocale(), "event.type.3")));
			eventTypeItems.add(new SelectItem(Event.TYPE_NOTES, Lib.getResource(getLocale(), "event.type.4")));
		}
		return eventTypeItems;
	}

	public void setEventTypeItems(List<SelectItem> eventTypeItems) {
		this.eventTypeItems = eventTypeItems;
	}

	public String getRepeatTypeName(Integer value) {
		return getLabel(getRepeatTypeItems(), value);
	}

	public List<SelectItem> getRepeatTypeItems() {
		if (repeatTypeItems == null) {
			repeatTypeItems = new ArrayList<SelectItem>();
			repeatTypeItems.add(new SelectItem(Event.REPEAT_TYPE_DAILY, Lib.getResource(getLocale(), "event.repeattype.d")));
			repeatTypeItems.add(new SelectItem(Event.REPEAT_TYPE_WEEKLY, Lib.getResource(getLocale(), "event.repeattype.w")));
			repeatTypeItems.add(new SelectItem(Event.REPEAT_TYPE_MONTHLY, Lib.getResource(getLocale(), "event.repeattype.m")));
			repeatTypeItems.add(new SelectItem(Event.REPEAT_TYPE_YEARLY, Lib.getResource(getLocale(), "event.repeattype.y")));
		}
		return repeatTypeItems;
	}

	public void setRepeatTypeItems(List<SelectItem> repeatTypeItems) {
		this.repeatTypeItems = repeatTypeItems;
	}

	public String getTimeUnitName(String value) {
		return getLabel(getTimeUnitItems(), value);
	}

	public List<SelectItem> getTimeUnitItems() {
		if (timeUnitItems == null) {
			timeUnitItems = new ArrayList<SelectItem>();
			timeUnitItems.add(new SelectItem(Constant.UNIT_TIME_MINUTE, Lib.getResource(getLocale(), "unit.time.i")));
			timeUnitItems.add(new SelectItem(Constant.UNIT_TIME_HOUR, Lib.getResource(getLocale(), "unit.time.h")));
			timeUnitItems.add(new SelectItem(Constant.UNIT_TIME_DAY, Lib.getResource(getLocale(), "unit.time.d")));
			timeUnitItems.add(new SelectItem(Constant.UNIT_TIME_WEEK, Lib.getResource(getLocale(), "unit.time.w")));
			timeUnitItems.add(new SelectItem(Constant.UNIT_TIME_MONTH, Lib.getResource(getLocale(), "unit.time.m")));
			timeUnitItems.add(new SelectItem(Constant.UNIT_TIME_YEAR, Lib.getResource(getLocale(), "unit.time.y")));
		}
		return timeUnitItems;
	}

	public void setTimeUnitItems(List<SelectItem> timeUnitItems) {
		this.timeUnitItems = timeUnitItems;
	}

	public List<SelectItem> getRepeatByItems() {
		if (repeatByItems == null) {
			repeatByItems = new ArrayList<SelectItem>();
			repeatByItems.add(new SelectItem(Event.REPEAT_BY_DAYOFMONTH, Lib.getResource(getLocale(), "event.repeatby.dom")));
			repeatByItems.add(new SelectItem(Event.REPEAT_BY_DAYOFWEEK, Lib.getResource(getLocale(), "event.repeatby.dow")));
		}
		return repeatByItems;
	}

	public void setRepeatByItems(List<SelectItem> repeatByItems) {
		this.repeatByItems = repeatByItems;
	}

	public List<SelectItem> getRepeatEndItems() {
		if (repeatEndItems == null) {
			repeatEndItems = new ArrayList<SelectItem>();
			repeatEndItems.add(new SelectItem(Event.REPEAT_END_ON, Lib.getResource(getLocale(), "event.repeatend.1")));
			repeatEndItems.add(new SelectItem(Event.REPEAT_END_AFTER, Lib.getResource(getLocale(), "event.repeatend.2")));
		}
		return repeatEndItems;
	}

	public void setRepeatEndItems(List<SelectItem> repeatEndItems) {
		this.repeatEndItems = repeatEndItems;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public boolean isSelectGuest() {
		return selectGuest;
	}

	public void setSelectGuest(boolean selectGuest) {
		this.selectGuest = selectGuest;
	}

}
