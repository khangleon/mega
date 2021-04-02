package org.mega.business.wrk;

import java.util.List;

import org.mega.business.service.CommonService;
import org.mega.client.ParamQuery;
import org.mega.common.Lib;
import org.mega.dao.entity.Event;
import org.mega.dao.entity.EventGuest;
import org.mega.dto.wrk.WRK011Dto;

public class WRK01Service extends CommonService {

	public WRK01Service() {
		super();
	}

	/**
	 * Get guest event
	 * 
	 * @param eventId
	 * @return
	 */
	public List<EventGuest> getEventGuest(Long eventId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("EventGuest.findByEventId");
		paramQuery.addParam("eventId", eventId);
		List<EventGuest> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Guest display
	 * 
	 * @param eventId
	 * @return
	 */
	public String getGuestValue(Long eventId) {
		String str = "";
		List<EventGuest> items = getEventGuest(eventId);
		if (items != null) {
			for (EventGuest e : items) {
				str += ", " + e.getGuestCode() + ": " + e.getGuestName();
			}
		}
		str = str.length() > 1 ? str.substring(2) : "";
		return str;
	}

	/**
	 * Role control: cbSave cbEdit cbPrint cbProcessing cbDone cbStandard
	 * cbDelete
	 * 
	 * @param dto
	 */
	public void roleControl(WRK011Dto dto) {
		Event event = dto.getEvent();
		dto.disabled(true, "cbSave", "cbEdit", "cbPrint", "cbProcessing", "cbDone", "cbStandard", "cbDelete");

		Long id = event.getId();
		Long loginEmployeId = loginInfo.getEmployeeId();
		if (Event.STATUS_NEW.equals(event.getStatus())) {
			if (id == null) {
				dto.disabled(false, "cbSave", "cbEdit");
			} else {
				if (loginEmployeId != null
						&& (loginEmployeId.equals(event.getCreatorId()) || loginEmployeId.equals(event.getPicId()))) {
					dto.disabled(false, "cbSave", "cbEdit", "cbPrint", "cbProcessing", "cbDone", "cbStandard",
							"cbDelete");
				} else {
					dto.disabled(false, "cbPrint", "cbProcessing", "cbDone", "cbStandard");
				}
			}
		} else if (Event.STATUS_PROCESSING.equals(event.getStatus())) {
			if (loginEmployeId != null
					&& (loginEmployeId.equals(event.getCreatorId()) || loginEmployeId.equals(event.getPicId()))) {
				dto.disabled(false, "cbSave", "cbEdit", "cbPrint", "cbProcessing", "cbDone", "cbStandard", "cbDelete");
			} else {
				dto.disabled(false, "cbPrint", "cbProcessing", "cbDone", "cbStandard");
			}
		} else if (Event.STATUS_DONE.equals(event.getStatus())) {
			dto.disabled(false, "cbPrint", "cbStandard");
		} else if (Event.STATUS_CANCELED.equals(event.getStatus())) {
			if (loginEmployeId != null
					&& (loginEmployeId.equals(event.getCreatorId()) || loginEmployeId.equals(event.getPicId()))) {
				dto.disabled(false, "cbSave", "cbEdit", "cbDelete");
			}
		}
	}

	/**
	 * Processing event
	 * 
	 * @param dto
	 */
	public void processing(WRK011Dto dto) {
		Event event = dto.getEvent();
		event.setStatus(Event.STATUS_PROCESSING);
		event = updateEntity(event);
		dto.setEvent(event);
		if (!messageDto.isShowMessage()) {
			messageDto.showTop(Lib.getMessage(locale, IM0003));
		}
	}

	/**
	 * Sold data
	 * 
	 * @param dto
	 */
	public void done(WRK011Dto dto) {
		Event event = dto.getEvent();
		event.setStatus(Event.STATUS_DONE);
		event = updateEntity(event);
		dto.setEvent(event);

		if (!messageDto.isShowMessage()) {
			messageDto.showTop(Lib.getMessage(locale, IM0003));
		}
	}

	/**
	 * Delete data
	 * 
	 * @param dto
	 */
	public void delete(WRK011Dto dto) {
		Event event = dto.getEvent();
		Long id = event.getId();
		if (id == null) {
			String message = Lib.getMessage(locale, EM0014);
			messageDto.showError(message);
			return;
		}
		List<EventGuest> guestList = getEventGuest(event.getId());

		event = deleteEntity(event);
		guestList = deleteEntities(guestList);

		dto.setId(id);
		dto.setEvent(event);
		dto.setEventGuestValue("");

		if (!messageDto.isShowMessage()) {
			messageDto.showTop(Lib.getMessage(locale, IM0002));
		}
	}

	/**
	 * Print data
	 * @param dto
	 */
	public void print(WRK011Dto dto) {

	}

}
