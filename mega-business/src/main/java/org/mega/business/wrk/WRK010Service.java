package org.mega.business.wrk;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.mega.client.LocalService;
import org.mega.client.ParamQuery;
import org.mega.client.RemoteService;
import org.mega.common.Lib;
import org.mega.constant.Constant;
import org.mega.dao.entity.Employee;
import org.mega.dao.entity.Event;
import org.mega.dto.wrk.WRK010Dto;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless(mappedName = "MEGA", name = "wrk010Service")
public class WRK010Service extends WRK01Service implements RemoteService, LocalService {

	public WRK010Service() {
		super();
	}

	/**
	 * Initial Page
	 * 
	 * @param dto
	 */
	public void initPage(WRK010Dto dto) {
		Date currDate = getTimestamp();
		Date fromDate = Lib.addMonth(currDate, -1, zone, locale);
		Date toDate = Lib.addMonth(currDate, 1, zone, locale);
		dto.setFromDate(fromDate);
		dto.setToDate(toDate);
		
		// Load quick data
		searchQuick(dto);
	}

	/**
	 * Search Quick
	 * 
	 * @param dto
	 */
	public void searchQuick(WRK010Dto dto) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Event.searchQuick");

		String query = dto.getQuickSearch();
		query = query == null ? "" : query.toLowerCase();
		query = ParamQuery.wildCardLike(query);
		
		Date currDate = getTimestamp();
		Date fromDate = Lib.getStartDate(Lib.addMonth(currDate, -1, zone, locale), zone, locale);
		Date toDate = Lib.getEndDate(Lib.addMonth(currDate, 1, zone, locale), zone, locale);

		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		paramQuery.addParam("content", query);
		paramQuery.addParam("picName", query);
		paramQuery.addParam("creatorName", query);
		
		paramQuery.addParam("fromDate", fromDate);
		paramQuery.addParam("toDate", toDate);

		paramQuery.addParam("eventTypeEvent", Event.TYPE_EVENT);
		paramQuery.addParam("eventTypeMeeting", Event.TYPE_MEETING);
		paramQuery.addParam("levelData", loginInfo.getLevelData(dto.getBusinessCode()));
		//paramQuery.addParam("levelSelf", Constant.LEVEL_DATA_SELF);
		paramQuery.addParam("levelGroup", Constant.LEVEL_DATA_GROUP);
		paramQuery.addParam("levelDepartment", Constant.LEVEL_DATA_DEPARTMENT);
		paramQuery.addParam("levelBranch", Constant.LEVEL_DATA_BRANCH);
		paramQuery.addParam("levelCompany", Constant.LEVEL_DATA_COMPANY);

		//paramQuery.addParam("eventTypeTask", Event.TYPE_TASK);
		//paramQuery.addParam("eventTypeNotes", Event.TYPE_NOTES);
		paramQuery.addParam("visibilityInternal", Event.VISIBILITY_INTERNAL);
		paramQuery.addParam("visibilityManager", Event.VISIBILITY_MANAGER);
		paramQuery.addParam("visibilityGroup", Event.VISIBILITY_GROUP);
		paramQuery.addParam("visibilityDepartment", Event.VISIBILITY_DEPARTMENT);
		paramQuery.addParam("visibilityBranch", Event.VISIBILITY_BRANCH);
		paramQuery.addParam("visibilityCompany", Event.VISIBILITY_COMPANY);
		paramQuery.addParam("employeeId", loginInfo.getEmployeeId());
		paramQuery.addParam("groupId", loginInfo.getGroupId());
		paramQuery.addParam("departmentId", loginInfo.getDepartmentId());
		paramQuery.addParam("branchId", loginInfo.getBranchId());

