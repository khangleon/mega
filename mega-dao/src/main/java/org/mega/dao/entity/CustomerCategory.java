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
@NamedQueries({ @NamedQuery(name = "CustomerCategory.findByGroupId", query = "select o from CustomerCategory o where o.deleted = 0 and o.companyId = :companyId and o.groupId = :groupId order by o.sort") })
@Table(name = "customer_category")
public class CustomerCategory extends DaoEntity implements Serializable {
	private static final long serialVersionUID = 2211780118188209482L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "group_id")
	private Long groupId;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "name_en")
	private String nameEn;

	@Column(name = "sort")
	private Integer sort;

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
	private Integer version;

	public CustomerCategory() {
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
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

}
