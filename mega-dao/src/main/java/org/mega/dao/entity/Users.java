/*
 * %W% %E%
 *
 * Copyright (c) 2014, AISOFT and/or its affiliates. All rights reserved.
 * AISOFT Business Management System PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.mega.dao.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.mega.dao.core.DaoEntity;

@Entity
@NamedQueries({
		@NamedQuery(name = "Users.findAll", query = "select o from Users o where o.deleted = 0 order by o.name"),
		@NamedQuery(name = "Users.findByAccountExternal", query = "select o from Users o where o.deleted = 0 and :curDate between o.accessStartDate and o.accessEndDate order by o.accessStartDate, o.accessEndDate"),
		@NamedQuery(name = "Users.findByAccount", query = "select o from Users o where o.deleted = 0 and o.account = :account"),
		@NamedQuery(name = "Users.searchByCodeName", query = "select o from Users o where o.deleted = 0 and (lower(o.account) like :account or lower(o.name) like :name) order by o.name asc"),
		@NamedQuery(name = "Users.findByEmployee", query = "select o from Users o where o.deleted = 0 and o.employeeId = :employeeId"),
		@NamedQuery(name = "Users.searchQuick", query = "select o from Users o where o.deleted = 0 and o.companyId = :companyId and (:branchId = 0 or o.branchId = :branchId) and o.active = :active and o.open = :open and (lower(o.account) like :account or lower(o.name) like :name) order by o.name"),
		@NamedQuery(name = "Users.searchAdvance", query = "select o from Users o where o.deleted = 0 and o.companyId = :companyId and (:branchId = 0 or o.branchId = :branchId) and (:status = 0 or o.active = :active or o.open = :open or o.changePassword = :changePassword) and (:userGroup = 0 or o.userGroup = :userGroup) and lower(o.account) like :account and lower(o.name) like :name and lower(o.phone) like :phone and lower(o.email) like :email order by o.name") })
@Table(name = "users")
public class Users extends DaoEntity implements Serializable {
	private static final long serialVersionUID = 2276437152338636209L;

	public static final Integer STATUS_RESET = 1;
	public static final Integer STATUS_ACTIVE = 2;
	public static final Integer STATUS_OPEN = 3;

	public static final Integer GROUP_GUEST = 1;
	public static final Integer GROUP_PARTNER = 2;
	public static final Integer GROUP_EMPLOYEE = 3;
	public static final Integer GROUP_ADMIN = 4;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "account")
	private String account;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "employee_id")
	private Long employeeId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Employee employee;

	@Column(name = "user_group")
	private Integer userGroup = GROUP_EMPLOYEE;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "account_expiry")
	private Date accountExpiry;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "password_expiry")
	private Date passwordExpiry;

	@Column(name = "change_password")
	private Integer changePassword = 1;

	@Column(name = "active")
	private Integer active = 1;

	@Column(name = "open")
	private Integer open = 1;

	@Column(name = "image")
	private byte[] image;

	@Column(name = "image_type")
	private String imageType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "access_start_date")
	private Date accessStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "access_end_date")
	private Date accessEndDate;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "branch_id")
	private Long branchId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Branch branch;

	@Column(name = "deleted")
	private Integer deleted = 0;

	@Column(name = "deleted_id")
	private Long deletedId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_date", updatable = false)
	private Date deletedDate;

	@Column(name = "created_id", updatable = false)
	private Long createdId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_id")
	private Long updatedId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;

	@Version
	@Column(name = "version")
	private Integer version = 0;

	public Users() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public byte[] getEmployeeImageData() {
		return this.employee != null ? this.employee.getImage() : null;
	}

	public void setEmployeeImageData(byte[] employeeImageData) {
	}

	public String getEmployeeImageType() {
		return this.employee != null ? this.employee.getImageType() : null;
	}

	public void setEmployeeImageType(String employeeImageType) {
	}

	public String getEmployeeCode() {
		return employee != null ? employee.getCode() : null;
	}

	public void setEmployeeCode(String employee) {
	}

	public Integer getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(Integer userGroup) {
		this.userGroup = userGroup;
	}

	public Date getAccountExpiry() {
		return accountExpiry;
	}

	public void setAccountExpiry(Date accountExpiry) {
		this.accountExpiry = accountExpiry;
	}

	public Date getPasswordExpiry() {
		return passwordExpiry;
	}

	public void setPasswordExpiry(Date passwordExpiry) {
		this.passwordExpiry = passwordExpiry;
	}

	public boolean isChangePassword() {
		return changePassword > 0;
	}

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword ? 1 : 0;
	}

	public boolean isActive() {
		return active > 0;
	}

	public void setActive(boolean active) {
		this.active = active ? 1 : 0;
	}

	public boolean isOpen() {
		return open > 0;
	}

	public void setOpen(boolean open) {
		this.open = open ? 1 : 0;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public Date getAccessStartDate() {
		return accessStartDate;
	}

	public void setAccessStartDate(Date accessStartDate) {
		this.accessStartDate = accessStartDate;
	}

	public Date getAccessStartTime() {
		return accessStartDate;
	}

	public void setAccessStartTime(Date accessStartTime) {
		if (this.accessStartDate == null) {
			this.accessStartDate = accessStartTime;
		} else if (accessStartTime != null) {
			Calendar date = Calendar.getInstance();
			Calendar time = Calendar.getInstance();
			date.setTime(this.accessStartDate);
			time.setTime(accessStartTime);
			date.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
			date.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
			this.accessStartDate = date.getTime();
		}
	}

	public Date getAccessEndDate() {
		return accessEndDate;
	}

	public void setAccessEndDate(Date accessEndDate) {
		this.accessEndDate = accessEndDate;
	}

	public Date getAccessEndTime() {
		return accessEndDate;
	}

	public void setAccessEndTime(Date accessEndTime) {
		if (this.accessEndDate == null) {
			this.accessEndDate = accessEndTime;
		} else if (accessEndTime != null) {
			Calendar date = Calendar.getInstance();
			Calendar time = Calendar.getInstance();
			date.setTime(this.accessEndDate);
			time.setTime(accessEndTime);
			date.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
			date.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
			this.accessEndDate = date.getTime();
		}
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getBranchName() {
		return branch != null ? branch.getName() : null;
	}

	public void setBranchName(String branch) {
	}

	public boolean isDeleted() {
		return this.deleted > 0;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted ? 1 : 0;
	}

	public Long getDeletedId() {
		return this.deletedId;
	}

	public void setDeletedId(Long deletedId) {
		this.deletedId = deletedId;
	}

	public Date getDeletedDate() {
		return this.deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public Long getCreatedId() {
		return this.createdId;
	}

	public void setCreatedId(Long createdId) {
		this.createdId = createdId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedId() {
		return this.updatedId;
	}

	public void setUpdatedId(Long updatedId) {
		this.updatedId = updatedId;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Transient
	private boolean selected = false;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Transient
	private int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Transient
	private boolean edit = false;

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public boolean isEmpty() {
		boolean ret = false;
		if ((account == null || "".equals(account.trim()))) {
			ret = true;
		}
		return ret;
	}
}
