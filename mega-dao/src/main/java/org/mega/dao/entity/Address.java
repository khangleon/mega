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
import javax.persistence.Id;
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
		@NamedQuery(name = "Address.findAll", query = "select o from Address o where o.deleted = 0 order by o.address"),
		@NamedQuery(name = "Address.searchQuick", query = "select o from Address o where o.deleted = 0 and lower(o.address) like :address order by o.address"),
		@NamedQuery(name = "Address.searchAdvance", query = "select o from Address o where o.deleted = 0 and lower(o.address) like :address and lower(o.ward) like :ward and lower(o.district) like :district and lower(o.province) like :province and lower(o.country) like :country order by o.address"),
		@NamedQuery(name = "Address.searchByCodeName", query = "select o from Address o where o.deleted = 0 order by o.address") })
@Table(name = "address")
public class Address extends DaoEntity implements Serializable {
	private static final long serialVersionUID = 1421402140156L;
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "ward_id")
	private Long wardId;

	@Column(name = "district_id")
	private Long districtId;

	@Column(name = "province_id")
	private Long provinceId;

	@Column(name = "country_id")
	private Long countryId;

	@Column(name = "address")
	private String address;

	@Column(name = "ward")
	private String ward;

	@Column(name = "district")
	private String district;

	@Column(name = "province")
	private String province;

	@Column(name = "country")
	private String country;

	@Column(name = "zip_code")
	private String zipCode;

	@Column(name = "post_code")
	private String postCode;

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

	public Address() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWardId() {
		return this.wardId;
	}

	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getWardName() {
		return null;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressFull() {
		StringBuffer bufAddress = new StringBuffer(this.address);
		if (this.ward != null && !this.ward.isEmpty()) {
			bufAddress.append(", ");
			bufAddress.append(this.ward);
		}

		if (this.district != null && !this.district.isEmpty()) {
			bufAddress.append(", ");
			bufAddress.append(this.district);
		}

		if (this.province != null && !this.province.isEmpty()) {
			bufAddress.append(", ");
			bufAddress.append(this.province);
		}

		// if (this.nation != null && !this.nation.isEmpty()) {
		// bufAddress.append(", ");
		// bufAddress.append(this.nation);
		// }

		return bufAddress.toString();
	}

	public String getWard() {
		return this.ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
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
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Transient
	private Boolean selected = false;

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
