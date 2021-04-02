package org.mega.view.wrk;

import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.mega.common.Lib;
import org.mega.dao.entity.Event;
import org.mega.dto.wrk.WRK012Dto;
import org.mega.view.core.BaseBean;

@ManagedBean(name = "wrk012Bean")
@ViewScoped
public class WRK012Bean extends BaseBean {

	private WRK012Dto dto;

	public WRK012Bean() {
		super();
	}

	@Override
	public String getPageCode() {
		return WRK012;
	}

	@Override
	public String getBussinessCode() {
		return WRK01;
	}

	@Override
	public void initPage() throws Exception {
		dto = getDto();
		dto.setId(Lib.toLong(getParamPage(ID)));
		dto.setFromPage(getParamPage(FROM_PAGE));
		doService("initPage", dto);
	}

	/**
	 * AutoComplete change AssignedBy
	 * 
	 * @throws Exception
	 */
	public void changePicAction() throws Exception {
		doService("changePic", dto);
	}

	/**
	 * Add guest
	 * 
	 * @throws Exception
	 */
	public void changeEventGuestAction() throws Exception {
		doService("changeEventGuest", dto);
	}

	/**
	 * Show/Hide repeat
	 * 
	 * @throws Exception
	 */
	public void changeRepeatAction() throws Exception {
		Event event = dto.getEvent();

		event.setRepeatMon(false);
		event.setRepeatTue(false);
		event.setRepeatWed(false);
		event.setRepeatThu(false);
		event.setRepeatFri(false);
		event.setRepeatSat(false);
		event.setRepeatSun(false);

		event.setRepeatType(null);
		event.setRepeatEvery(null);
		event.setRepeatBy(null);
		event.setEndRepeatType(null);
		event.setEndRepeat(null);
		event.setOccurrences(null);

		if (event.isRepeat()) {
			event.setRepeatType(Event.REPEAT_TYPE_WEEKLY);
			event.setRepeatEvery(1);
			event.setRepeatBy(null);
			event.setEndRepeatType(Event.REPEAT_END_ON);
			event.setEndRepeat(null);
			event.setOccurrences(null);

			Calendar cal = Calendar.getInstance(zone, locale);
			if (event.getStartDate() != null) {
				cal.setTime(event.getStartDate());
			}

			event.setRepeatMon(Calendar.MONDAY == cal.get(Calendar.DAY_OF_WEEK));
			event.setRepeatTue(Calendar.TUESDAY == cal.get(Calendar.DAY_OF_WEEK));
			event.setRepeatWed(Calendar.WEDNESDAY == cal.get(Calendar.DAY_OF_WEEK));
			event.setRepeatThu(Calendar.THURSDAY == cal.get(Calendar.DAY_OF_WEEK));
			event.setRepeatFri(Calendar.FRIDAY == cal.get(Calendar.DAY_OF_WEEK));
			event.setRepeatSat(Calendar.SATURDAY == cal.get(Calendar.DAY_OF_WEEK));
			event.setRepeatSun(Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK));
		}
	}

	/**
	 * Change repeat type
	 * 
	 * @throws Exception
	 */
	public void changeRepeatTypeAction() throws Exception {
		Event event = dto.getEvent();

		event.setRepeatMon(false);
		event.setRepeatTue(false);
		event.setRepeatWed(false);
		event.setRepeatThu(false);
		event.setRepeatFri(false);
		event.setRepeatSat(false);
		event.setRepeatSun(false);
		event.setRepeatEvery(1);
		event.setRepeatBy(null);
		event.setEndRepeatType(Event.REPEAT_END_ON);
		event.setEndRepeat(null);
		event.setOccurrences(null);

		if (Event.REPEAT_TYPE_WEEKLY.equals(event.getRepeatType())) {
			Calendar cal = Calendar.getInstance(zone, locale);
			if (event.getStartDate() != null) {
				cal.setTime(event.getStartDate());
			}

			event.setRepeatMon(Calendar.MONDAY == cal.get(Calendar.DAY_OF_WEEK));
			event.setRepeatTue(Calendar.TUESDAY == cal.get(Calendar.DAY_OF_WEEK));
			event.setRepeatWed(Calendar.WEDNESDAY == cal.get(Calendar.DAY_OF_WEEK));
			event.setRepeatThu(Calendar.THURSDAY == cal.get(Calendar.DAY_OF_WEEK));
			event.setRepeatFri(Calendar.FRIDAY == cal.get(Calendar.DAY_OF_WEEK));
			event.setRepeatSat(Calendar.SATURDAY == cal.get(Calendar.DAY_OF_WEEK));
			event.setRepeatSun(Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK));
		} else if (Event.REPEAT_TYPE_MONTHLY.equals(event.getRepeatType())) {
			event.setRepeatBy(Event.REPEAT_BY_DAYOFMONTH);
		}
	}

	/**
	 * Change end repeat type
	 * 
	 * @throws Exception
	 */
	public void changeEndRepeatTypeAction() throws Exception {
		Event event = dto.getEvent();

		if (Event.REPEAT_END_ON.equals(event.getEndRepeatType())) {
			event.setOccurrences(null);
		} else {
			event.setOccurrences(5);
		}
	}

	/**
	 * Go to list view
	 * 
	 * @return
	 * @throws Exception
	 */
	public String closeAction() throws Exception {
		fromCacheDto(true);
		return goPage(Lib.isEmpty(dto.getFromPage()) ? WRK010 : dto.getFromPage());
	}

	/**
	 * Save data
	 * 
	 * @throws Exception
	 */
	public void saveAction() throws Exception {
		doService("save", dto);

	}

	/**
	 * Print data
	 * 
	 * @throws Exception
	 */
	public void printAction() throws Exception {
		doService("print", dto);

		Event event = dto.getEvent();

		pdfViewReport(dto.getDataExport(), "SK" + event.getId());
		dto.setDataExport(null);
	}

	/**
	 * Processing event
	 * 
	 * @throws Exception
	 */
	public void processingAction() throws Exception {
		doService("processing", dto);
	}

	/**
	 * Done event
	 * 
	 * @throws Exception
	 */
	public void doneAction() throws Exception {
		doService("done", dto);
	}

	/**
	 * Delete action
	 * 
	 * @throws Exception
	 */
	public String deleteAction() throws Exception {
		doService("delete", dto);
		fromCacheDto(true);
		return goPage(Lib.isEmpty(dto.getFromPage()) ? WRK010 : dto.getFromPage());
	}

	/**
	 * Request Confirm
	 * 
	 * @return
	 * @throws Exception
	 */
	public void deleteConfirmAction() throws Exception {
		messageDto.showConf(Lib.getMessage(locale, CM0001), "", "", "cbDelete");
	}

	public void selectGuestAction() throws Exception {
		doService("selectGuest", dto);
	}
}
