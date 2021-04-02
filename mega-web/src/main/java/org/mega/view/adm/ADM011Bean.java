package org.mega.view.adm;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.mega.common.Lib;
import org.mega.dto.adm.ADM011Dto;
import org.mega.view.core.BaseBean;

@ManagedBean(name = "adm011Bean")
@ViewScoped
public class ADM011Bean extends BaseBean {
	private ADM011Dto dto;

	public ADM011Bean() {
		super();
	}

	@Override
	public String getPageCode() {
		return ADM011;
	}

	@Override
	public String getBussinessCode() {
		return ADM01;
	}

	@Override
	public void initPage() throws Exception {
		dto = getDto();
		dto.setId(Lib.toLong(getParamPage(ID)));
		dto.setFromPage(getParamPage(FROM_PAGE));
		doService("initPage", dto);
	}

	/**
	 * Back to List View
	 * 
	 * @return
	 * @throws Exception
	 */
	public String closeAction() throws Exception {
		fromCacheDto(true);
		return goPage(Lib.isEmpty(dto.getFromPage()) ? ADM010 : dto.getFromPage(), true);
	}

	/**
	 * Edit data
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editAction() throws Exception {
		putParamPage(ID, dto.getId());
		return goPage(ADM012, dto.getFromPage());
	}

	/**
	 * Disable
	 * 
	 * @return
	 * @throws Exception
	 */
	public void disableAction() throws Exception {
		doService("disable", dto);
	}

	/**
	 * Enable user
	 * 
	 * @return
	 * @throws Exception
	 */
	public void enableAction() throws Exception {
		doService("enable", dto);
	}

	/**
	 * Close user
	 * 
	 * @return
	 * @throws Exception
	 */
	public void lockAction() throws Exception {
		doService("lock", dto);
	}

	/**
	 * Open
	 * 
	 * @return
	 * @throws Exception
	 */
	public void unlockAction() throws Exception {
		doService("unlock", dto);
	}

	/**
	 * Reset password
	 * 
	 * @throws Exception
	 */
	public void resetPasswordAction() throws Exception {
		dto.setEditPass(true);
		dto.getUser().setPassword("");
	}

	/**
	 * Delete user
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteAction() throws Exception {
		doService("delete", dto);
		return goPage(ADM010);
	}
}
