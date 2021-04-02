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
		@NamedQuery(name = "RoleBusinessControl.findByRole", query = "select o from RoleBusinessControl o inner join BusinessControl c on (o.businessControlId = c.id) where o.deleted = 0 and c.deleted = 0 and o.roleId = :roleId order by o.business, c.sort"),
		@NamedQuery(name = "RoleBusinessControl.findByUser", query = "select o from RoleBusinessControl o inner join BusinessControl c on (o.businessControlId = c.id) inner join UserRole r on (o.roleId = r.roleId) where o.deleted = 0 and c.deleted = 0 and r.deleted = 0 and r.userId = :userId order by o.business, r.roleId") })
@Table(name = "role_business_control")
public class RoleBusinessControl extends DaoEntity implements Serializable {
	private static final long serialVersionUID = -7563315818688864121L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "business")
	private String business;

	@Column(name = "business_control_id")
	private Long businessControlId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "business_control_id", referencedColumnName = "id", insertable = false, updatable = false)
	private BusinessControl businessControl;

	@Column(name = "rendered")
	private Integer rendered = 0;

	@Column(name = "disabled")
	private Integer disabled = 0;

	@Column(name = "hidden")
	private Integer hidden = 0;

	@Column(name = "readonly")
	private Integer readonly = 0;

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

	public RoleBusinessControl() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getBusiness() {
		return this.business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public Long getBusinessControlId() {
		return businessControlId;
	}

	public void setBusinessControlId(Long businessControlId) {
		this.businessControlId = businessControlId;
	}

	public BusinessControl getBusinessControl() {
		return businessControl;
	}

	public void setBusinessControl(BusinessControl businessControl) {
		this.businessControl = businessControl;
	}

	public String getControlId() {
		return businessControl != null ? businessControl.getControlId() : null;
	}

	public void setControlId(String controlId) {

	}

	public String getControlName() {
		return businessControl != null ? businessControl.getControlName() : null;
	}

	public void setControlName(String controlName) {
	}

	public String getControlType() {
		return businessControl != null ? businessControl.getControlType() : null;
	}

	public void setControlType(String controlType) {
	}

	public String getActionName() {
		return businessControl != null ? businessControl.getActionName() : null;
	}

	public void setActionName(String actionName) {
	}

	public boolean isRendered() {
		return rendered > 0;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered ? 1 : 0;
	}

	public boolean isDisabled() {
		return disabled > 0;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled ? 1 : 0;
	}

	public boolean isHidden() {
		return hidden > 0;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden ? 1 : 0;
	}

	public boolean isReadonly() {
		return readonly > 0;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly ? 1 : 0;
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
