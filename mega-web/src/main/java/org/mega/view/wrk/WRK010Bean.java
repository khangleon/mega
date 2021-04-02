package org.mega.view.wrk;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.mega.common.Lib;
import org.mega.dao.entity.Event;
import org.mega.dto.wrk.WRK010Dto;
import org.mega.view.core.BaseBean;

@ManagedBean(name = "wrk010Bean")
@ViewScoped
public class WRK010Bean extends BaseBean {
	private WRK010Dto dto;

	public WRK010Bean() {
		super();
	}

	@Override
	public String getPageCode() {
		return WRK010;
	}

	@Override
	public String getBussinessCode() {
		return WRK01;
	}

	@Override
	public void initPage() throws Exception {
		dto = getDto();
		dto.setId(Lib.toLong(getParamPage(ID)));
		doService("initPage", dto);
	}

	/**
	 * Search Quick
	 * 
	 * @throws Exception
	 */
	public void searchQuickAction() throws Exception {
		doService("searchQuick", dto);
	}

	/**
	 * Search Advance
	 * 
	 * @throws Exception
	 */
	public void searchAdvanceAction() throws Exception {
		doService("searchAdvance", dto);
	}

	/**
	 * Add new data
	 * @return
	 * @throws Exception
	 */
	public String addNewAction() throws Exception {
		return goPage(WRK012, true);
	}

	/**
	 * View detail data
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewAction() throws Exception {
		Long id = dto.getIdSelected();
		if (id == null || id < 0) {
			List<Event> dataTable = dto.getDataTable();
			if (dataTable != null) {
				for (Event e : dataTable) {
					if (e.isSelected()) {
						id = e.getId();
						break;
					}
				}
			}
		}

		putParamPage(ID, id);
		dto.setIdSelected(null);
		return goPage(WRK011, true);
	}

}
