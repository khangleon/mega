package org.mega.business.wrk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.mega.client.LocalService;
import org.mega.client.RemoteService;
import org.mega.common.Lib;
import org.mega.dao.entity.Employee;
import org.mega.dao.entity.Event;
import org.mega.dao.entity.EventGuest;
import org.mega.dto.wrk.WRK012Dto;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless(mappedName = "MEGA", name = "wrk012Service")
public class WRK012Service extends WRK01Service implements RemoteService, LocalService {

	public WRK012Service() {
		super();
	}

	/**
	 * Initial page
	 * 
	 * @param dto
	 */
	public void initPage(WRK012Dto dto) {
		Event event = findEntity(Event.class, dto.getId());
		if (event != null) {
			dto.setEventGuestValue(getGuestValue(dto.getId()));
		} else {
			event = new Event();
			event.setBranchId(loginInfo.getBranchId());
			event.setCompanyId(loginInfo.getCompanyId());
			event.setCreatorId(loginInfo.getEmployeeId());
			event.setCreator(loginInfo.getEmployee());
			event.setPicId(loginInfo.getEmployeeId());
			event.setPic(loginInfo.getEmployee());
		}
		dto.setEvent(event);

		roleControl(dto);
	}

	/**
	 * Save data
	 * 
	 * @param dto
	 */
	public void save(WRK012Dto dto) {
		if (!saveValidate(dto)) {
			return;
		}
		Event event = dto.getEvent();

		Date startDate = event.getStartDate();
		Date endDate = event.getEndDate();
		Date startTime = event.getStartTime();
		Date endTime = event.getEndTime();

		if (startDate != null && endDate != null) {
			if (endDate.before(startDate)) {
				event.setStartDate(endDate);
				event.setEndDate(startDate);
				if (startTime != null && endTime != null) {
					event.setStartTime(endTime);
					event.setEndTime(startTime);
				} else if (startTime != null) {
					event.setStartTime(startTime);
					event.setEndTime(startTime);
				} else if (endTime != null) {
					event.setStartTime(endTime);
					event.setEndTime(endTime);
				} else {
					event.setStartTime(getBeginMorning());
					event.setEndTime(getBeginMorning());
				}
			} else if (endDate.equals(startDate)) {
				if (startTime != null && endTime != null) {
					if (endTime.before(startTime)) {
						event.setStartTime(endTime);
						event.setEndTime(startTime);
					}
				} else if (startTime != null) {
					event.setStartTime(startTime);
					event.setEndTime(startTime);
				} else if (endTime != null) {
					event.setStartTime(endTime);
					event.setEndTime(endTime);
				} else {
					event.setStartTime(getBeginMorning());
					event.setEndTime(getBeginMorning());
				}
			} else {
				if (startTime != null && endTime != null) {

				} else if (startTime != null) {
					event.setStartTime(startTime);
					event.setEndTime(startTime);
				} else if (endTime != null) {
					event.setStartTime(endTime);
					event.setEndTime(endTime);
				} else {
					event.setStartTime(getBeginMorning());
					event.setEndTime(getBeginMorning());
				}
			}

		} else if (startDate != null) {
			event.setStartDate(startDate);
			event.setEndDate(startDate);
			if (startTime != null) {
				event.setStartTime(startTime);
				event.setEndTime(startTime);
			} else {
				event.setStartTime(getBeginMorning());
				event.setEndTime(getBeginMorning());
			}
		} else if (endDate != null) {
			event.setStartDate(endDate);
			event.setEndDate(endDate);
			if (endTime != null) {
				event.setStartTime(endTime);
				event.setEndTime(endTime);
			} else {
				event.setStartTime(getBeginMorning());
				event.setEndTime(getBeginMorning());
			}
		} else {
			event.setStartTime(null);
			event.setEndTime(null);
		}

		// Calculate end repeat
		if (event.isRepeat()) {
			Integer repeatEvery = event.getRepeatEvery();
			Integer occurrences = event.getOccurrences();
			repeatEvery = repeatEvery == null || repeatEvery == 0 ? 1 : repeatEvery;
			occurrences = occurrences == null || occurrences == 0 ? 1 : occurrences;

			if (Event.REPEAT_END_AFTER.equals(event.getEndRepeatType())) {
				Calendar cal = Calendar.getInstance(zone, locale);
				if (event.getStartDate() != null) {
					cal.setTime(event.getStartDate());
				}

				if (Event.REPEAT_TYPE_WEEKLY.equals(event.getRepeatType())) {
					cal.add(Calendar.WEEK_OF_YEAR, repeatEvery * occurrences);
				} else if (Event.REPEAT_TYPE_DAILY.equals(event.getRepeatType())) {
					cal.add(Calendar.DATE, repeatEvery * occurrences);
				} else if (Event.REPEAT_TYPE_MONTHLY.equals(event.getRepeatType())) {
					cal.add(Calendar.MONTH, repeatEvery * occurrences);
				} else if (Event.REPEAT_TYPE_YEARLY.equals(event.getRepeatType())) {
					cal.add(Calendar.YEAR, repeatEvery * occurrences);
				}

				event.setEndRepeat(cal.getTime());
				event.setOccurrences(occurrences);
			} else {
				if (event.getEndRepeat() == null) {
					event.setEndRepeat(event.getEndDate());
				}
				event.setOccurrences(null);
			}

			event.setRepeatEvery(repeatEvery);
		}

		event = updateEntity(event);
		dto.setEvent(event);

		if (!Lib.isEmpty(dto.getEventGuestValue())) {
			List<Long> eventGuestId = eventGuestId(dto.getEventGuestValue());
			saveEventGuest(event.getId(), eventGuestId);
			dto.setEventGuestValue(getGuestValue(event.getId()));
		}
		if (!messageDto.isShowMessage()) {
			messageDto.showTop(Lib.getMessage(dto.getLocale(), IM0001));
		}
	}

