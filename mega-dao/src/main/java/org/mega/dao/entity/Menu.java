/*
 * %W% %E%
 *
 * Copyright (c) 2014, AISOFT and/or its affiliates. All rights reserved.
 * AISOFT Business Management System PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.mega.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
		@NamedQuery(name = "Menu.findMainAll", query = "select o from Menu o where o.deleted = 0 and o.companyId = :companyId and o.main = 1 order by o.sort"),
		@NamedQuery(name = "Menu.findSubAll", query = "select o from Menu o where o.deleted = 0 and o.companyId = :companyId and o.main = 0 order by o.mainId, o.sort"),
		@NamedQuery(name = "Menu.findMainMenu", query = "select distinct o from Menu o inner join RoleMenu r on (o.id = r.menuId) inner join UserRole u on (r.roleId = u.roleId) where o.deleted = 0 and r.deleted = 0 and u.deleted = 0 and o.main = 1 and u.userId = :userId order by o.sort"),
		@NamedQuery(name = "Menu.findSubMenu", query = "select distinct o from Menu o inner join RoleMenu r on (o.id = r.menuId) inner join UserRole u on (r.roleId = u.roleId) where o.deleted = 0 and r.deleted = 0 and u.deleted = 0 and o.main = 0 and o.mainId = :mainId and u.userId = :userId order by o.sort"),
		@NamedQuery(name = "Menu.findMainMenuUser", query = "select distinct o from Menu o inner join UserMenu u on (o.id = u.menuId) where o.deleted = 0 and u.deleted = 0 and o.main = 1 and u.userId = :userId order by o.sort"),
		@NamedQuery(name = "Menu.findSubMenuUser", query = "select distinct o from Menu o inner join UserMenu u on (o.id = u.menuId) where o.deleted = 0 and u.deleted = 0 and o.main = 0 and o.mainId = :mainId and u.userId = :userId order by o.sort"),
		@NamedQuery(name = "Menu.findMainMenuPublic", query = "select distinct o from Menu o inner join RoleMenu r on (o.id = r.menuId) inner join UserRole u on (r.roleId = u.roleId) where o.deleted = 0 and r.deleted = 0 and u.deleted = 0 and o.main = 1 and r.menuPublic = 1 and u.userId = :userId order by o.sort"),
		@NamedQuery(name = "Menu.findSubMenuPublic", query = "select distinct o from Menu o inner join RoleMenu r on (o.id = r.menuId) inner join UserRole u on (r.roleId = u.roleId) where o.deleted = 0 and r.deleted = 0 and u.deleted = 0 and o.main = 0 and r.menuPublic = 1 and o.mainId = :mainId and u.userId = :userId order by o.sort"),
		@NamedQuery(name = "Menu.findMainMenuUserPublic", query = "select distinct o from Menu o inner join UserMenu u on (o.id = u.menuId) where o.deleted = 0 and u.deleted = 0 and o.main = 1 and u.menuPublic = 1 and u.userId = :userId order by o.sort"),
		@NamedQuery(name = "Menu.findSubMenuUserPublic", query = "select distinct o from Menu o inner join UserMenu u on (o.id = u.menuId) where o.deleted = 0 and u.deleted = 0 and o.main = 0 and u.menuPublic = 1 and o.mainId = :mainId and u.userId = :userId order by o.sort"),
		@NamedQuery(name = "Menu.findMainDefault", query = "select o from Menu o inner join RoleMenu r on (o.id = r.menuId) inner join UserRole u on (r.roleId = u.roleId) where o.deleted = 0 and r.deleted = 0 and u.deleted = 0 and o.main = 1 and r.menuDefault = 1 and u.userId = :userId order by r.sort, o.sort"),
		@NamedQuery(name = "Menu.findSubDefault", query = "select o from Menu o inner join RoleMenu r on (o.id = r.menuId) inner join UserRole u on (r.roleId = u.roleId) where o.deleted = 0 and r.deleted = 0 and u.deleted = 0 and o.main = 0 and r.menuDefault = 1 and o.mainId = :mainId and u.userId = :userId order by r.sort, o.sort"),
		@NamedQuery(name = "Menu.findMainUserDefault", query = "select o from Menu o inner join UserMenu u on (o.id = u.menuId) where o.deleted = 0 and u.deleted = 0 and o.main = 1 and u.menuDefault = 1 and u.userId = :userId order by o.sort"),
		@NamedQuery(name = "Menu.findSubUserDefault", query = "select o from Menu o inner join UserMenu u on (o.id = u.menuId) where o.deleted = 0 and u.deleted = 0 and o.main = 0 and u.menuDefault = 1 and o.mainId = :mainId and u.userId = :userId order by u.sort, o.sort")
})
@Table(name = "menu")
public class Menu extends DaoEntity implements Serializable {
	private static final long serialVersionUID = -5809675376510755263L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "page_id")
	private Long pageId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "page_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Page page;

	@Column(name = "menu_group")
	private Integer menuGroup = 0;

	@Column(name = "main")
	private Integer main = 0;

	@Column(name = "main_id")
	private Long mainId;

	@Transient
	private List<Menu> subMenu;

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

	public Menu() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getBusiness() {
		return page != null ? page.getBusiness() : "";
	}

	public void setBusiness(String business) {

	}

	public Integer getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(Integer menuGroup) {
		this.menuGroup = menuGroup;
	}

	public void setMain(Integer main) {
		this.main = main;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public boolean isMenuGroup() {
		return menuGroup > 0;
	}

	public void setMenuGroup(boolean menuGroup) {
		this.menuGroup = menuGroup ? 1 : 0;
	}

	public boolean getMain() {
		return main > 0;
	}

	public void setMain(boolean main) {
		this.main = main ? 1 : 0;
	}

	public List<Menu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
