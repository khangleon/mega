package org.mega.view.wrk;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.mega.common.Lib;
import org.mega.dao.entity.Event;
import org.mega.dto.wrk.WRK011Dto;
import org.mega.view.core.BaseBean;

@ManagedBean(name = "wrk011Bean")
@ViewScoped
public class WRK011Bean extends BaseBean {
	private WRK011Dto dto;

	public WRK011Bean() {
		super();
	}

	@Override
	public void initPage() throws Exception {
		dto = getDto();
		dto.setId(Lib.toLong(getParamPage(ID)));
		dto.setFromPage(getParamPage(FROM_PAGE));
		doService("initPage", dto);
	}

	@Override
	public String getPageCode() {
		return WRK011;
	}

	@Override
	public String getBussinessCode() {
		return WRK01;
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
	 * Edit data
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editAction() throws Exception {
		putParamPage(ID, dto.getId());
		return goPage(WRK012, dto.getFromPage());
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
}