	/**
	 * Save guest
	 * 
	 * @param eventId
	 * @param eventGuestId
	 */
	public void saveEventGuest(Long eventId, List<Long> eventGuestId) {
		List<EventGuest> items = getEventGuest(eventId);
		if (items != null) {
			removeEntities(items);
		}
		if (eventGuestId != null) {
			for (Long id : eventGuestId) {
				EventGuest eventGuest = new EventGuest();
				eventGuest.setEventId(eventId);
				eventGuest.setGuestId(id);
				updateEntity(eventGuest);
			}
		}
	}

	/**
	 * Guest data
	 * 
	 * @param data
	 * @return
	 */
	public List<Long> eventGuestId(String data) {
		HashSet<Long> eventGuestId = new HashSet<Long>();
		if (!Lib.isEmpty(data)) {
			String[] tmp = data.split(","); // [abc: ABC], [def: DEF]
			String[] tmpSub = null;
			Employee guest = null;
			String guestCode = null;
			for (int i = 0; i < tmp.length; i++) {
				tmpSub = tmp[i].split(":");
				guestCode = Lib.trim(tmpSub[0]);
				guest = getEmployee(guestCode);
				if (guest != null) {
					eventGuestId.add(guest.getId());
				}
			}
		} else {
			eventGuestId = null;
		}

		return new ArrayList<Long>(eventGuestId);
	}

	/**
	 * Change guest
	 * 
	 * @param dto
	 */
	public void changeEventGuest(WRK012Dto dto) {

	}

	/**
	 * Validate input
	 * 
	 * @param dto
	 * @return
	 */
	private boolean saveValidate(WRK012Dto dto) {
		boolean valid = true;
		Event event = dto.getEvent();
		String message = "";
		String label = "";

		if (Lib.isEmpty(event.getContent())) {
			message = Lib.getMessage(dto.getLocale(), EM0001);
			label = Lib.getResource(dto.getLocale(), "wrk01.label.content");
			messageDto.showError(message, label, "itContent");
			valid = false;
		}

		if (event.getStartDate() == null) {
			message = Lib.getMessage(dto.getLocale(), EM0001);
			label = Lib.getResource(dto.getLocale(), "wrk01.label.startdate");
			messageDto.showError(message, label, "itStartDate");
			valid = false;
		}

		if (event.getStartTime() == null) {
			message = Lib.getMessage(dto.getLocale(), EM0001);
			label = Lib.getResource(dto.getLocale(), "wrk01.label.startdate");
			messageDto.showError(message, label, "itStartTime");
			valid = false;
		}

		if (event.getEndDate() == null) {
			message = Lib.getMessage(dto.getLocale(), EM0001);
			label = Lib.getResource(dto.getLocale(), "wrk01.label.enddate");
			messageDto.showError(message, label, "itEndDate");
			valid = false;
		}

		if (event.getEndTime() == null) {
			message = Lib.getMessage(dto.getLocale(), EM0001);
			label = Lib.getResource(dto.getLocale(), "wrk01.label.enddate");
			messageDto.showError(message, label, "itEndTime");
			valid = false;
		}

		// CHECK STARTDATE < ENDDATE
		if (event.getStartDate() != null && event.getEndDate() != null && event.getEndDate().before(event.getStartDate())) {
			String start = Lib.getResource(dto.getLocale(), "wrk01.label.startdate");
			String end = Lib.getResource(dto.getLocale(), "wrk01.label.enddate");
			message = Lib.getMessage(dto.getLocale(), EM0006, end, start);
			label = Lib.getResource(dto.getLocale(), "wrk01.label.tododate");
			messageDto.showError(message, label, "itStartDate");
			valid = false;
		}

		if (event.getPicId() == null) {
			message = Lib.getMessage(dto.getLocale(), EM0001);
			label = Lib.getResource(dto.getLocale(), "wrk01.label.pic");
			messageDto.showError(message, label, "itPicName");
			valid = false;
		}

		if (event.isRepeat()) {
			if (Event.REPEAT_END_ON.equals(event.getEndRepeatType())) {
				if (event.getEndRepeat() == null) {
					message = Lib.getMessage(dto.getLocale(), EM0001);
					label = Lib.getResource(dto.getLocale(), "wrk01.label.ends");
					messageDto.showError(message, label, "itEndRepeat");
					valid = false;
				}
			} else {
				if (event.getOccurrences() == null || event.getOccurrences() <= 0) {
					message = Lib.getMessage(dto.getLocale(), EM0001);
					label = Lib.getResource(dto.getLocale(), "wrk01.label.ends");
					messageDto.showError(message, label, "itOccurrences");
					valid = false;
				}
			}
		}

		return valid;
	}

