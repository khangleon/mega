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
		@NamedQuery(name = "Department.findAll", query = "select o from Department o where o.deleted = 0 order by o.sort"),
		@NamedQuery(name = "Department.findByBranchId", query = "select o from Department o where o.deleted = 0 and (:branchId = 0 or o.branchId = :branchId) order by o.sort"),
		@NamedQuery(name = "Department.findByCodeName", query = "select o from Department o where o.deleted = 0 and (o.branchId = :branchId) and (o.code like :code or o.name like :name) order by o.sort"),
		@NamedQuery(name = "Department.searchQuick", query = "select o from Department o where o.deleted = 0 and (:branchId = 0 or o.branchId = :branchId) and o.status = :status and (lower(o.code) like :code or lower(o.name) like :name) order by o.sort"),
		@NamedQuery(name = "Department.searchAdvance", query = "select o from Department o where o.deleted = 0 and o.branchId = :branchId and o.status = :status and lower(o.code) like :code and lower(o.name) like :name order by o.sort") })
@Table(name = "department")
public class Department extends DaoEntity implements Serializable {
	private static final long serialVersionUID = 2792848940182023468L;

	public static final Integer STATUS_ACTIVE = 1;
	public static final Integer STATUS_INACTIVE = 0;
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "branch_id")
	private Long branchId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "branch_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Branch branch;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "name_full")
	private String nameFull;

	@Column(name = "name_short")
	private String nameShort;

	@Column(name = "representative")
	private String representative;

	@Column(name = "representative_title")
	private String representativeTitle;

	@Column(name = "status")
	private Integer status = STATUS_ACTIVE;

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
	private Integer version = 0;

	public Department() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setBranchName(String branchId) {

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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
