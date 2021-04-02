package org.mega.business.adm;

import java.util.ArrayList;
import java.util.List;

import org.mega.business.service.CommonService;
import org.mega.client.ParamQuery;
import org.mega.common.Lib;
import org.mega.dao.entity.Page;
import org.mega.dao.entity.Role;
import org.mega.dao.entity.UserBusiness;
import org.mega.dao.entity.UserRole;
import org.mega.dao.entity.Users;
import org.mega.dto.adm.ADM011Dto;

public class ADM01Service extends CommonService {

	public ADM01Service() {
		super();
	}

	/**
	 * Role control: cbSave cbPrint cbCopy cbStandard cbDelete cbSubmit
	 * cbCancelSubmit cbApprove cbCancelApprove cbReject cbAdvance cbRepayment
	 * cbPrevBusiness cbNextBusiness cbCancel
	 * 
	 * @param dto
	 */
	public void roleControl(ADM011Dto dto) {
		Users user = dto.getUser();

		if (user.isActive()) {
			dto.rendered(false, "cbEnable");
			dto.rendered(true, "cbDisable");
		} else {
			dto.rendered(true, "cbEnable");
			dto.rendered(false, "cbDisable");
		}

		if (user.isOpen()) {
			dto.rendered(true, "cbLock");
			dto.rendered(false, "cbUnlock");
		} else {
			dto.rendered(false, "cbLock");
			dto.rendered(true, "cbUnlock");
		}
	}

	/**
	 * Load role
	 * 
	 * @param dto
	 */
	public void loadRole(ADM011Dto dto) {
		Users user = dto.getUser();
		Long userId = user.getId();

		List<UserRole> userRoleList = getUserRole(userId);
		List<Role> roleList = getRole();

		userRoleList = userRoleList == null ? new ArrayList<UserRole>() : userRoleList;
		Role role = null;
		UserRole userRole = null;
		Long roleId = null;

		List<UserRole> userRoleDisplay = new ArrayList<UserRole>();
		if (roleList != null) {
			for (int i = 0; i < roleList.size(); i++) {
				role = roleList.get(i);
				roleId = role.getId();
				userRole = null;
				for (int j = 0; j < userRoleList.size(); j++) {
					if (roleId.equals(userRoleList.get(j).getRoleId())) {
						userRole = userRoleList.remove(j);
						break;
					}
				}

				// Set display
				if (userRole == null) {
					userRole = new UserRole();
					userRole.setUserId(userId);
					userRole.setRoleId(roleId);
					userRole.setSelected(false);
				} else {
					userRole.setSelected(true);
				}
				userRole.setRoleCode(role.getCode());
				userRole.setRoleName(role.getName());
				userRole.setLevelData(role.getLevelData());
				userRole.setIndex(i + 1);

				userRoleDisplay.add(userRole);
			}
		}

		dto.setUserRole(userRoleDisplay);

		loadBusiness(dto);
	}

	/**
	 * Load business
	 * 
	 * @param dto
	 */
	public void loadBusiness(ADM011Dto dto) {
		loadBusiness(dto, false);
	}

	public void loadBusiness(ADM011Dto dto, boolean resetLevelData) {

		Users user = dto.getUser();
		Long userId = user.getId();
		Integer levelData = 0;

		List<UserRole> userRoleList = dto.getUserRole();
		List<UserBusiness> userBusinessList = getUserBusiness(userId);
		List<Long> roleIdList = new ArrayList<Long>();
		if (userRoleList != null) {
			for (UserRole e : userRoleList) {
				if (e.isSelected()) {
					roleIdList.add(e.getRoleId());
					if (e.getLevelData() > levelData) {
						levelData = e.getLevelData();
					}
				}
			}
		}

		if (roleIdList.isEmpty()) {
			roleIdList.add(-1L);
		}
		List<Page> businessList = getRoleBusiness(roleIdList);

		userBusinessList = userBusinessList == null ? new ArrayList<UserBusiness>() : userBusinessList;
		Page page = null;
		UserBusiness userBusiness = null;
		String business = null;
		List<UserBusiness> userBusinessDisplay = new ArrayList<UserBusiness>();
		if (businessList != null) {
			for (int i = 0; i < businessList.size(); i++) {
				page = businessList.get(i);

				business = page.getBusiness();
				userBusiness = null;
				for (int j = 0; j < userBusinessList.size(); j++) {
					if (business.equals(userBusinessList.get(j).getBusiness())) {
						userBusiness = userBusinessList.remove(j);
						break;
					}
				}

				// Set display
				if (userBusiness == null) {
					userBusiness = new UserBusiness();
					userBusiness.setUserId(userId);
					userBusiness.setBusiness(page.getBusiness());
					userBusiness.setLevelData(levelData);
					userBusiness.setSelected(false);
				} else {
					userBusiness.setSelected(true);
					if (resetLevelData) {
						userBusiness.setLevelData(levelData);
					}
				}
				userBusiness.setBusinessName(page.getName());
				userBusiness.setIndex(i + 1);

				userBusinessDisplay.add(userBusiness);
			}
		}

		dto.setUserBusiness(userBusinessDisplay);
	}

