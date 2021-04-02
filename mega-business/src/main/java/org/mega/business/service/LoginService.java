package org.mega.business.service;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.mega.client.LocalService;
import org.mega.client.ParamQuery;
import org.mega.client.RemoteService;
import org.mega.common.Lib;
import org.mega.dao.entity.Branch;
import org.mega.dao.entity.Company;
import org.mega.dao.entity.Employee;
import org.mega.dao.entity.Menu;
import org.mega.dao.entity.Page;
import org.mega.dao.entity.RoleBusinessControl;
import org.mega.dao.entity.UserBusiness;
import org.mega.dao.entity.Users;
import org.mega.dto.core.LoginDto;
import org.mega.dto.core.LoginInfo;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless(mappedName = "MEGA", name = "loginService")
public class LoginService extends CommonService implements RemoteService, LocalService {

	public LoginService() {
		super();
	}

	/**
	 * Login system
	 * 
	 * @param dto
	 */
	public void login(LoginDto dto) {
		String account = dto.getAccount();
		String password = dto.getPassword();
		password = Lib.getPassword(password);
		Date curDate = getTimestamp();
		Users user = getUser(account);

		if (user != null && password != null && password.equals(user.getPassword())) {
			dto.setLoginOk(true);

			// Check expire
			if (user.getAccountExpiry() != null && curDate.after(user.getAccountExpiry())) {
				messageDto.showError(Lib.getMessage(locale, "EM1004", account));
				dto.setLoginOk(false);
			}

			if (user.getPasswordExpiry() != null) {
				if (curDate.after(user.getPasswordExpiry())) {
					messageDto.showError(Lib.getMessage(locale, "EM1005"));
					dto.setLoginOk(false);
				} else {
					long diff = Lib.diffDay(user.getPasswordExpiry(), curDate);
					if (diff < 15) {
						messageDto.showWarn(Lib.getMessage(locale, "WM0001", diff));
					}
				}
			}

			if (!user.isActive() || !user.isOpen()) {
				dto.setLoginOk(false);
				if (user.isChangePassword()) {
					messageDto.showError(Lib.getMessage(locale, "EM1008", account));
				} else {
					messageDto.showError(Lib.getMessage(locale, "EM1004", account));
				}
			}
		} else {
			dto.setLoginFailedDate(new Date());
			dto.setLoginFailedTimes(dto.getLoginFailedTimes() + 1);
			messageDto.showError(Lib.getMessage(locale, "EM1002", 5, 10));
			dto.setLoginOk(false);
		}

		if (dto.isLoginOk()) {
			loginInfo = createLoginInfo(user);
			loginInfo.setAccount(account);
			loginInfo.setSessionId(dto.getSessionId());
			loginInfo.setRemoteAddr(dto.getRemoteAddr());
			loginInfo.setRemoteHost(dto.getRemoteHost());
			loginInfo.setRemotePort(dto.getRemotePort());
			loginInfo.setLocalName(dto.getLocalName());
			loginInfo.setLocalPort(dto.getLocalPort());
			loginInfo.setServerPort(dto.getServerPort());
			loginInfo.setServerName(dto.getServerName());
			loginInfo.setServerPath(dto.getServerPath());

			dto.setLoginInfo(loginInfo);

			// Create menu
			Long userId = user.getId();
			List<Menu> mainMenu = getMainMenu(userId);

			if (mainMenu != null && !mainMenu.isEmpty()) {
				for (Menu menu : mainMenu) {
					menu.setSubMenu(getSubMenu(menu.getId(), userId));
				}

				Menu mainDefault = getMainDefault(userId);
				if (mainDefault == null) {
					mainDefault = mainMenu.get(0);
				}

				Menu subDefault = getSubDefault(mainDefault.getId(), userId);
				if (subDefault == null) {
					List<Menu> subMenu = mainDefault.getSubMenu();
					if (subMenu != null && !subMenu.isEmpty()) {
						subDefault = subMenu.get(0);
					}
				}

				dto.setMainMenu(mainMenu);
				dto.setMainDefault(mainDefault);
				dto.setSubDefault(subDefault);
			}
		}
	}

	public void logout(LoginDto dto) {
		System.out.println("Logout....");
	}

	/**
	 * Check access control
	 * 
	 * @param dto
	 * @return
	 */
	public boolean isLimitAccess(LoginDto dto) {
		return false;
	}

