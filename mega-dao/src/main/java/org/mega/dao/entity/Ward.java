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
		@NamedQuery(name = "Ward.findAll", query = "select o from Ward o where o.deleted = 0 and o.companyId = :companyId order by o.code"),
		@NamedQuery(name = "Ward.searchQuick", query = "select o from Ward o inner join District d on (o.districtId = d.id) inner join Province p on (d.provinceId = p.id) where o.deleted = 0 and d.deleted = 0 and p.deleted = 0 and o.companyId = :companyId and (lower(o.code) like :code or lower(o.name) like :name) order by p.name"),
		@NamedQuery(name = "Ward.searchAdvance", query = "select o from Ward o inner join District d on (o.districtId = d.id) inner join Province p on (d.provinceId = p.id) where o.deleted = 0 and d.deleted = 0 and p.deleted = 0 and o.companyId = :companyId and (lower(o.code) like :wardCode and lower(o.name) like :wardName) and (lower(d.code) like :districtCode and lower(d.name) like :districtName) and (lower(p.code) like :provinceCode and lower(p.name) like :provinceName) order by p.name"),
		@NamedQuery(name = "Ward.findDistrict", query = "select o from Ward o where o.deleted = 0 and o.companyId = :companyId and o.districtId = :districtId order by o.code"),
		@NamedQuery(name = "Ward.searchByCodeName", query = "select o from Ward o where o.deleted = 0 and o.companyId = :companyId and (:districtId < 0 or o.districtId = :districtId) and (lower(o.code) like :code or lower(o.name) like :name) order by o.code ") })
@Table(name = "ward")
public class Ward extends DaoEntity implements Serializable {
	private static final long serialVersionUID = -2294437635760142571L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "district_id")
	private Long districtId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "district_id", referencedColumnName = "id", insertable = false, updatable = false)
	private District district;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

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

	public Ward() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
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
		return this.name + (this.district != null ? ", " + this.district.getNameFull() : "");
	}

	public String getDistrictCode() {
		return (this.district != null ? this.district.getCode() : "");
	}

	public String getDistrictName() {
		return (this.district != null ? this.district.getName() : "");
	}

	public String getProvinceCode() {
		return (this.district != null ? this.district.getProvinceCode() : "");
	}

	public Long getProvinceId() {
		return (this.district != null ? this.district.getProvinceId() : null);
	}

	public String getProvinceName() {
		return (this.district != null ? this.district.getProvinceName() : "");
	}

	public String getZipCode() {
		return (this.district != null ? this.district.getZipCode() : "");
	}

	public Long getCountryId() {
		return (this.district != null ? this.district.getCountryId() : null);
	}

	public String getCountryName() {
		return (this.district != null ? this.district.getCountryName() : "");
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