	/**
	 * Disable account
	 * 
	 * @param dto
	 */
	public void disable(ADM011Dto dto) {
		Users user = dto.getUser();
		user.setActive(false);
		user = updateEntity(user);
		dto.setUser(user);
		roleControl(dto);

		if (!messageDto.isShowMessage()) {
			messageDto.showTop(Lib.getMessage(dto.getLocale(), IM0003));
		}
	}

	/**
	 * Enable account
	 * 
	 * @param dto
	 */
	public void enable(ADM011Dto dto) {
		Users user = dto.getUser();
		user.setActive(true);
		user = updateEntity(user);
		dto.setUser(user);
		roleControl(dto);

		if (!messageDto.isShowMessage()) {
			messageDto.showTop(Lib.getMessage(dto.getLocale(), IM0003));
		}
	}

	/**
	 * Open account
	 * 
	 * @param dto
	 */
	public void unlock(ADM011Dto dto) {
		Users user = dto.getUser();
		user.setOpen(true);
		user = updateEntity(user);
		dto.setUser(user);
		roleControl(dto);

		if (!messageDto.isShowMessage()) {
			messageDto.showTop(Lib.getMessage(dto.getLocale(), IM0003));
		}
	}

	/**
	 * Close account
	 * 
	 * @param dto
	 */
	public void lock(ADM011Dto dto) {
		Users user = dto.getUser();
		user.setOpen(false);
		user = updateEntity(user);
		dto.setUser(user);
		roleControl(dto);

		if (!messageDto.isShowMessage()) {
			messageDto.showTop(Lib.getMessage(dto.getLocale(), IM0003));
		}
	}

	/**
	 * Delete account
	 * 
	 * @param dto
	 */
	public void delete(ADM011Dto dto) {
		Users user = dto.getUser();
		Long userId = user.getId();
		if (userId == null) {
			return;
		}

		user = deleteEntity(user);
		removeEntities(getUserRole(userId));
		removeEntities(getUserBusiness(userId));

		if (!messageDto.isShowMessage()) {
			messageDto.showTop(Lib.getMessage(locale, IM0002));
		}

	}

	/**
	 * Get Role
	 * 
	 * @return
	 */
	public boolean checkExist(String account, Long id) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Users.findByAccount");
		paramQuery.addParam("account", account);
		paramQuery.setFirst(true);
		Users entity = getEntity(paramQuery);

		return entity != null && !entity.getId().equals(id);
	}

	/**
	 * Get Role
	 * 
	 * @return
	 */
	public List<Role> getRole() {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Role.findAll");
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		List<Role> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get Role of User
	 * 
	 * @return
	 */
	public List<UserRole> getUserRole(Long userId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("UserRole.findByUser");
		paramQuery.addParam("userId", userId);
		List<UserRole> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get Business of Role
	 * 
	 * @return
	 */
	public List<Page> getRoleBusiness(List<Long> roleList) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Page.findByRole");
		paramQuery.addParam("roleList", roleList);
		List<Page> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get Business of User
	 * 
	 * @return
	 */
	public List<UserBusiness> getUserBusiness(Long userId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("UserBusiness.findByUser");
		paramQuery.addParam("userId", userId);
		List<UserBusiness> list = getEntities(paramQuery);
		return list;
	}

}
