/*
 * %W% %E%
 *
 * Copyright (c) 2014, AISOFT and/or its affiliates. All rights reserved.
 * AISOFT Business Management System PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.mega.dao.entity;

import java.io.Serializable;
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
		@NamedQuery(name = "Branch.findByCompanyId", query = "select o from Branch o where o.deleted = 0 and o.companyId = :companyId order by o.sort"),
		@NamedQuery(name = "Branch.searchQuick", query = "select o from Branch o where o.deleted = 0 and o.companyId = :companyId and o.status = :status and (lower(o.code) like :code or lower(o.name) like :name) order by o.sort"),
		@NamedQuery(name = "Branch.searchAdvance", query = "select o from Branch o where o.deleted = 0 and o.companyId = :companyId and o.status = :status and lower(o.code) like :code and lower(o.name) like :name order by o.sort"),
		@NamedQuery(name = "Branch.searchByCodeName", query = "select o from Branch o where o.deleted = 0 and o.companyId = :companyId and (lower(o.code) like :code or lower(o.name) like :name) order by o.code ") })
@Table(name = "branch")
public class Branch extends DaoEntity implements Serializable {
	private static final long serialVersionUID = -303260271350152194L;

	public static final Integer STATUS_ACTIVE = 1;
	public static final Integer STATUS_INACTIVE = 0;

	public static final Integer PAGE_BRANCH_VN = 1;
	public static final Integer PAGE_BRANCH_EN = 2;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "company_id")
	private Long companyId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Company company;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "name_full")
	private String nameFull;

	@Column(name = "name_short")
	private String nameShort;

	@Column(name = "name_2")
	private String name2;

	@Column(name = "name_full_2")
	private String nameFull2;

	@Column(name = "name_short_2")
	private String nameShort2;

	@Column(name = "location_id")
	private Long locationId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Location location;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "fax")
	private String fax;

	@Column(name = "email")
	private String email;

	@Column(name = "tax_code")
	private String taxCode;

	@Column(name = "bank_account")
	private String bankAccount;

	@Column(name = "bank_account_name")
	private String bankAccountName;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "bank_branch")
	private String bankBranch;

	@Column(name = "bank_swipt")
	private String bankSwipt;

	@Column(name = "bank_iban")
	private String bankIban;

	@Column(name = "representative")
	private String representative;

	@Column(name = "representative_title")
	private String representativeTitle;

	@Column(name = "contact_name")
	private String contactName;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "address_2")
	private String address2;

	@Column(name = "phone_2")
	private String phone2;

	@Column(name = "fax_2")
	private String fax2;

	@Column(name = "email_2")
	private String email2;

	@Column(name = "bank_account_2")
	private String bankAccount2;

	@Column(name = "bank_account_name_2")
	private String bankAccountName2;

	@Column(name = "bank_name_2")
	private String bankName2;

	@Column(name = "bank_branch_2")
	private String bankBranch2;

	@Column(name = "bank_swipt_2")
	private String bankSwipt2;

	@Column(name = "bank_iban_2")
	private String bankIban2;

	@Column(name = "representative_2")
	private String representative2;

	@Column(name = "representative_title_2")
	private String representativeTitle2;

	@Column(name = "contact_name_2")
	private String contactName2;

	@Column(name = "mobile_2")
	private String mobile2;

	@Column(name = "headquarter")
	private Integer headquarter = 0;

	@Column(name = "report_path")
	private String reportPath;

	@Column(name = "logo")
	private byte[] logo;

	@Column(name = "logo_type")
	private String logoType;

	@Column(name = "letter_head")
	private byte[] letterHead;

	@Column(name = "letter_head_type")
	private String letterHeadType;

	@Column(name = "letter_head_2")
	private byte[] letterHead2;

	@Column(name = "letter_head_type_2")
	private String letterHeadType2;

	@Column(name = "sort")
	private Integer sort;

	@Column(name = "status")
	private Integer status = STATUS_ACTIVE;

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

	public Branch() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameFull() {
		return this.nameFull;
	}

	public void setNameFull(String nameFull) {
		this.nameFull = nameFull;
	}

	public String getNameShort() {
		return nameShort;
	}

	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public String getLocationName() {
		return this.location != null ? this.location.getName() : "";
	}

	public void setLocationName(String locationName) {
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

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTaxCode() {
		return this.taxCode;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getNameFull2() {
		return nameFull2;
	}

	public void setNameFull2(String nameFull2) {
		this.nameFull2 = nameFull2;
	}

	public String getNameShort2() {
		return nameShort2;
	}

	public void setNameShort2(String nameShort2) {
		this.nameShort2 = nameShort2;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getFax2() {
		return fax2;
	}

	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getBankAccount2() {
		return bankAccount2;
	}

	public void setBankAccount2(String bankAccount2) {
		this.bankAccount2 = bankAccount2;
	}

	public String getBankAccountName2() {
		return bankAccountName2;
	}

	public void setBankAccountName2(String bankAccountName2) {
		this.bankAccountName2 = bankAccountName2;
	}

	public String getBankName2() {
		return bankName2;
	}

	public void setBankName2(String bankName2) {
		this.bankName2 = bankName2;
	}

	public String getBankBranch2() {
		return bankBranch2;
	}

	public void setBankBranch2(String bankBranch2) {
		this.bankBranch2 = bankBranch2;
	}

	public String getBankSwipt2() {
		return bankSwipt2;
	}

	public void setBankSwipt2(String bankSwipt2) {
		this.bankSwipt2 = bankSwipt2;
	}

	public String getBankIban2() {
		return bankIban2;
	}

	public void setBankIban2(String bankIban2) {
		this.bankIban2 = bankIban2;
	}

	public String getRepresentative2() {
		return representative2;
	}

	public void setRepresentative2(String representative2) {
		this.representative2 = representative2;
	}

	public String getRepresentativeTitle2() {
		return representativeTitle2;
	}

	public void setRepresentativeTitle2(String representativeTitle2) {
		this.representativeTitle2 = representativeTitle2;
	}

	public String getContactName2() {
		return contactName2;
	}

	public void setContactName2(String contactName2) {
		this.contactName2 = contactName2;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
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

	public String getBankSwipt() {
		return bankSwipt;
	}

	public void setBankSwipt(String bankSwipt) {
		this.bankSwipt = bankSwipt;
	}

	public String getBankIban() {
		return bankIban;
	}

	public void setBankIban(String bankIban) {
		this.bankIban = bankIban;
	}

	public String getRepresentative() {
		return this.representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public String getRepresentativeTitle() {
		return this.representativeTitle;
	}

	public void setRepresentativeTitle(String representativeTitle) {
		this.representativeTitle = representativeTitle;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isHeadquarter() {
		return headquarter > 0;
	}

	public void setHeadquarter(boolean headquarter) {
		this.headquarter = headquarter ? 1 : 0;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getLogoType() {
		return logoType;
	}

	public void setLogoType(String logoType) {
		this.logoType = logoType;
	}

	public byte[] getLetterHead() {
		return letterHead;
	}

	public void setLetterHead(byte[] letterHead) {
		this.letterHead = letterHead;
	}

	public String getLetterHeadType() {
		return letterHeadType;
	}

	public void setLetterHeadType(String letterHeadType) {
		this.letterHeadType = letterHeadType;
	}

	public byte[] getLetterHead2() {
		return letterHead2;
	}

	public void setLetterHead2(byte[] letterHead2) {
		this.letterHead2 = letterHead2;
	}

	public String getLetterHeadType2() {
		return letterHeadType2;
	}

	public void setLetterHeadType2(String letterHeadType2) {
		this.letterHeadType2 = letterHeadType2;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return this.status;
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
