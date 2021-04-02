package org.mega.business.adm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.mega.client.LocalService;
import org.mega.client.RemoteService;
import org.mega.common.Lib;
import org.mega.dao.entity.Employee;
import org.mega.dao.entity.UserBusiness;
import org.mega.dao.entity.UserRole;
import org.mega.dao.entity.Users;
import org.mega.dto.adm.ADM012Dto;

/**
 * Session Bean implementation class ADM010Service
 * 
 * @author Admin
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless(mappedName = "MEGA", name = "adm012Service")
public class ADM012Service extends ADM01Service implements RemoteService, LocalService {

	public ADM012Service() {
		super();
	}

	/**
	 * Load initial
	 * 
	 * @param dto
	 */
	public void initPage(ADM012Dto dto) {
		Users user = findEntity(Users.class, dto.getId());

		if (user == null) {
			user = new Users();
			Date curDate = getTimestamp();
			user.setAccountExpiry(Lib.addYear(curDate, 1, zone, locale));
			user.setPasswordExpiry(Lib.addDate(curDate, getPasswordExpiryDate(), zone, locale));
			user.setCompanyId(loginInfo.getCompanyId());
			user.setBranchId(loginInfo.getBranchId());
			user.setBranch(loginInfo.getBranch());
			dto.setEditPass(true);
		}
		user.getBranch();

		dto.setBranchItems(getBranchItems(getBranchList(loginInfo.getCompanyId())));

		dto.setUser(user);

		loadRole(dto);

		roleControl(dto);

		// Setup business flow
		businessFlow(dto);
	}

	/**
	 * Save data
	 * 
	 * @param dto
	 */
	public void save(ADM012Dto dto) {
		if (!saveValidate(dto)) {
			return;
		}

		Users user = dto.getUser();
		if (dto.isEditPass()) {
			user.setPassword(Lib.getPassword(dto.getPassword()));
			user.setPasswordExpiry(Lib.addDate(getTimestamp(), 45, zone, locale));
			user.setChangePassword(true);
			dto.setEditPass(false);
		}

		user = updateEntity(user);

		Long userId = user.getId();
		List<UserRole> userRoleList = dto.getUserRole();
		List<UserBusiness> userBusinessList = dto.getUserBusiness();
		List<UserBusiness> userBusinessOld = getUserBusiness(userId);

		// Remove user business
		Map<Long, Long> bussiness = new HashMap<Long, Long>();
		for (UserBusiness e : userBusinessList) {
			if (e.getId() != null) {
				bussiness.put(e.getId(), e.getId());
			}
		}

		List<UserBusiness> userBusinessDelete = new ArrayList<UserBusiness>();
		if (userBusinessOld != null) {
			for (UserBusiness e : userBusinessOld) {
				if (bussiness.get(e.getId()) == null) {
					userBusinessDelete.add(e);
				}
			}
		}

		List<UserRole> userRoleUpdate = new ArrayList<UserRole>();
		List<UserRole> userRoleDelete = new ArrayList<UserRole>();
		for (UserRole e : userRoleList) {
			if (e.isSelected()) {
				e.setUserId(userId);
				userRoleUpdate.add(e);
			} else {
				if (e.getId() != null) {
					userRoleDelete.add(e);
				}
			}
		}

		userRoleUpdate = updateEntities(userRoleUpdate);
		userRoleDelete = removeEntities(userRoleDelete);
		userBusinessList = updateEntities(userBusinessList);

		// Remove user business
		userBusinessDelete = removeEntities(userBusinessDelete);

		dto.setId(userId);
		dto.setUser(user);
		loadRole(dto);

		roleControl(dto);

		if (!messageDto.isShowMessage()) {
			messageDto.showTop(Lib.getMessage(dto.getLocale(), IM0001));
			// loadRole(dto);
		}
	}

	/**
	 * Validate data
	 * 
	 * @param dto
	 * @return
	 */
	private boolean saveValidate(ADM012Dto dto) {
		boolean valid = true;
		Users user = dto.getUser();
		String message = "";
		String label = "";

		if (Lib.isEmpty(user.getAccount())) {
			message = Lib.getMessage(dto.getLocale(), EM0001);
			label = Lib.getResource(dto.getLocale(), "adm01.label.account");
			messageDto.showError(message, label, "itAccount");
			valid = false;
		}

		if (Lib.isEmpty(user.getName())) {
			message = Lib.getMessage(dto.getLocale(), EM0001);
			label = Lib.getResource(dto.getLocale(), "adm01.label.name");
			messageDto.showError(message, label, "itName");
			valid = false;
		}

		if (dto.isEditPass() && Lib.isEmpty(dto.getPassword())) {
			message = Lib.getMessage(dto.getLocale(), EM0001);
			label = Lib.getResource(dto.getLocale(), "adm01.label.password");
			messageDto.showError(message, label, "itPassword");
			valid = false;
		}

		if (checkExist(user.getAccount(), user.getId())) {
			message = Lib.getMessage(dto.getLocale(), EM0021, user.getAccount());
			label = Lib.getResource(dto.getLocale(), "adm01.label.account");
			messageDto.showError(message, label, "itAccount");
			valid = false;
		}

		return valid;
	}

	/**
	 * Disable account
	 * 
	 * @param dto
	 */
	public void disable(ADM012Dto dto) {
		super.disable(dto);
	}

	/**
	 * Enable account
	 * 
	 * @param dto
	 */
	public void enable(ADM012Dto dto) {
		super.enable(dto);
	}

	/**
	 * Lock account
	 * 
	 * @param dto
	 */
	public void lock(ADM012Dto dto) {
		super.lock(dto);
	}

	/**
	 * Unlock account
	 * 
	 * @param dto
	 */
	public void unlock(ADM012Dto dto) {
		super.unlock(dto);
	}

	/**
	 * Change password
	 * 
	 * @param dto
	 */
	public void delete(ADM012Dto dto) {
		super.delete(dto);
	}

	/**
	 * Change Select main
	 * 
	 * @param dto
	 */
	public void changeSelectRole(ADM012Dto dto) {
		Integer rowSelected = dto.getRowSelected();
		rowSelected = rowSelected == null ? 0 : rowSelected - 1;
		loadBusiness(dto, true);
	}

	/**
	 * Auto Complete employee
	 * 
	 * @param dto
	 */
	public void changeEmployee(ADM012Dto dto) {
		Users user = dto.getUser();
		Long employeeId = user.getEmployeeId();

		Employee employee = findEntity(Employee.class, employeeId);
		user.setEmployee(employee);

		if (employee != null) {
			user.setBranchId(employee.getBranchId());
			user.setBranch(employee.getBranch());
			user.setName(employee.getName());
			user.setAddress(employee.getCurrentAddress());
			user.setPhone(employee.getPhone());
			user.setEmail(employee.getEmail());
			user.setImage(employee.getImage());
			user.setImageType(employee.getImageType());
		} else {
			user.setEmployeeId(null);
		}
	}

}
