/*
 * %W% %E%
 *
 * Copyright (c) 2014, AISOFT and/or its affiliates. All rights reserved.
 * AISOFT Business Management System PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.mega.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.mega.dao.core.DaoEntity;

@Entity
@NamedQueries({
		@NamedQuery(name = "Employee.findAll", query = "select o from Employee o where o.deleted = 0 and o.status != -1 and o.companyId = :companyId order by o.name"),
		@NamedQuery(name = "Employee.findByCode", query = "select o from Employee o where o.deleted = 0 and o.companyId = :companyId and o.code = :code"),
		@NamedQuery(name = "Employee.findManager", query = "select o from Employee o where o.deleted = 0 and o.companyId = :companyId and (:branchId = 0 or o.branchId = :branchId) and (:departmentId = 0 or o.departmentId = :departmentId) and o.manager = 1 order by o.name"),
		@NamedQuery(name = "Employee.findEmployee", query = "select o from Employee o where o.deleted = 0 and o.companyId = :companyId and o.status != -1 and o.branchId = :branchId and (:departmentId = 0 or o.departmentId = :departmentId) and (:groupId = 0 or o.groupId = :groupId) order by o.name"),
		@NamedQuery(name = "Employee.searchQuick", query = "select o from Employee o where o.deleted = 0 and o.companyId = :companyId and (0 < o.status) and (o.code like :code or o.name like :name) and (o.id = :employeeId or (o.id != :employeeId and ((:levelData = :levelGroup and o.groupId = :groupId and o.groupId > 0) or (:levelData = :levelDepartment and o.departmentId = :departmentId) or (:levelData = :levelBranch and o.branchId = :branchId) or :levelData = :levelCompany))) order by o.name"),
		@NamedQuery(name = "Employee.searchAdvance", query = "select o from Employee o where o.deleted = 0 and o.companyId = :companyId and (:branchId = 0 or o.branchId = :branchId) and (:status = 0 or o.status = :status) and (:departmentId = 0 or o.departmentId = :departmentId) and lower(o.code) like :code and lower(o.name) like :name and lower(o.identify) like :identify and lower(o.phone) like :phone and lower(o.email) like :email and (o.id = :employeeId or (o.id != :employeeId and ((:levelData = :levelGroup and o.groupId = :groupId and o.groupId > 0) or (:levelData = :levelDepartment and o.departmentId = :departmentId) or (:levelData = :levelBranch and o.branchId = :branchId) or :levelData = :levelCompany))) order by o.name"),
		@NamedQuery(name = "Employee.searchByCodeName", query = "select o from Employee o where o.deleted = 0 and o.active = 1 and o.companyId = :companyId and o.status > 0 and (:branchId = 0 or o.branchId = :branchId) and (:departmentId = 0 or o.departmentId = :departmentId) and (:groupId = 0 or o.groupId is null or o.groupId = :groupId) and (lower(o.code) like :code or lower(o.name) like :name) order by o.name") })
@Table(name = "employee")
public class Employee extends DaoEntity implements Serializable {
	private static final long serialVersionUID = 8610227367871390539L;

	public static final Integer STATUS_TRAILWORK = 1;
	public static final Integer STATUS_OFFICIAL = 2;
	public static final Integer STATUS_LEAVE_JOB = -1;

	public static final String SEX_MALE = "M";
	public static final String SEX_FEMALE = "F";
	public static final String SEX_ORTHER = "O";

	public static final String MARITAL_STATUS_1 = "SINGLE";
	public static final String MARITAL_STATUS_2 = "MARRIED";
	public static final String MARITAL_STATUS_3 = "WIDOWED";
	public static final String MARITAL_STATUS_4 = "SEPARATED";
	public static final String MARITAL_STATUS_5 = "DIVORCED";
	public static final String MARITAL_STATUS_6 = "NOTREPORTED";

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "tel")
	private String tel;

	@Column(name = "work_place")
	private String workPlace;

	@Column(name = "work_address")
	private String workAddress;

	@Column(name = "work_tel")
	private String workTel;

	@Column(name = "company_id")
	private Long companyId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Company company;

	@Column(name = "branch_id")
	private Long branchId = 0L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "branch_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Branch branch;

	@Column(name = "department_id")
	private Long departmentId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Department department;

	@Column(name = "group_id")
	private Long groupId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id", referencedColumnName = "id", insertable = false, updatable = false)
	private EmployeeGroup group;

	@Column(name = "job_title")
	private String jobTitle;

	@Column(name = "manager_id")
	private Long managerId;

	@Column(name = "supervisor_id")
	private Long supervisorId;

	@Column(name = "manager")
	private Integer manager = 0;

	@Column(name = "identify")
	private String identify;

	@Temporal(TemporalType.DATE)
	@Column(name = "identify_date")
	private Date identifyDate;

	@Column(name = "identify_issued")
	private String identifyIssued;

	@Column(name = "passport")
	private String passport;

	@Temporal(TemporalType.DATE)
	@Column(name = "passport_date")
	private Date passportDate;

	@Column(name = "passport_issued")
	private String passportIssued;

	@Column(name = "bank_account")
	private String bankAccount;

	@Column(name = "bank_account_name")
	private String bankAccountName;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "bank_branch")
	private String bankBranch;

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "birth_place")
	private String birthPlace;

	@Column(name = "resident")
	private String resident;

	@Column(name = "current_address")
	private String currentAddress;

	@Column(name = "national")
	private String national;

	@Column(name = "personal_email")
	private String personalEmail;

	@Column(name = "sex")
	private String sex = SEX_MALE;

	@Column(name = "married")
	private String married = MARITAL_STATUS_1;

	@Column(name = "number_children")
	private Integer numberChildren;

	@Column(name = "number_dependent")
	private Integer numberDependent;

	@Column(name = "employee_code")
	private String employeeCode;

	@Column(name = "salary_code")
	private String salaryCode;

	@Column(name = "allowance_code")
	private String allowanceCode;

	@Column(name = "vacation_code")
	private String vacationCode;

	@Column(name = "vacation_day")
	private Integer vacationDay = 0;

	@Column(name = "vacation_used_day")
	private Integer vacationUsedDay;

	@Column(name = "notes")
	private String notes;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "jion_date")
	private Date jionDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "out_date")
	private Date outDate;

	@Column(name = "active")
	private Integer active = 1;

	@Column(name = "open")
	private Integer open = 1;

	@Column(name = "image")
	private byte[] image;

	@Column(name = "image_type")
	private String imageType;

	@Column(name = "status")
	private Integer status = STATUS_TRAILWORK;

	@Column(name = "deleted")
	private Integer deleted = 0;

	@Column(name = "deleted_id")
	private Long deletedId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_date")
	private Date deletedDate;

	@Column(name = "created_id")
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

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	private List<Users> users;

	public Employee() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWorkPlace() {
		return this.workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getWorkAddress() {
		return this.workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getWorkTel() {
		return this.workTel;
	}

	public void setWorkTel(String workTel) {
		this.workTel = workTel;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getCompanyName() {
		return this.company != null ? this.company.getName() : null;
	}

	public void setCompanyName(String companyName) {

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
		return this.branch != null ? this.branch.getName() : null;
	}

	public void setBranchName(String branchName) {

	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getDepartmentName() {
		return this.department != null ? this.department.getName() : null;
	}

	public void setDepartmentName(String departmentName) {

	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public EmployeeGroup getGroup() {
		return group;
	}

	public void setGroup(EmployeeGroup group) {
		this.group = group;
	}

	public String getGroupName() {
		return this.group != null ? this.group.getName() : null;
	}

	public void setGroupName(String groupName) {

	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Long getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}

	public boolean isManager() {
		return this.manager > 0;
	}

	public void setManager(boolean manager) {
		this.manager = manager ? 1 : 0;
	}

	public String getIdentify() {
		return this.identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public Date getIdentifyDate() {
		return this.identifyDate;
	}

	public void setIdentifyDate(Date identifyDate) {
		this.identifyDate = identifyDate;
	}

	public String getIdentifyIssued() {
		return this.identifyIssued;
	}

	public void setIdentifyIssued(String identifyIssued) {
		this.identifyIssued = identifyIssued;
	}

	public String getPassport() {
		return this.passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public Date getPassportDate() {
		return this.passportDate;
	}

	public void setPassportDate(Date passportDate) {
		this.passportDate = passportDate;
	}

	public String getPassportIssued() {
		return this.passportIssued;
	}

	public void setPassportIssued(String passportIssued) {
		this.passportIssued = passportIssued;
	}

	public String getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankAccountName() {
		return this.bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return this.bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getResident() {
		return this.resident;
	}

	public void setResident(String resident) {
		this.resident = resident;
	}

	public String getCurrentAddress() {
		return this.currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getNational() {
		return this.national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getPersonalEmail() {
		return this.personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMarried() {
		return this.married;
	}

	public void setMarried(String married) {
		this.married = married;
	}

	public Integer getNumberChildren() {
		return this.numberChildren;
	}

	public void setNumberChildren(Integer numberChildren) {
		this.numberChildren = numberChildren;
	}

	public Integer getNumberDependent() {
		return numberDependent;
	}

	public void setNumberDependent(Integer numberDependent) {
		this.numberDependent = numberDependent;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getSalaryCode() {
		return this.salaryCode;
	}

	public void setSalaryCode(String salaryCode) {
		this.salaryCode = salaryCode;
	}

	public String getAllowanceCode() {
		return this.allowanceCode;
	}

	public void setAllowanceCode(String allowanceCode) {
		this.allowanceCode = allowanceCode;
	}

	public String getVacationCode() {
		return this.vacationCode;
	}

	public void setVacationCode(String vacationCode) {
		this.vacationCode = vacationCode;
	}

	public Integer getVacationDay() {
		return vacationDay;
	}

	public void setVacationDay(Integer vacationDay) {
		this.vacationDay = vacationDay;
	}

	public Integer getVacationUsedDay() {
		return this.vacationUsedDay;
	}

	public void setVacationUsedDay(Integer vacationUsedDay) {
		this.vacationUsedDay = vacationUsedDay;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getJionDate() {
		return jionDate;
	}

	public void setJionDate(Date jionDate) {
		this.jionDate = jionDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public Long getUsersId() {
		Long id = 0L;
		if (this.users != null && this.users.size() > 0) {
			id = users.get(0).getId();
		}
		return id;
	}

	public void setUsersId(Long usersId) {

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

}
