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
		@NamedQuery(name = "Customer.findAll", query = "select o from Customer o where o.deleted = 0 order by o.name"),
		@NamedQuery(name = "Customer.findByCode", query = "select o from Customer o where o.deleted = 0 and o.companyId = :companyId and o.code = :code"),
		@NamedQuery(name = "Customer.findByName", query = "select o from Customer o where o.deleted = 0 and o.companyId = :companyId and lower(o.name) = lower(:name)"),
		@NamedQuery(name = "Customer.searchQuick", query = "select o from Customer o where o.deleted = 0 and o.companyId = :companyId and (lower(o.code) like :code or lower(o.name) like :name or lower(o.taxCode) like :taxCode) order by o.code"),
		@NamedQuery(name = "Customer.searchAdvance", query = "select o from Customer o where o.deleted = 0 and o.companyId = :companyId and lower(o.code) like :code and lower(o.name) like :name and lower(o.taxCode) like :taxCode and (:locationId = 0 or o.locationId = :locationId) and (:categoryId = 0 or o.categoryId = :categoryId) and (:groupId = 0 or o.groupId = :groupId) order by o.code"),
		@NamedQuery(name = "Customer.searchByCodeName", query = "select o from Customer o where o.deleted = 0 and o.companyId = :companyId and (lower(o.code) like :code or lower(o.name) like :name) order by o.code"),
		@NamedQuery(name = "Customer.searchByTaxCode", query = "select o from Customer o where o.deleted = 0 and o.companyId = :companyId and lower(o.taxCode) like :taxCode order by o.code") })
@Table(name = "customer")
public class Customer extends DaoEntity implements Serializable {
	private static final long serialVersionUID = -1789307491266487887L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "name_full")
	private String nameFull;

	@Column(name = "name_short")
	private String nameShort;

	@Column(name = "location_id")
	private Long locationId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Location location;

	@Column(name = "group_id")
	private Long groupId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id", referencedColumnName = "id", insertable = false, updatable = false)
	private CustomerGroup group;

	@Column(name = "category_id")
	private Long categoryId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
	private CustomerCategory category;

	@Column(name = "address_id")
	private Long addressId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Address address;

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

	@Column(name = "representative")
	private String representative;

	@Column(name = "representative_title")
	private String representativeTitle;

	@Column(name = "address_mail")
	private String addressMail;

	@Column(name = "contact_phone")
	private String contactPhone;

	@Column(name = "notes")
	private String notes;

	@Column(name = "status")
	private Integer status;

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

	@Transient
	private String addressFull;

	public Customer() {

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameFull() {
		return nameFull;
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
		return locationId == null ? -1L : locationId;
	}

	public void setLocationId(Long locationId) {
		if (locationId != null && locationId == 0) {
			locationId = null;
		}
		this.locationId = locationId;
	}

	public String getLocationName() {
		return location == null ? null : location.getName();
	}

	public void setLocationName(String locationName) {

	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Long getGroupId() {
		return groupId == null ? -1L : groupId;
	}

	public void setGroupId(Long groupId) {
		if (groupId != null && groupId == 0) {
			groupId = null;
		}
		this.groupId = groupId;
	}

	public String getGroupName() {
		return group == null ? null : group.getName();
	}

	public CustomerGroup getGroup() {
		return group;
	}

	public void setGroup(CustomerGroup group) {
		this.group = group;
	}

	public Long getCategoryId() {
		return categoryId == null ? -1L : categoryId;
	}

	public void setCategoryId(Long categoryId) {
		if (categoryId != null && categoryId == 0) {
			categoryId = null;
		}
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return category == null ? null : category.getName();
	}

	public CustomerCategory getCategory() {
		return category;
	}

	public void setCategory(CustomerCategory category) {
		this.category = category;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public String getRepresentativeTitle() {
		return representativeTitle;
	}

	public void setRepresentativeTitle(String representativeTitle) {
		this.representativeTitle = representativeTitle;
	}

	public String getAddressMail() {
		return addressMail;
	}

	public void setAddressMail(String addressMail) {
		this.addressMail = addressMail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	public String getAddressFull() {
		if (addressFull == null && address != null) {
			addressFull = address.getAddressFull();
		}
		return addressFull;
	}

	public void setAddressFull(String addressFull) {
		this.addressFull = addressFull;
	}

}
