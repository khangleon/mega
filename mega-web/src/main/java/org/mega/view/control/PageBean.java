package org.mega.view.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.mega.common.Lib;
import org.mega.dao.entity.Menu;
import org.mega.dao.entity.Page;
import org.mega.dto.core.PageDto;
import org.mega.view.core.BaseBean;

@ManagedBean(name = "page")
@ViewScoped
public class PageBean extends BaseBean {
	private Map<String, Page> pages;
	private PageDto pageDto;

	public PageBean() {
		super();
		try {
			initPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getPageCode() {
		return "page";
	}

	public void initPage() throws Exception {
		this.pageDto = getDto(PAGE_DTO);
		doService("initApp", this.pageDto);
	}

	public String getTitle() {
		return "Mega";
	}

	public void changeMenu() throws Exception {
		List<Menu> mainList = this.pageDto.getMainMenu();

		Menu main = null;
		for (Menu menu : mainList) {
			if (menu.getId().equals(this.pageDto.getMainSelected())) {
				main = menu;
				break;
			}
		}

		this.pageDto.setSubMenu(main.getSubMenu());
		this.pageDto.setMenuSelected(0L);
		this.pageDto.setPageUrl(null);
	}

	public void changeSubMenu() throws Exception {
		List<Menu> mainList = this.pageDto.getMainMenu();

		Menu main = null;
		for (Menu menu : mainList) {
			if (menu.getId().equals(this.pageDto.getMainSelected())) {
				main = menu;
				break;
			}
		}

		List<Menu> subMenuList = main.getSubMenu();
		Menu sub = null;
		for (Menu menu : subMenuList) {
			if (menu.getId().equals(this.pageDto.getMenuSelected())) {
				sub = menu;
				break;
			}
		}

		this.pageDto.setSubMenu(subMenuList);
		this.pageDto.setPageUrl(sub.getPage().getUrl());
	}

	public String nextPage(String pageId) {
		String nextPage = null;

		if (Lib.isEmpty(pageId) || pageId.equals(pageDto.getPageId())) {
			nextPage = null;
		} else {
			Page page = getNextPage(pageId);
			if (page != null) {
				if (page.getLayout().equals(pageDto.getLayout())) {
					pageDto.setPageUrl(page.getUrl());
					nextPage = null;
				} else {
					pageDto.setPageUrl(page.getUrl());
					nextPage = page.getLayout();
				}
			} else {
				// TODO show error message
			}
		}

		return nextPage;
	}

	private Page getNextPage(String pageId) {
		if (pages == null) {
			pages = new HashMap<String, Page>();
		}

		return pages.get(pageId);
	}

	public String systemAction() throws Exception {
		System.out.println("Da vo systemAction");

		doService("insertMenu", this.pageDto);

		return null;
	}

	public String settingAction() throws Exception {
		System.out.println("Da vo settingAction: ");

		doService("updateMenu", this.pageDto);

		return null;
	}

	public String accountAction() throws Exception {
		doService("mainMenu", this.pageDto);
		return null;
	}

	/**
	 * Refresh message
	 * 
	 * @throws Exception
	 */
	public void refeshMessageAction() throws Exception {
		//doService("refeshMessage", this.pageDto);
	}

	/**
	 * Refresh message
	 * 
	 * @throws Exception
	 */
	public void unReadMessageAction() throws Exception {
		//doService("unReadMessage", this.pageDto);
	}

	/**
	 * Change Password Action
	 * 
	 * @throws Exception
	 */
	public String changePasswordAction() throws Exception {
		putParamPage("changePassword", true);
		return LOGIN;
	}

}
