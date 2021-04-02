package org.mega.dto.adm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.mega.common.Lib;
import org.mega.dao.entity.Users;
import org.mega.dto.core.SelectItem;
import org.mega.dto.core.TableDto;

public class ADM010Dto extends TableDto implements Serializable {
	private static final long serialVersionUID = -6958926456485034223L;
	private String account;
	private String name;
	private String phone;
	private String email;
	private Integer status = 0;
	private Integer group = 0;
	private List<SelectItem> statusItems;
	private List<SelectItem> groupItems;

	private List<Users> dataTable;

	public ADM010Dto() {

	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public String getStatusName(Integer value) {
		return getLabel(getStatusItems(), value);
	}

	public List<SelectItem> getStatusItems() {
		if (statusItems == null) {
			statusItems = new ArrayList<SelectItem>();
			statusItems.add(new SelectItem(0, Lib.getResource(getLocale(), "common.label.all")));
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
			groupItems.add(new SelectItem(0, Lib.getResource(getLocale(), "common.label.all")));
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

	public List<Users> getDataTable() {
		return this.dataTable;
	}

	@SuppressWarnings("unchecked")
	public void setDataTable(List<?> dataTable) {
		this.dataTable = (List<Users>) dataTable;
	}

}
