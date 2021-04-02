/*
 * %W% %E%
 *
 * Copyright (c) 2014, AISOFT and/or its affiliates. All rights reserved.
 * AISOFT Business Management System PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.mega.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.mega.dao.core.DaoEntity;

@Entity
@NamedQueries({
		@NamedQuery(name = "Event.searchQuick", query = "select distinct o from Event o inner join Employee e on (o.picId = e.id) inner join Employee c on (o.creatorId = c.id) left join EventGuest g on (o.id = g.eventId) where o.deleted = 0 and o.companyId = :companyId and (lower(o.content) like :content or lower(e.name) like :picName or lower(c.name) like :creatorName) and o.startDate <= :toDate and ((o.repeat = 0 and o.endDate >= :fromDate) or (o.repeat = 1 and o.endRepeat >= :fromDate)) and (o.picId = :employeeId or o.creatorId = :employeeId or g.guestId = :employeeId or (o.picId != :employeeId and ((o.visibility = :visibilityInternal and (o.creatorId = :employeeId or g.guestId = :employeeId)) or (o.visibility = :visibilityManager and (e.managerId = :employeeId or e.supervisorId = :employeeId)) or (o.visibility = :visibilityGroup and e.groupId = :groupId and e.groupId > 0) or (o.visibility = :visibilityDepartment and e.departmentId = :departmentId) or (o.visibility = :visibilityBranch and o.branchId = :branchId) or o.visibility = :visibilityCompany or ((o.eventType = :eventTypeEvent or o.eventType = :eventTypeMeeting) and ((:levelData = :levelGroup and e.groupId = :groupId and e.groupId > 0) or (:levelData = :levelDepartment and e.departmentId = :departmentId) or (:levelData = :levelBranch and o.branchId = :branchId) or :levelData = :levelCompany))))) order by o.createdDate desc"),
		@NamedQuery(name = "Event.searchAdvance", query = "select distinct o from Event o inner join Employee e on (o.picId = e.id) left join EventGuest g on (o.id = g.eventId) where o.deleted = 0 and o.companyId = :companyId and (:creatorId = 0 or o.creatorId = :creatorId) and (:picId = 0 or o.picId = :picId) and (:eventType = 0 or o.eventType = :eventType) and (:status = 0 or o.status = :status) and o.content like :content and o.startDate <= :toDate and ((o.repeat = 0 and o.endDate >= :fromDate) or (o.repeat = 1 and o.endRepeat >= :fromDate)) and (o.picId = :employeeId or o.creatorId = :employeeId or g.guestId = :employeeId or (o.picId != :employeeId and ((o.visibility = :visibilityInternal and (o.creatorId = :employeeId or g.guestId = :employeeId)) or (o.visibility = :visibilityManager and (e.managerId = :employeeId or e.supervisorId = :employeeId)) or (o.visibility = :visibilityGroup and e.groupId = :groupId and e.groupId > 0) or (o.visibility = :visibilityDepartment and e.departmentId = :departmentId) or (o.visibility = :visibilityBranch and o.branchId = :branchId) or o.visibility = :visibilityCompany or ((o.eventType = :eventTypeEvent or o.eventType = :eventTypeMeeting) and ((:levelData = :levelGroup and e.groupId = :groupId and e.groupId > 0) or (:levelData = :levelDepartment and e.departmentId = :departmentId) or (:levelData = :levelBranch and o.branchId = :branchId) or :levelData = :levelCompany))))) order by o.createdDate desc"),
		@NamedQuery(name = "Event.findCalendar", query = "select distinct o from Event o inner join Employee e on (o.picId = e.id) left join EventGuest g on (o.id = g.eventId) left join g.guest gg on (g.guestId = gg.id) where o.deleted = 0 and o.companyId = :companyId and (o.status between :fromStatus and :toStatus) and (:searchBranch = 0 or e.branchId = :searchBranch or gg.branchId = :searchBranch) and (:searchDepartment = 0 or e.departmentId = :searchDepartment or gg.departmentId =:searchDepartment) and (:searchGroup = 0 or e.groupId = :searchGroup or gg.groupId =:searchGroup) and (:searchEmployee = 0 or o.picId = :searchEmployee or o.creatorId = :searchEmployee or g.guestId = :searchEmployee or (g.guestId is null and :searchEmployee = 0 and o.visibility != :visibilityInternal)) and o.startDate <= :toDate and ((o.repeat = 0 and o.endDate >= :fromDate) or (o.repeat = 1 and o.endRepeat >= :fromDate)) and (o.picId = :employeeId or o.creatorId = :employeeId or g.guestId = :employeeId or (o.picId != :employeeId and ((o.visibility = :visibilityInternal and (o.creatorId = :employeeId or g.guestId = :employeeId)) or (o.visibility = :visibilityManager and (e.managerId = :employeeId or e.supervisorId = :employeeId or gg.managerId = :employeeId or gg.supervisorId = :employeeId)) or (o.visibility = :visibilityGroup and ((e.groupId = :groupId and e.groupId > 0) or (gg.groupId = :groupId and gg.groupId > 0))) or (o.visibility = :visibilityDepartment and (e.departmentId = :departmentId or gg.departmentId = :departmentId)) or (o.visibility = :visibilityBranch and (e.branchId = :branchId or gg.branchId = :branchId)) or o.visibility = :visibilityCompany or ((o.eventType = :eventTypeEvent or o.eventType = :eventTypeMeeting) and ((:levelData = :levelGroup and ((e.groupId = :groupId and e.groupId > 0) or (e.groupId = :groupId and gg.groupId > 0))) or (:levelData = :levelDepartment and (e.departmentId = :departmentId or gg.departmentId = :departmentId)) or (:levelData = :levelBranch and (e.branchId = :branchId or gg.branchId = :branchId)) or :levelData = :levelCompany))))) order by o.startDate"),
		@NamedQuery(name = "Event.findEventMeeting", query = "select distinct o from Event o inner join Employee e on (o.picId = e.id) left join EventGuest g on (o.id = g.eventId) where o.deleted = 0 and o.companyId = :companyId and (o.status between :fromStatus and :toStatus) and (o.eventType = :eventTypeEvent or o.eventType = :eventTypeMeeting) and o.startDate <= :toDate and ((o.repeat = 0 and o.endDate >= :fromDate) or (o.repeat = 1 and o.endRepeat >= :fromDate)) and (o.picId = :employeeId or g.guestId = :employeeId or (o.picId != :employeeId and ((o.visibility = :visibilityInternal and (g.guestId = :employeeId or o.creatorId = :employeeId)) or (o.visibility = :visibilityManager and (e.managerId = :employeeId or e.supervisorId = :employeeId)) or (o.visibility = :visibilityGroup and e.groupId = :groupId and e.groupId > 0 and g.guestId is null) or (o.visibility = :visibilityDepartment and e.departmentId = :departmentId and g.guestId is null) or (o.visibility = :visibilityBranch and o.branchId = :branchId and g.guestId is null) or (o.visibility = :visibilityCompany and g.guestId is null)))) order by o.startDate"),
		@NamedQuery(name = "Event.findTaskNotes", query = "select distinct o from Event o inner join Employee e on (o.picId = e.id) left join EventGuest g on (o.id = g.eventId) where o.deleted = 0 and o.companyId = :companyId and (o.status between :fromStatus and :toStatus) and (o.eventType = :eventTypeTask or o.eventType = :eventTypeNotes) and o.startDate <= :toDate and ((o.repeat = 0 and o.endDate >= :fromDate) or (o.repeat = 1 and o.endRepeat >= :fromDate)) and (o.picId = :employeeId or g.guestId = :employeeId or (o.picId != :employeeId and ((o.visibility = :visibilityInternal and (g.guestId = :employeeId or o.creatorId = :employeeId)) or (o.visibility = :visibilityManager and (e.managerId = :employeeId or e.supervisorId = :employeeId)) or (o.visibility = :visibilityGroup and e.groupId = :groupId and e.groupId > 0 and g.guestId is null) or (o.visibility = :visibilityDepartment and e.departmentId = :departmentId and g.guestId is null) or (o.visibility = :visibilityBranch and o.branchId = :branchId and g.guestId is null) or (o.visibility = :visibilityCompany and g.guestId is null)))) order by o.startDate") })
@Table(name = "event")
public class Event extends DaoEntity implements Serializable {
	private static final long serialVersionUID = 8292307652806288621L;

	public static final Integer PRIORITY_URGENT = 0;
	public static final Integer PRIORITY_HIGH = 1;
	public static final Integer PRIORITY_NORMAL = 2;
	public static final Integer PRIORITY_LOW = 3;

	public static final String REMIND_POPUP = "p";
	public static final String REMIND_EMAIL = "e";

	public static final String REPEAT_TYPE_DAILY = "d";
	public static final String REPEAT_TYPE_WEEKLY = "w";
	public static final String REPEAT_TYPE_MONTHLY = "m";
	public static final String REPEAT_TYPE_YEARLY = "y";

	public static final String REPEAT_BY_DAYOFMONTH = "dom";
	public static final String REPEAT_BY_DAYOFWEEK = "dow";

	public static final Integer REPEAT_END_ON = 1;
	public static final Integer REPEAT_END_AFTER = 2;

	public static final Integer TYPE_EVENT = 1;
	public static final Integer TYPE_MEETING = 2;
	public static final Integer TYPE_TASK = 3;
	public static final Integer TYPE_NOTES = 4;

	public static final Integer STATUS_NEW = 1;
	public static final Integer STATUS_PROCESSING = 6;
	public static final Integer STATUS_DONE = 8;
	public static final Integer STATUS_CANCELED = -1;

	public static final Integer VISIBILITY_INTERNAL = 1;
	public static final Integer VISIBILITY_MANAGER = 2;
	public static final Integer VISIBILITY_GROUP = 3;
	public static final Integer VISIBILITY_DEPARTMENT = 4;
	public static final Integer VISIBILITY_BRANCH = 5;
	public static final Integer VISIBILITY_COMPANY = 6;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "event_type")
	private Integer eventType = TYPE_TASK;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	@Temporal(TemporalType.TIME)
	@Column(name = "start_time")
	private Date startTime;

	@Temporal(TemporalType.TIME)
	@Column(name = "end_time")
	private Date endTime;

	@Column(name = "pic_id")
	private Long picId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pic_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Employee pic;

	@Column(name = "creator_id")
	private Long creatorId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Employee creator;

	@Column(name = "content")
	private String content;

	@Column(name = "content_detail")
	private String contentDetail;

	@Column(name = "notes")
	private String notes;

	@Column(name = "event_where")
	private String eventWhere;

	@Column(name = "event_color")
	private Integer eventColor = 1;

	@Column(name = "remind_type")
	private String remindType = REMIND_POPUP;

	@Column(name = "remind_time")
	private Integer remindTime;

	@Column(name = "remind_time_unit")
	private String remindTimeUnit = "i";

	@Column(name = "repeat")
	private Integer repeat = 0;

	@Column(name = "repeat_type")
	private String repeatType;

	@Column(name = "repeat_every")
	private Integer repeatEvery = 0;

	@Column(name = "repeat_mon")
	private Integer repeatMon = 0;

	@Column(name = "repeat_tue")
	private Integer repeatTue = 0;

	@Column(name = "repeat_wed")
	private Integer repeatWed = 0;

	@Column(name = "repeat_thu")
	private Integer repeatThu = 0;

	@Column(name = "repeat_fri")
	private Integer repeatFri = 0;

	@Column(name = "repeat_sat")
	private Integer repeatSat = 0;

	@Column(name = "repeat_sun")
	private Integer repeatSun = 0;

	@Column(name = "repeat_by")
	private String repeatBy;

	@Column(name = "end_repeat_type")
	private Integer endRepeatType;

	@Column(name = "occurrences")
	private Integer occurrences;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_repeat")
	private Date endRepeat;

	@Column(name = "visibility")
	private Integer visibility = VISIBILITY_BRANCH;

	@Column(name = "show_me_as")
	private Integer showMeAs;

	@Column(name = "priority")
	private Integer priority = PRIORITY_NORMAL;

	@Column(name = "status")
	private Integer status = STATUS_NEW;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "branch_id")
	private Long branchId;

	@Column(name = "deleted")
	private Integer deleted = 0;

	@Column(name = "deleted_id")
	private Long deletedId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_date")
	private Date deletedDate;

	@Column(name = "created_id")
	private Long createdId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_id")
	private Long updatedId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;

	@Version
	@Column(name = "version")
	private Integer version = 0;

	public Event() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getPicId() {
		return this.picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public Employee getPic() {
		return pic;
	}

	public void setPic(Employee pic) {
		this.pic = pic;
	}

	public String getPicCode() {
		return this.pic != null ? this.pic.getCode() : null;
	}

	public void setPicCode(String pic) {

	}

	public String getPicName() {
		return this.pic != null ? this.pic.getName() : null;
	}

	public void setPicName(String pic) {

	}

	public Long getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Employee getCreator() {
		return creator;
	}

	public void setCreator(Employee creator) {
		this.creator = creator;
	}

	public String getCreatorName() {
		return this.creator != null ? this.creator.getName() : null;
	}

	public void setCreatorName(String creatorName) {

	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentDetail() {
		return this.contentDetail;
	}

	public void setContentDetail(String contentDetail) {
		this.contentDetail = contentDetail;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getEventWhere() {
		return eventWhere;
	}

	public void setEventWhere(String eventWhere) {
		this.eventWhere = eventWhere;
	}

	public Integer getEventColor() {
		return eventColor;
	}

	public void setEventColor(Integer eventColor) {
		this.eventColor = eventColor;
	}

	public String getRemindType() {
		return remindType;
	}

	public void setRemindType(String remindType) {
		this.remindType = remindType;
	}

	public Integer getRemindTime() {
		return this.remindTime;
	}

	public void setRemindTime(Integer remindTime) {
		this.remindTime = remindTime;
	}

	public String getRemindTimeUnit() {
		return remindTimeUnit;
	}

	public void setRemindTimeUnit(String remindTimeUnit) {
		this.remindTimeUnit = remindTimeUnit;
	}

	public boolean isRepeat() {
		return this.repeat > 0;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat ? 1 : 0;
	}

	public String getRepeatType() {
		return this.repeatType;
	}

	public void setRepeatType(String repeatType) {
		this.repeatType = repeatType;
	}

	public Integer getRepeatEvery() {
		return this.repeatEvery;
	}

	public void setRepeatEvery(Integer repeatEvery) {
		this.repeatEvery = repeatEvery;
	}

	public boolean isRepeatMon() {
		return this.repeatMon > 0;
	}

	public void setRepeatMon(boolean repeatMon) {
		this.repeatMon = repeatMon ? 1 : 0;
	}

	public boolean isRepeatTue() {
		return this.repeatTue > 0;
	}

	public void setRepeatTue(boolean repeatTue) {
		this.repeatTue = repeatTue ? 1 : 0;
	}

	public boolean isRepeatWed() {
		return this.repeatWed > 0;
	}

	public void setRepeatWed(boolean repeatWed) {
		this.repeatWed = repeatWed ? 1 : 0;
	}

	public boolean isRepeatThu() {
		return this.repeatThu > 0;
	}

	public void setRepeatThu(boolean repeatThu) {
		this.repeatThu = repeatThu ? 1 : 0;
	}

	public boolean isRepeatFri() {
		return this.repeatFri > 0;
	}

	public void setRepeatFri(boolean repeatFri) {
		this.repeatFri = repeatFri ? 1 : 0;
	}

	public boolean isRepeatSat() {
		return this.repeatSat > 0;
	}

	public void setRepeatSat(boolean repeatSat) {
		this.repeatSat = repeatSat ? 1 : 0;
	}

	public boolean isRepeatSun() {
		return this.repeatSun > 0;
	}

	public void setRepeatSun(boolean repeatSun) {
		this.repeatSun = repeatSun ? 1 : 0;
	}

	public String getRepeatBy() {
		return repeatBy;
	}

	public void setRepeatBy(String repeatBy) {
		this.repeatBy = repeatBy;
	}

	public Integer getEndRepeatType() {
		return this.endRepeatType;
	}

	public void setEndRepeatType(Integer endRepeatType) {
		this.endRepeatType = endRepeatType;
	}

	public Integer getOccurrences() {
		return this.occurrences;
	}

	public void setOccurrences(Integer occurrences) {
		this.occurrences = occurrences;
	}

	public Date getEndRepeat() {
		return this.endRepeat;
	}

	public void setEndRepeat(Date endRepeat) {
		this.endRepeat = endRepeat;
	}

	public Integer getVisibility() {
		return visibility;
	}

	public void setVisibility(Integer visibility) {
		this.visibility = visibility;
	}

	public Integer getShowMeAs() {
		return showMeAs;
	}

	public void setShowMeAs(Integer showMeAs) {
		this.showMeAs = showMeAs;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getBranchId() {
		return this.branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public boolean isDeleted() {
		return this.deleted > 0;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted ? 1 : 0;
	}

	public Long getDeletedId() {
		return this.deletedId;
	}

	public void setDeletedId(Long deletedId) {
		this.deletedId = deletedId;
	}

	public Date getDeletedDate() {
		return this.deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public Long getCreatedId() {
		return this.createdId;
	}

	public void setCreatedId(Long createdId) {
		this.createdId = createdId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedId() {
		return this.updatedId;
	}

	public void setUpdatedId(Long updatedId) {
		this.updatedId = updatedId;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Transient
	private boolean selected = false;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Transient
	private int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
