package org.mega.view.adm;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.mega.dao.entity.Users;
import org.mega.dto.adm.ADM010Dto;
import org.mega.view.core.BaseBean;

@ManagedBean(name = "adm010Bean")
@ViewScoped
public class ADM010Bean extends BaseBean {
	private ADM010Dto dto;

	public ADM010Bean() {
		super();
	}

	@Override
	public String getPageCode() {
		return ADM010;
	}

	@Override
	public String getBussinessCode() {
		return ADM01;
	}

	@Override
	public void initPage() throws Exception {
		dto = getDto();
		doService("initPage", dto);

		System.out.println("ADM010 : initPage");
	}

	/**
	 * Add new record
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addNewAction() throws Exception {
		return goPage(ADM012, true);
	}

	/**
	 * View user
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewAction() throws Exception {
		dto = getDto();
		Long id = dto.getIdSelected();
		if (id == null || id < 0) {
			List<Users> dataTable = dto.getDataTable();
			if (dataTable != null) {
				for (Users e : dataTable) {
					if (e.isSelected()) {
						id = e.getId();
						break;
					}
				}
			}
		}

		putParamPage(ID, id);
		return goPage(ADM011, true);
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void searchQuickAction() throws Exception {
		doService("searchQuick", dto);
	}

	public void searchAdvanceAction() throws Exception {
		doService("searchAdvance", dto);
	}

}