		List<Event> tableData = getEntities(paramQuery);
		dto.setTableData(tableData);
		dto.setDataTable(dto.getTableData().firstPageData());
	}

	/**
	 * Search Advance
	 * 
	 * @param dto
	 */
	public void searchAdvance(WRK010Dto dto) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Event.searchAdvance");

		Long creatorId = dto.getCreatorId();
		String creatorName = dto.getCreatorName();
		Long picId = dto.getPicId();
		String picName = dto.getPicName();
		Integer eventType = dto.getEventType();
		String content = dto.getContent();
		Integer status = dto.getStatus();

		Date currDate = getTimestamp();
		Date fromDate = Lib.getStartDate(dto.getFromDate(), zone, locale);
		Date toDate = Lib.getEndDate(dto.getToDate(), zone, locale);

		if (fromDate == null) {
			fromDate = Lib.getStartMonth(currDate, zone, locale);
		}
		if (toDate == null) {
			toDate = Lib.getEndMonth(currDate, zone, locale);
		}
		content = ParamQuery.wildCardLike(content);

		if (Lib.isEmpty(creatorName)) {
			creatorId = 0L;
		} else {
			Employee creator = findEntity(Employee.class, creatorId);
			if (creator == null) {
				creatorId = 0L;
				creatorName = "";
			} else {
				creatorName = creator.getName();
			}
		}

		if (Lib.isEmpty(picName)) {
			picId = 0L;
		} else {
			Employee pic = findEntity(Employee.class, picId);
			if (pic == null) {
				picId = 0L;
				picName = "";
			} else {
				picName = pic.getName();
			}
		}

		dto.setCreatorId(creatorId);
		dto.setCreatorName(creatorName);
		dto.setPicId(picId);
		dto.setPicName(picName);
		dto.setFromDate(fromDate);
		dto.setToDate(toDate);

		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		paramQuery.addParam("creatorId", creatorId);
		paramQuery.addParam("picId", picId);
		paramQuery.addParam("eventType", eventType);
		paramQuery.addParam("content", content);
		paramQuery.addParam("status", status);
		paramQuery.addParam("fromDate", fromDate);
		paramQuery.addParam("toDate", toDate);
		
		paramQuery.addParam("eventTypeEvent", Event.TYPE_EVENT);
		paramQuery.addParam("eventTypeMeeting", Event.TYPE_MEETING);
		paramQuery.addParam("levelData", loginInfo.getLevelData(dto.getBusinessCode()));
		//paramQuery.addParam("levelSelf", Constant.LEVEL_DATA_SELF);
		paramQuery.addParam("levelGroup", Constant.LEVEL_DATA_GROUP);
		paramQuery.addParam("levelDepartment", Constant.LEVEL_DATA_DEPARTMENT);
		paramQuery.addParam("levelBranch", Constant.LEVEL_DATA_BRANCH);
		paramQuery.addParam("levelCompany", Constant.LEVEL_DATA_COMPANY);

		//paramQuery.addParam("eventTypeTask", Event.TYPE_TASK);
		//paramQuery.addParam("eventTypeNotes", Event.TYPE_NOTES);
		paramQuery.addParam("visibilityInternal", Event.VISIBILITY_INTERNAL);
		paramQuery.addParam("visibilityManager", Event.VISIBILITY_MANAGER);
		paramQuery.addParam("visibilityGroup", Event.VISIBILITY_GROUP);
		paramQuery.addParam("visibilityDepartment", Event.VISIBILITY_DEPARTMENT);
		paramQuery.addParam("visibilityBranch", Event.VISIBILITY_BRANCH);
		paramQuery.addParam("visibilityCompany", Event.VISIBILITY_COMPANY);
		paramQuery.addParam("employeeId", loginInfo.getEmployeeId());
		paramQuery.addParam("groupId", loginInfo.getGroupId());
		paramQuery.addParam("departmentId", loginInfo.getDepartmentId());
		paramQuery.addParam("branchId", loginInfo.getBranchId());

		List<Event> tableData = getEntities(paramQuery);
		dto.setTableData(tableData);
		dto.setDataTable(dto.getTableData().firstPageData());
	}
}
