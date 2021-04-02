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
		@NamedQuery(name = "Page.findByCode", query = "select o from Page o where o.code = :code and o.deleted = 0"),
		@NamedQuery(name = "Page.findByRole", query = "select distinct o from Page o inner join Menu m on (o.id = m.pageId) inner join RoleMenu r on (m.id = r.menuId) where o.deleted = 0 and m.deleted = 0 and r.deleted = 0 and o.permission = 1 and m.main = 0 and m.pageId > 0 and r.roleId in :roleList order by o.business "),
		@NamedQuery(name = "Page.findByUser", query = "select distinct o from Page o inner join Menu m on (o.id = m.pageId) inner join RoleMenu r on (m.id = r.menuId) inner join UserRole u on (r.roleId = u.roleId) where o.deleted = 0 and m.deleted = 0 and r.deleted = 0 and u.deleted = 0 and o.permission = 1 and m.main = 0 and m.pageId > 0 and u.userId = :userId order by o.business ") })
@Table(name = "page")
public class Page extends DaoEntity implements Serializable {
	private static final long serialVersionUID = 1414919854664L;
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "business")
	private String business;

	@Column(name = "name")
	private String name;

	@Column(name = "title")
	private String title;

	@Column(name = "layout")
	private String layout;

	@Column(name = "url")
	private String url;

	@Column(name = "page_group")
	private String pageGroup;

	@Column(name = "permission")
	private Integer permission;

	@Column(name = "approve_detail")
	private Integer approveDetail;

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

	public Page() {
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

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPageGroup() {
		return pageGroup;
	}

	public void setPageGroup(String pageGroup) {
		this.pageGroup = pageGroup;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public boolean isApproveDetail() {
		return approveDetail > 0;
	}

	public void setApproveDetail(boolean approveDetail) {
		this.approveDetail = approveDetail ? 1 : 0;
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
