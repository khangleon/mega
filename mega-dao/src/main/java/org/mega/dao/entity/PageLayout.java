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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.mega.dao.core.DaoEntity;

@Entity
@Table(name = "page_layout")
public class PageLayout extends DaoEntity implements Serializable {
	private static final long serialVersionUID = 1414919854758L;
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "page_id")
	private String pageId;

	@Column(name = "name")
	private String name;

	@Column(name = "url_layout")
	private String urlLayout;

	@Column(name = "url_navigation")
	private String urlNavigation;

	@Column(name = "url_content_body")
	private String urlContentBody;

	@Column(name = "url_content_side")
	private String urlContentSide;

	@Column(name = "url_content_side_left")
	private String urlContentSideLeft;

	@Column(name = "url_content_side_right")
	private String urlContentSideRight;

	@Column(name = "url_content_top")
	private String urlContentTop;

	@Column(name = "url_content_bottom")
	private String urlContentBottom;

	@Column(name = "url_action_bar")
	private String urlActionBar;

	@Column(name = "main_layout")
	private Integer mainLayout;

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
	private Integer version = 1;

	public PageLayout() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPageId() {
		return this.pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlLayout() {
		return this.urlLayout;
	}

	public void setUrlLayout(String urlLayout) {
		this.urlLayout = urlLayout;
	}

	public String getUrlNavigation() {
		return this.urlNavigation;
	}

	public void setUrlNavigation(String urlNavigation) {
		this.urlNavigation = urlNavigation;
	}

	public String getUrlContentBody() {
		return this.urlContentBody;
	}

	public void setUrlContentBody(String urlContentBody) {
		this.urlContentBody = urlContentBody;
	}

	public String getUrlContentSide() {
		return this.urlContentSide;
	}

	public void setUrlContentSide(String urlContentSide) {
		this.urlContentSide = urlContentSide;
	}

	public String getUrlContentSideLeft() {
		return this.urlContentSideLeft;
	}

	public void setUrlContentSideLeft(String urlContentSideLeft) {
		this.urlContentSideLeft = urlContentSideLeft;
	}

	public String getUrlContentSideRight() {
		return this.urlContentSideRight;
	}

	public void setUrlContentSideRight(String urlContentSideRight) {
		this.urlContentSideRight = urlContentSideRight;
	}

	public String getUrlContentTop() {
		return this.urlContentTop;
	}

	public void setUrlContentTop(String urlContentTop) {
		this.urlContentTop = urlContentTop;
	}

	public String getUrlContentBottom() {
		return this.urlContentBottom;
	}

	public void setUrlContentBottom(String urlContentBottom) {
		this.urlContentBottom = urlContentBottom;
	}

	public String getUrlActionBar() {
		return this.urlActionBar;
	}

	public void setUrlActionBar(String urlActionBar) {
		this.urlActionBar = urlActionBar;
	}

	public Integer getMainLayout() {
		return this.mainLayout;
	}

	public void setMainLayout(Integer mainLayout) {
		this.mainLayout = mainLayout;
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
