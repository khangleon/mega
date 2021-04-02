package org.mega.dto.adm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.mega.common.Lib;
import org.mega.constant.Constant;
import org.mega.dao.entity.UserBusiness;
import org.mega.dao.entity.UserRole;
import org.mega.dao.entity.Users;
import org.mega.dto.core.Dto;
import org.mega.dto.core.SelectItem;

public class ADM011Dto extends Dto implements Serializable {
	private static final long serialVersionUID = 2960092198761182144L;
	private Long userId;
	private Users user;
	private List<UserRole> userRole;
	private List<UserBusiness> userBusiness;
	private List<SelectItem> statusItems;
	private List<SelectItem> groupItems;
	private List<SelectItem> levelDataItems;
	private List<SelectItem> branchItems;
	private String password;

	private boolean editPass = false;
	private boolean selectAll1 = false;
	private boolean selectAll2 = false;
	private boolean selectAll3 = false;

	public ADM011Dto() {

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}

	public List<UserBusiness> getUserBusiness() {
		return userBusiness;
	}

	public void setUserBusiness(List<UserBusiness> userBusiness) {
		this.userBusiness = userBusiness;
	}

	public String getStatusName(Integer value) {
		return getLabel(getStatusItems(), value);
	}

	public List<SelectItem> getStatusItems() {
		if (statusItems == null) {
			statusItems = new ArrayList<SelectItem>();
			statusItems.add(new SelectItem(Users.STATUS_RESET, Lib.getResource(getLocale(), "users.status.1")));
			statusItems.add(new SelectItem(Users.STATUS_ACTIVE, Lib.getResource(getLocale(), "users.status.2")));
			statusItems.add(new SelectItem(Users.STATUS_OPEN, Lib.getResource(getLocale(), "users.status.3")));
		}
		return statusItems;
	}

	public void setStatusItems(List<SelectItem> statusItems) {
		this.statusItems = statusItems;
	}

	public String getGroupName(Integer value) {
		return getLabel(getGroupItems(), value);
	}

	public List<SelectItem> getGroupItems() {
		if (groupItems == null) {
			groupItems = new ArrayList<SelectItem>();
			groupItems.add(new SelectItem(Users.GROUP_GUEST, Lib.getResource(getLocale(), "users.group.1")));
			groupItems.add(new SelectItem(Users.GROUP_PARTNER, Lib.getResource(getLocale(), "users.group.2")));
			groupItems.add(new SelectItem(Users.GROUP_EMPLOYEE, Lib.getResource(getLocale(), "users.group.3")));
			groupItems.add(new SelectItem(Users.GROUP_ADMIN, Lib.getResource(getLocale(), "users.group.4")));
		}
		return groupItems;
	}

	public void setGroupItems(List<SelectItem> groupItems) {
		this.groupItems = groupItems;
	}

	public String getLevelData(Integer value) {
		return getLabel(getLevelDataItems(), value);
	}

	public List<SelectItem> getLevelDataItems() {
		if (levelDataItems == null) {
			levelDataItems = new ArrayList<SelectItem>();
			levelDataItems.add(new SelectItem(Constant.LEVEL_DATA_SELF, Lib.getResource(getLocale(), "level.data.1")));
			levelDataItems.add(new SelectItem(Constant.LEVEL_DATA_GROUP, Lib.getResource(getLocale(), "level.data.3")));
			levelDataItems.add(new SelectItem(Constant.LEVEL_DATA_DEPARTMENT, Lib.getResource(getLocale(),
					"level.data.4")));
			levelDataItems
					.add(new SelectItem(Constant.LEVEL_DATA_BRANCH, Lib.getResource(getLocale(), "level.data.5")));
			levelDataItems
					.add(new SelectItem(Constant.LEVEL_DATA_COMPANY, Lib.getResource(getLocale(), "level.data.6")));
		}
		return levelDataItems;
	}

	public void setLevelDataItems(List<SelectItem> levelDataItems) {
		this.levelDataItems = levelDataItems;
	}

	public List<SelectItem> getBranchItems() {
		return branchItems;
	}

	public void setBranchItems(List<SelectItem> branchItems) {
		this.branchItems = branchItems;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEditPass() {
		return editPass;
	}

	public void setEditPass(boolean editPass) {
		this.editPass = editPass;
	}

	public boolean isSelectAll1() {
		return selectAll1;
	}

	public void setSelectAll1(boolean selectAll1) {
		this.selectAll1 = selectAll1;
	}

	public boolean isSelectAll2() {
		return selectAll2;
	}

	public void setSelectAll2(boolean selectAll2) {
		this.selectAll2 = selectAll2;
	}

	public boolean isSelectAll3() {
		return selectAll3;
	}

	public void setSelectAll3(boolean selectAll3) {
		this.selectAll3 = selectAll3;
	}

	public String getImage() {
		return user != null ? Lib.encodeBase64(user.getImage(), user.getImageType()) : "";
	}

	public void setImage(String image) {
	}

}