	/**
	 * AutoComplete change Pic
	 * 
	 * @param dto
	 */
	public void changePic(WRK012Dto dto) {
		Event event = dto.getEvent();
		Employee pic = findEntity(Employee.class, event.getPicId());
		if (pic != null) {
			event.setPicName(pic.getName());
			event.setPic(pic);
		} else {
			event.setPicId(null);
			event.setPicName(null);
			event.setPic(null);
		}
		dto.setEvent(event);
	}

	/**
	 * Processing event
	 * 
	 * @param dto
	 */
	public void processing(WRK012Dto dto) {
		super.processing(dto);
	}

	/**
	 * Sold data
	 * 
	 * @param dto
	 */
	public void done(WRK012Dto dto) {
		super.done(dto);
	}

	/**
	 * Delete data
	 * 
	 * @param dto
	 */
	public void delete(WRK012Dto dto) {
		super.delete(dto);
	}

	/**
	 * Print data
	 * 
	 * @param dto
	 */
	public void print(WRK012Dto dto) {
		super.print(dto);
	}

	public void selectGuest(WRK012Dto dto) {
		Event event = dto.getEvent();
		String eventGuestValue = dto.getEventGuestValue();
		Integer visibility = event.getVisibility();
		List<Employee> employeeList = getEmployeeList(loginInfo.getCompanyId());
		Employee creator = loginInfo.getEmployee();
		Long creatorId = loginInfo.getEmployeeId() != null ? loginInfo.getEmployeeId() : 0L;
		Long creatorBrachId = creator.getBranchId() != null ? creator.getBranchId() : 0L;
		Long creatorDepartmentId = creator.getDepartmentId() != null ? creator.getDepartmentId() : 0L;
		Long creatorGroupId = creator.getGroupId() != null ? creator.getGroupId() : 0L;
		Long creatorManagerId = creator.getManagerId() != null ? creator.getManagerId() : 0L;
		boolean selectGuest = dto.isSelectGuest();

		StringBuffer guest = new StringBuffer("");

		if (Event.VISIBILITY_COMPANY.equals(visibility)) {
			for (Employee e : employeeList) {
				if (e.getStatus() != -1) {
					guest.append(", ");
					guest.append(e.getCode());
					guest.append(": ");
					guest.append(e.getName());
				}
			}
		} else if (Event.VISIBILITY_BRANCH.equals(visibility)) {
			for (Employee e : employeeList) {
				if (e.getStatus() != -1 && creatorBrachId.equals(e.getBranchId())) {
					guest.append(", ");
					guest.append(e.getCode());
					guest.append(": ");
					guest.append(e.getName());
				}
			}
		} else if (Event.VISIBILITY_DEPARTMENT.equals(visibility)) {
			for (Employee e : employeeList) {
				if (e.getStatus() != -1 && creatorBrachId.equals(e.getBranchId()) && creatorDepartmentId.equals(e.getDepartmentId())) {
					guest.append(", ");
					guest.append(e.getCode());
					guest.append(": ");
					guest.append(e.getName());
				}
			}
		} else if (Event.VISIBILITY_GROUP.equals(visibility)) {
			for (Employee e : employeeList) {
				if (e.getStatus() != -1 && creatorBrachId.equals(e.getBranchId()) && creatorDepartmentId.equals(e.getDepartmentId()) && creatorGroupId.equals(e.getGroupId())) {
					guest.append(", ");
					guest.append(e.getCode());
					guest.append(": ");
					guest.append(e.getName());
				}
			}
		} else if (Event.VISIBILITY_MANAGER.equals(visibility)) {
			for (Employee e : employeeList) {
				if (e.getStatus() != -1 && creatorManagerId.equals(e.getManagerId())) {
					guest.append(", ");
					guest.append(e.getCode());
					guest.append(": ");
					guest.append(e.getName());
				}
			}
		} else if (Event.VISIBILITY_INTERNAL.equals(visibility)) {
			for (Employee e : employeeList) {
				if (e.getStatus() != -1 && creatorId.equals(e.getId())) {
					guest.append(", ");
					guest.append(e.getCode());
					guest.append(": ");
					guest.append(e.getName());
				}
			}
		}

		String guestStr = guest.toString();
		guestStr = guestStr.indexOf(",") == 0 ? guestStr.substring(2) : guestStr;

		if (selectGuest) {
			eventGuestValue += guestStr;
		} else {
			eventGuestValue = "";
		}

		dto.setEventGuestValue(eventGuestValue);
	}

}
