package org.mega.business.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.mega.client.LocalService;
import org.mega.client.ParamQuery;
import org.mega.client.RemoteService;
import org.mega.dao.entity.Menu;
import org.mega.dao.entity.Page;
import org.mega.dto.core.PageDto;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless(mappedName = "MEGA", name = "pageService")
public class PageService extends CommonService implements RemoteService, LocalService {
	public PageService() {
		super();
	}

	public void initApp(PageDto dto) {
		System.out.println("initApp....");
	}

	public void mainMenu(PageDto dto) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Menu.findMainMenu");
		List<Menu> mainMenu = getEntities(paramQuery);

		System.out.println("mainMenu: " + mainMenu.size());
	}

	public Page getPage(String code) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Page.findByCode");
		paramQuery.addParam("code", code);

		Page page = getEntity(paramQuery);
		page = page == null ? new Page() : page;
		return page;
	}

}
