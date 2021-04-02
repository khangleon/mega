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
		@NamedQuery(name = "RoleMenu.findMainAll", query = "select o from RoleMenu o inner join Menu m on (o.menuId = m.id) where o.deleted = 0 and m.deleted = 0 and m.main = 1 and o.roleId = :roleId order by o.sort, m.sort"),
		@NamedQuery(name = "RoleMenu.findSubAll", query = "select o from RoleMenu o inner join Menu m on (o.menuId = m.id) where o.deleted = 0 and m.deleted = 0 and m.main = 0 and o.roleId = :roleId and m.mainId = :mainId order by o.sort, m.sort"),
		@NamedQuery(name = "RoleMenu.findMainUser", query = "select distinct o from RoleMenu o inner join Menu m on (o.menuId = m.id) inner join UserRole u on (o.roleId = u.roleId) where o.deleted = 0 and m.deleted = 0 and m.main = 1 and u.userId = :userId order by o.sort, m.sort"),
		@NamedQuery(name = "RoleMenu.findSubUser", query = "select distinct o from RoleMenu o inner join Menu m on (o.menuId = m.id) inner join UserRole u on (o.roleId = u.roleId) where o.deleted = 0 and m.deleted = 0 and m.main = 0 and u.userId = :userId and m.mainId = :mainId order by o.sort, m.sort")})
@Table(name = "role_menu")
public class RoleMenu extends DaoEntity implements Serializable {
	private static final long serialVersionUID = -6430715486012900121L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "role_id")
	private Long roleId;

	@Transient
	private String roleName;

	@Column(name = "menu_id")
	private Long menuId;

	@Transient
	private String menuName;

	@Transient
	private String business;

	@Column(name = "level_data")
	private Integer levelData = 0;

	@Column(name = "sort")
	private Integer sort = 0;

	@Column(name = "menu_default")
	private Integer menuDefault = 0;

	@Column(name = "menu_public")
	public Integer menuPublic = 0;

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

	@Transient
	private List<RoleMenu> subMenu;

	@Transient
	private List<RoleBusinessControl> roleBusinessControl;

	public RoleMenu() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public boolean isMenuDefault() {
		return menuDefault > 0;
	}

	public void setMenuDefault(boolean menuDefault) {
		this.menuDefault = menuDefault ? 1 : 0;
	}

	public boolean isMenuPublic() {
		return menuPublic > 0;
	}

	public void setMenuPublic(boolean menuPublic) {
		this.menuPublic = menuPublic ? 1 : 0;
	}

	public Integer getLevelData() {
		return levelData;
	}

	public void setLevelData(Integer levelData) {
		this.levelData = levelData;
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

	public List<RoleMenu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<RoleMenu> subMenu) {
		this.subMenu = subMenu;
	}

	public List<RoleBusinessControl> getRoleBusinessControl() {
		return roleBusinessControl;
	}

	public void setRoleBusinessControl(List<RoleBusinessControl> roleBusinessControl) {
		this.roleBusinessControl = roleBusinessControl;
	}

	@Transient
	private boolean disableControl = false;

	public boolean isDisableControl() {
		return disableControl;
	}

	public void setDisableControl(boolean disableControl) {
		this.disableControl = disableControl;
	}

}
