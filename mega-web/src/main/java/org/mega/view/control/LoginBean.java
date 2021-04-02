package org.mega.view.control;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mega.common.Lib;
import org.mega.common.RandomString;
import org.mega.dao.entity.Menu;
import org.mega.dao.entity.Page;
import org.mega.dto.core.LoginDto;
import org.mega.dto.core.LoginInfo;
import org.mega.dto.core.PageDto;
import org.mega.view.core.BaseBean;
import org.mega.view.util.JSFUtils;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean extends BaseBean {
	private static final String EM1001 = "EM1001";
	private static final String EM1002 = "EM1002";
	private static final String EM1003 = "EM1003";
	private static final String EM1006 = "EM1006";
	private static final String EM1007 = "EM1007";

	private LoginDto dto;

	public LoginBean() {
		super();
	}

	@Override
	public String getPageCode() {
		return "login";
	}

	@Override
	public void initPage() throws Exception {
		dto = getDto();
		refreshCapchaAction();

		if (Lib.toBoolean(getParamPage("changePassword"), false)) {
			goChangePasswordAction();
		}
	}

	public String loginAction() throws Exception {
		if (Lib.isEmpty(dto.getAccount()) || Lib.isEmpty(dto.getPassword())) {
			if (Lib.isEmpty(dto.getAccount())) {
				messageDto.showError(Lib.getMessage(locale, EM0001), Lib.getResource(locale, "login.username"),
						"itAccount");
			}
			if (Lib.isEmpty(dto.getPassword())) {
				messageDto.showError(Lib.getMessage(locale, EM0001), Lib.getResource(locale, "login.password"),
						"itPassword");
			}
			refreshCapchaAction();
			return null;
		}

		// Check capcha
		String capcha = dto.getCapcha();
		String capchaRandom = dto.getCapchaRandom();
		int capchaIndex = dto.getCapchaIndex();
		if (Lib.isEmpty(capcha) || Lib.isEmpty(capchaRandom) || capchaIndex < 0 || capchaIndex > 5
				|| !capcha.equals(capchaRandom.substring(capchaIndex, capchaIndex + 1))) {
			//messageDto.showError(Lib.getMessage(locale, EM1001), Lib.getResource(locale, "login.capcha", ""),    "itCapcha");
			//refreshCapchaAction();
			//return null;
		}

		// Check block
		if (dto.getLoginFailedTimes() > 5) {
			if (Lib.diffMinute(new Date(), dto.getLoginFailedDate()) < 10) {
				messageDto.showError(Lib.getMessage(locale, EM1003, dto.getAccount()));
				return null;
			} else {
				dto.setLoginFailedTimes(0);
				dto.setLoginFailedDate(new Date());
			}
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		/** get user session id */
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		String sessionId = session.getId();

		/** get user IP address */
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		String remoteAddr = request.getRemoteAddr();
		String remoteHost = request.getRemoteHost();
		Integer remotePort = request.getRemotePort();
		String localName = request.getLocalName();
		Integer localPort = request.getLocalPort();
		Integer serverPort = request.getServerPort();
		String serverName = request.getServerName();
		String serverPath = request.getServletPath();

		dto.setSessionId(sessionId);
		dto.setRemoteAddr(remoteAddr);
		dto.setRemoteHost(remoteHost);
		dto.setRemotePort(remotePort);
		dto.setLocalName(localName);
		dto.setLocalPort(localPort);
		dto.setServerPort(serverPort);
		dto.setServerName(serverName);
		dto.setServerPath(serverPath);

		doService("login", dto);

		if (dto.isLoginOk()) {
			dto.setLoginOk(false);
			LoginInfo loginInfo = dto.getLoginInfo();
			JSFUtils.setBean("loginInfo", loginInfo);

			PageDto pageDto = getDto(PAGE_DTO);

			List<Menu> mainMenu = dto.getMainMenu();
			List<Menu> subMenu = null;
			Long mainSelected = dto.getMainDefault() != null ? dto.getMainDefault().getId() : 0L;
			mainSelected = mainSelected == null ? 0L : mainSelected;
			if (mainMenu != null) {
				for (int i = 0; i < mainMenu.size(); i++) {
					if (mainSelected.equals(mainMenu.get(i).getId())) {
						subMenu = mainMenu.get(i).getSubMenu();
						break;
					}
				}

				Long menuSelected = dto.getSubDefault() != null ? dto.getSubDefault().getId() : 0L;
				menuSelected = menuSelected == null ? 0L : menuSelected;
				Menu menu = null;
				Page page = null;
				if (subMenu != null) {
					for (int i = 0; i < subMenu.size(); i++) {
						menu = subMenu.get(i);
						if (menuSelected.equals(menu.getId())) {
							page = menu.getPage();
							pageDto.setLayout(page.getLayout());
							pageDto.setPageUrl(page.getUrl());
							break;
						}
					}
				}

				pageDto.setMainMenu(mainMenu);
				pageDto.setMainSelected(mainSelected);
				pageDto.setSubMenu(subMenu);
				pageDto.setMenuSelected(menuSelected);
			}
		} else {
			refreshCapchaAction();
			return null;
		}

		return redirectPage("page");
	}

	/**
	 * Generate capcha
	 * 
	 * @throws Exception
	 */
	public void refreshCapchaAction() throws Exception {
		RandomString strRandom = new RandomString(6);
		String capchaRandom = strRandom.nextString();

		Random random = new Random();
		int capchaIndex = random.nextInt(6);
		capchaIndex = capchaIndex > 5 ? 5 : capchaIndex;

		String capchaPlaceHolder = Lib.getResource(dto.getLocale(), "login.capcha", capchaIndex + 1);

		dto.setPassword("");
		dto.setCapcha("");
		dto.setCapchaRandom(capchaRandom);
		dto.setCapchaIndex(capchaIndex);
		dto.setCapchaPlaceHolder(capchaPlaceHolder);
	}

	/**
	 * Go to mode change password
	 * 
	 * @throws Exception
	 */
	public void goChangePasswordAction() throws Exception {
		dto.setPassword("");
		dto.setNewPassword("");
		dto.setRetypePassword("");
		dto.setChangePassword(true);
	}

	/**
	 * Go to mode Login
	 * 
	 * @throws Exception
	 */
	public void goLoginAction() throws Exception {
		dto.setPassword("");
		dto.setNewPassword("");
		dto.setRetypePassword("");
		dto.setChangePassword(false);
	}

	/**
	 * Change password
	 * 
	 * @throws Exception
	 */
	public void changePasswordAction() throws Exception {
		if (Lib.isEmpty(dto.getAccount()) || Lib.isEmpty(dto.getPassword()) || Lib.isEmpty(dto.getNewPassword())
				|| Lib.isEmpty(dto.getRetypePassword())) {
			if (Lib.isEmpty(dto.getAccount())) {
				messageDto.showError(Lib.getMessage(locale, EM0001), Lib.getResource(locale, "login.username"),
						"itAccount");
			}
			if (Lib.isEmpty(dto.getPassword())) {
				messageDto.showError(Lib.getMessage(locale, EM0001), Lib.getResource(locale, "login.currentpassword"),
						"itCurrentPassword");
			}
			if (Lib.isEmpty(dto.getNewPassword())) {
				messageDto.showError(Lib.getMessage(locale, EM0001), Lib.getResource(locale, "login.newpassword"),
						"itNewPassword");
			}
			if (Lib.isEmpty(dto.getRetypePassword())) {
				messageDto.showError(Lib.getMessage(locale, EM0001), Lib.getResource(locale, "login.retypepassword"),
						"itRetypePassword");
			}
			refreshCapchaAction();
			return;
		}

		// Check capcha
		String capcha = dto.getCapcha();
		String capchaRandom = dto.getCapchaRandom();
		int capchaIndex = dto.getCapchaIndex();
		if (Lib.isEmpty(capcha) || Lib.isEmpty(capchaRandom) || capchaIndex < 0 || capchaIndex > 5
				|| !capcha.equals(capchaRandom.substring(capchaIndex, capchaIndex + 1))) {
			messageDto.showError(Lib.getMessage(locale, EM1001), Lib.getResource(locale, "login.capcha", ""), "itCapcha");
			refreshCapchaAction();
			return;
		}

		String newPassword = dto.getNewPassword();
		String retypePassword = dto.getRetypePassword();
		if (Lib.isEmpty(newPassword) || Lib.isEmpty(retypePassword) || !newPassword.equals(retypePassword)) {
			messageDto.showError(Lib.getMessage(locale, EM1007));
			refreshCapchaAction();
			return;
		}

		if (newPassword.equals(dto.getPassword())) {
			messageDto.showError(Lib.getMessage(locale, EM1006));
			refreshCapchaAction();
			return;
		}

		// Check block
		if (dto.getLoginFailedTimes() > 5) {
			if (Lib.diffMinute(new Date(), dto.getLoginFailedDate()) < 10) {
				messageDto.showError(Lib.getMessage(locale, EM1003, dto.getAccount()));
				return;
			} else {
				dto.setLoginFailedTimes(0);
				dto.setLoginFailedDate(new Date());
			}
		}

		doService("changePassword", dto);

		if (dto.isLoginOk()) {
			dto.setLoginOk(false);
			goLoginAction();
		} else {
			messageDto.showError(Lib.getMessage(locale, EM1002, 5, 10));
			refreshCapchaAction();
		}
	}
	
	/**
	 * LogoutAction
	 * 
	 * @throws Exception
	 */
	public String logoutAction() throws Exception {
		if (dto == null) {
			dto = getDto();
		}
		loginInfo = JSFUtils.findBean("loginInfo");
		dto.setLoginInfo(loginInfo);
		doService("logout", dto);
		JSFUtils.setBean("loginInfo", null);
		return LOGIN;
	}

}