	public boolean isPublicDomain(String serverName) {
		return true;
	}

	public boolean isLocalDomain(String serverName) {
		return false;
	}

	/**
	 * Check ip address permission
	 * 
	 * @param remoteAddr
	 * @param remoteHost
	 * @param accessIp
	 * @return
	 */
	public boolean isAccessIpList(String remoteAddr, String remoteHost, String accessIp) {
		if (accessIp == null || accessIp.isEmpty()) {
			return false;
		}
		try {
			accessIp = accessIp.replaceAll("[\\r\\n]+\\s", "");
			String[] ipList = accessIp.split(",");
			String[] ipArr = null;
			String ipFrom = null;
			String ipTo = null;
			String matchIp = null;
			int from = 0;
			int to = 0;
			int ip = 0;
			for (String e : ipList) {
				if (e == null || e.trim().isEmpty()) {
					continue;
				}
				if (e.indexOf("*") > 0) {
					e = e.trim();
					matchIp = e.substring(0, e.indexOf("*"));
					if (remoteAddr.startsWith(matchIp) || remoteHost.startsWith(matchIp)) {
						return true;
					}
				} else if (e.indexOf("-") > 0) {
					ipArr = e.split("-");
					if (ipArr.length > 1) {
						ipFrom = ipArr[0].trim();
						ipTo = ipArr[1].trim();
						if (isIPv4Address(ipFrom)) {
							from = Lib.toInteger(ipFrom.substring(ipFrom.lastIndexOf(".") + 1), 0);
							to = Lib.toInteger(ipFrom.substring(ipTo.lastIndexOf(".") + 1), 0);
							if (isIPv4Address(remoteAddr)) {
								ip = Lib.toInteger(remoteAddr.substring(remoteAddr.lastIndexOf(".") + 1), 0);
							} else if (isIPv4Address(remoteHost)) {
								ip = Lib.toInteger(remoteHost.substring(remoteHost.lastIndexOf(".") + 1), 0);
							}
							if (ip >= from && ip <= to) {
								return true;
							}
						} else if (isIPv6Address(ipFrom)) {

						}
					} else if (ipArr.length > 0) {
						ipFrom = ipArr[0].trim();
						if (ipFrom.equals(remoteAddr) || ipFrom.equals(remoteHost)) {
							return true;
						}
					}
				} else if (e.equals(remoteAddr) || e.equals(remoteHost)) {
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean isIPv4Address(String address) {
		if (address.isEmpty()) {
			return false;
		}
		try {
			Object res = InetAddress.getByName(address);
			return res instanceof Inet4Address;
		} catch (final UnknownHostException ex) {
			return false;
		}
	}

	public static boolean isIPv6Address(String address) {
		if (address.isEmpty()) {
			return false;
		}
		try {
			Object res = InetAddress.getByName(address);
			return res instanceof Inet6Address;
		} catch (final UnknownHostException ex) {
			return false;
		}
	}

	/**
	 * Create login info
	 * 
	 * @param user
	 * @return
	 */
	private LoginInfo createLoginInfo(Users user) {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUserId(user.getId());
		loginInfo.setUserName(user.getName());
		loginInfo.setUser(user);

		Employee employee = findEntity(Employee.class, user.getEmployeeId());

		if (employee != null) {
			loginInfo.setEmployeeId(employee.getId());
			loginInfo.setEmployeeName(employee.getName());
			loginInfo.setEmployee(employee);

			loginInfo.setCompanyId(employee.getCompanyId());
			loginInfo.setCompanyName(employee.getCompanyName());
			loginInfo.setCompany(employee.getCompany());

			loginInfo.setBranchId(employee.getBranchId());
			loginInfo.setBranchName(employee.getBranchName());
			loginInfo.setBranch(employee.getBranch());

			loginInfo.setDepartmentId(employee.getDepartmentId());
			loginInfo.setDepartmentName(employee.getDepartmentName());
			loginInfo.setDepartment(employee.getDepartment());
			loginInfo.setGroupId(employee.getGroupId());
			loginInfo.setSupervisorId(employee.getSupervisorId());
			loginInfo.setManager(employee.isManager());

		} else {
			Company company = findEntity(Company.class, user.getCompanyId());
			if (company != null) {
				loginInfo.setCompanyId(company.getId());
				loginInfo.setCompanyName(company.getName());
				loginInfo.setCompany(company);
			}

			Branch branch = findEntity(Branch.class, user.getBranchId());
			if (branch != null) {
				loginInfo.setBranchId(branch.getId());
				loginInfo.setBranchName(branch.getName());
				loginInfo.setBranch(branch);
			}
		}

		List<UserBusiness> userBusinessList = getUserBusiness(user.getId());
		// Create approval privilege
		if (userBusinessList != null) {
			Map<String, Integer> levelData = new HashMap<String, Integer>();
			Map<String, Boolean> approveLevel1 = new HashMap<String, Boolean>();
			Map<String, Boolean> approveLevel2 = new HashMap<String, Boolean>();
			Map<String, Boolean> approveLevel3 = new HashMap<String, Boolean>();
			String business = null;
			String businessDetail = null;
			for (UserBusiness e : userBusinessList) {
				business = e.getBusiness();
				businessDetail = e.getBusinessDetail();
				businessDetail = Lib.isEmpty(businessDetail) ? business : business + "-" + businessDetail;
				levelData.put(business, e.getLevelData());
				approveLevel1.put(businessDetail, e.isLevelApprove1());
				approveLevel2.put(businessDetail, e.isLevelApprove2());
				approveLevel3.put(businessDetail, e.isLevelApprove3());
			}

			loginInfo.setLevelData(levelData);
			loginInfo.setApproveLevel1(approveLevel1);
			loginInfo.setApproveLevel2(approveLevel2);
			loginInfo.setApproveLevel3(approveLevel3);
		}

		List<Page> pageList = getBusinessList(user.getId());
		if (pageList != null) {
			Map<String, String> business = new HashMap<String, String>();
			for (Page e : pageList) {
				business.put(e.getBusiness(), e.getName());
			}

			loginInfo.setBusiness(business);
		}

		List<RoleBusinessControl> businessControlList = getBusinessControlList(user.getId());
		if (businessControlList != null) {
			Map<String, Map<String, String>> mapControl = new HashMap<String, Map<String, String>>();
			Map<String, Map<String, String>> mapActionControl = new HashMap<String, Map<String, String>>();
			Map<String, Map<String, Boolean>> mapRendered = new HashMap<String, Map<String, Boolean>>();
			Map<String, Map<String, Boolean>> mapDisabled = new HashMap<String, Map<String, Boolean>>();
			Map<String, Map<String, Boolean>> mapHidden = new HashMap<String, Map<String, Boolean>>();
			Map<String, Map<String, Boolean>> mapReadOnly = new HashMap<String, Map<String, Boolean>>();

			String curBusiness = null;
			String business = null;
			Map<String, String> control = null;
			Map<String, String> actionControl = null;
			Map<String, Boolean> rendered = null;
			Map<String, Boolean> disabled = null;
			Map<String, Boolean> hidden = null;
			Map<String, Boolean> readOnly = null;
			for (RoleBusinessControl e : businessControlList) {
				business = e.getBusiness();
				if (business.equals(curBusiness)) {
					control.put(e.getControlId(), e.getControlName());
					actionControl.put(e.getActionName(), e.getControlName());
					rendered.put(e.getControlId(), e.isRendered());
					disabled.put(e.getControlId(), e.isDisabled());
					hidden.put(e.getControlId(), e.isHidden());
					readOnly.put(e.getControlId(), e.isReadonly());

				} else {
					curBusiness = business;
					control = new HashMap<String, String>();
					actionControl = new HashMap<String, String>();
					rendered = new HashMap<String, Boolean>();
					disabled = new HashMap<String, Boolean>();
					hidden = new HashMap<String, Boolean>();
					readOnly = new HashMap<String, Boolean>();

					control.put(e.getControlId(), e.getControlName());
					actionControl.put(e.getActionName(), e.getControlName());
					rendered.put(e.getControlId(), e.isRendered());
					disabled.put(e.getControlId(), e.isDisabled());
					hidden.put(e.getControlId(), e.isHidden());
					readOnly.put(e.getControlId(), e.isReadonly());

					mapControl.put(business, control);
					mapActionControl.put(business, actionControl);
					mapRendered.put(business, rendered);
					mapDisabled.put(business, disabled);
					mapHidden.put(business, hidden);
					mapReadOnly.put(business, readOnly);
				}
			}

			loginInfo.setMapControl(mapControl);
			loginInfo.setMapActionControl(mapActionControl);
			loginInfo.setMapRendered(mapRendered);
			loginInfo.setMapDisabled(mapDisabled);
			loginInfo.setMapHidden(mapHidden);
			loginInfo.setMapReadOnly(mapReadOnly);

		}

		return loginInfo;
	}

	/**
	 * Change password
	 * 
	 * @param dto
	 */
	public void changePassword(LoginDto dto) {
		String account = dto.getAccount();
		String password = dto.getPassword();
		password = Lib.getPassword(password);

		String newPassword = dto.getNewPassword();
		newPassword = Lib.getPassword(newPassword);

		Users user = getUser(account);
		if (user != null && password != null && password.equals(user.getPassword())) {
			Date curDate = getTimestamp();
			// Check expire
			if (user.getAccountExpiry() != null && curDate.after(user.getAccountExpiry())) {
				messageDto.showError(Lib.getMessage(locale, "EM1004", account));
				dto.setLoginOk(false);
				return;
			}

			// Change pass
			user.setPassword(newPassword);
			Date passwordExpiryDate = Lib.addDate(getTimestamp(), getPasswordExpiryDate(), zone, locale);
			user.setPasswordExpiry(passwordExpiryDate);
			user.setChangePassword(false);
			user = updateEntity(user);
			dto.setLoginOk(true);
		} else {
			dto.setLoginFailedDate(new Date());
			dto.setLoginFailedTimes(dto.getLoginFailedTimes() + 1);
			messageDto.showError(Lib.getMessage(locale, "EM1002", 5, 10));
			dto.setLoginOk(false);
		}
	}

	private Users getUser(String account) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Users.findByAccount");
		paramQuery.addParam("account", account);
		return getEntity(paramQuery);
	}

	public List<Menu> getMainMenu(Long userId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Menu.findMainMenuUser");
		paramQuery.addParam("userId", userId);
		List<Menu> list = getEntities(paramQuery);

		if (list == null || list.isEmpty()) {
			paramQuery.setName("Menu.findMainMenu");
			list = getEntities(paramQuery);
		}
		return list;
	}

	public List<Menu> getSubMenu(Long mainId, Long userId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Menu.findSubMenuUser");
		paramQuery.addParam("mainId", mainId);
		paramQuery.addParam("userId", userId);
		List<Menu> list = getEntities(paramQuery);

		if (list == null || list.isEmpty()) {
			paramQuery.setName("Menu.findSubMenu");
			list = getEntities(paramQuery);
		}
		return list;
	}

	public List<Menu> getMainMenuPublic(Long userId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Menu.findMainMenuUserPublic");
		paramQuery.addParam("userId", userId);
		List<Menu> list = getEntities(paramQuery);

		if (list == null || list.isEmpty()) {
			paramQuery.setName("Menu.findMainMenuPublic");
			list = getEntities(paramQuery);
		}
		return list;
	}

	public Menu getMainDefault(Long userId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Menu.findMainUserDefault");
		paramQuery.addParam("userId", userId);
		paramQuery.setFirst(true);
		Menu menu = getEntity(paramQuery);

		if (menu == null) {
			paramQuery.setName("Menu.findMainDefault");
			menu = getEntity(paramQuery);
		}
		return menu;
	}

	public Menu getSubDefault(Long mainId, Long userId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Menu.findSubUserDefault");
		paramQuery.addParam("mainId", mainId);
		paramQuery.addParam("userId", userId);
		paramQuery.setFirst(true);
		Menu menu = getEntity(paramQuery);

		if (menu == null) {
			paramQuery.setName("Menu.findSubDefault");
			menu = getEntity(paramQuery);
		}
		return menu;
	}

	public List<Page> getBusinessList(Long userId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Page.findByUser");
		paramQuery.addParam("userId", userId);
		List<Page> list = getEntities(paramQuery);
		return list;
	}

	public List<RoleBusinessControl> getBusinessControlList(Long userId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("RoleBusinessControl.findByUser");
		paramQuery.addParam("userId", userId);
		List<RoleBusinessControl> list = getEntities(paramQuery);
		return list;
	}

}
