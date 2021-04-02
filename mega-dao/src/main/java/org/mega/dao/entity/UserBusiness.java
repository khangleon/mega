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
@NamedQueries({ @NamedQuery(name = "UserBusiness.findByUser", query = "select o from UserBusiness o where o.deleted = 0 and o.userId = :userId order by o.business"),
		@NamedQuery(name = "UserBusiness.findByBusiness", query = "select o from UserBusiness o where o.deleted = 0 and o.business = :business order by o.userId"),
		@NamedQuery(name = "UserBusiness.findByUserBusiness", query = "select o from UserBusiness o where o.deleted = 0 and o.userId = :userId and o.business = :business ") })
@Table(name = "user_business")
public class UserBusiness extends DaoEntity implements Serializable {
	private static final long serialVersionUID = -7563315818688864121L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "business")
	private String business;

	@Column(name = "business_detail")
	private String businessDetail;

	@Transient
	private String businessName;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "level_data")
	private Integer levelData = 0;

	@Column(name = "level_approve1")
	private Integer levelApprove1 = 0;

	@Column(name = "level_approve2")
	private Integer levelApprove2 = 0;

	@Column(name = "level_approve3")
	private Integer levelApprove3 = 0;

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

	public UserBusiness() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusiness() {
		return this.business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getBusinessDetail() {
		return businessDetail;
	}

	public void setBusinessDetail(String businessDetail) {
		this.businessDetail = businessDetail;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getLevelData() {
		return levelData;
	}

	public void setLevelData(Integer levelData) {
		this.levelData = levelData;
	}

	public boolean isLevelApprove1() {
		return this.levelApprove1 > 0;
	}

	public void setLevelApprove1(boolean level1) {
		this.levelApprove1 = level1 ? 1 : 0;
	}

	public boolean isLevelApprove2() {
		return this.levelApprove2 > 0;
	}

	public void setLevelApprove2(boolean level2) {
		this.levelApprove2 = level2 ? 1 : 0;
	}

	public boolean isLevelApprove3() {
		return this.levelApprove3 > 0;
	}

	public void setLevelApprove3(boolean level3) {
		this.levelApprove3 = level3 ? 1 : 0;
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
