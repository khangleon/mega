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
		@NamedQuery(name = "Brand.findAll", query = "select o from Brand o where o.deleted = 0 and o.companyId = :companyId order by o.code"),
		@NamedQuery(name = "Brand.findByCode", query = "select o from Brand o where o.deleted = 0 and o.companyId = :companyId and o.code = :code"),
		@NamedQuery(name = "Brand.searchQuick", query = "select o from Brand o where o.deleted = 0 and o.companyId = :companyId and (lower(o.code) like :code or lower(o.name) like :name) order by o.sort"),
		@NamedQuery(name = "Brand.searchAdvance", query = "select o from Brand o where o.deleted = 0 and o.companyId = :companyId and lower(o.code) like :code and lower(o.name) like :name and lower(o.factoryCountry) like :factoryCountry order by o.sort"),
		@NamedQuery(name = "Brand.searchByCodeName", query = "select o from Brand o where o.deleted = 0 and o.companyId = :companyId and (lower(o.code) like :code or lower(o.name) like :name) order by o.code ") })
@Table(name = "brand")
public class Brand extends DaoEntity implements Serializable {
	private static final long serialVersionUID = 1414919854258L;
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "factory_name")
	private String factoryName;

	@Column(name = "factory_address")
	private String factoryAddress;

	@Column(name = "factory_country")
	private String factoryCountry;

	@Column(name = "active")
	private Integer active = 1;

	@Column(name = "sort")
	private Integer sort;

	@Column(name = "company_id")
	private Long companyId;

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

	public Brand() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFactoryAddress() {
		return factoryAddress;
	}

	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}

	public String getFactoryCountry() {
		return factoryCountry;
	}

	public void setFactoryCountry(String factoryCountry) {
		this.factoryCountry = factoryCountry;
	}

	public boolean isActive() {
		return active > 0;
	}

	public void setActive(boolean active) {
		this.active = active ? 1 : 0;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
	
	public boolean isEmpty() {
		boolean empty = false;
		if ((name == null || name.trim().isEmpty()) && (code == null || code.trim().isEmpty())) {
			empty = true;
		}
		return empty;
	}

}
