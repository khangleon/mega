package org.mega.dto.wrk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mega.common.Lib;
import org.mega.dao.entity.Event;
import org.mega.dto.core.SelectItem;
import org.mega.dto.core.TableDto;

public class WRK010Dto extends TableDto implements Serializable {

	private static final long serialVersionUID = -4206357286586330075L;

	private Long creatorId = 0L;
	private String creatorName;
	private Long picId = 0L;
	private String picName;
	private String content;
	private Date fromDate;
	private Date toDate;
	private Integer status = 0;
	private Integer eventType = 0;
	private List<SelectItem> statusItems;
	private List<SelectItem> eventTypeItems;

	private List<Event> dataTable;

	public WRK010Dto() {
		super();
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public List<Event> getDataTable() {
		return dataTable;
	}

	@SuppressWarnings("unchecked")
	public void setDataTable(List<?> dataTable) {
		this.dataTable = (List<Event>) dataTable;
	}

	public String getStatusName(Integer value) {
		return getLabel(getStatusItems(), value);
	}

	public List<SelectItem> getStatusItems() {
		if (statusItems == null) {
			statusItems = new ArrayList<SelectItem>();
			statusItems.add(new SelectItem(0, Lib.getResource(getLocale(), "common.label.all")));
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

	public String getEventTypeName(Integer value) {
		return getLabel(getEventTypeItems(), value);
	}

	public List<SelectItem> getEventTypeItems() {

		if (eventTypeItems == null) {
			eventTypeItems = new ArrayList<SelectItem>();
			eventTypeItems.add(new SelectItem(0, Lib.getResource(getLocale(), "common.label.all")));
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

}
