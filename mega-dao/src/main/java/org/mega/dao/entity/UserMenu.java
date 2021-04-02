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
		@NamedQuery(name = "UserMenu.findMainAll", query = "select o from UserMenu o inner join Menu m on (o.menuId = m.id) where o.deleted = 0 and m.deleted = 0 and m.main = 1 and o.userId = :userId order by o.sort, m.sort"),
		@NamedQuery(name = "UserMenu.findSubAll", query = "select o from UserMenu o inner join Menu m on (o.menuId = m.id) where o.deleted = 0 and m.deleted = 0 and m.main = 0 and o.userId = :userId and m.mainId = :mainId order by o.sort, m.sort") })
@Table(name = "user_menu")
public class UserMenu extends DaoEntity implements Serializable {
	private static final long serialVersionUID = -1841891557499356685L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Transient
	private String userName;

	@Column(name = "menu_id")
	private Long menuId;

	@Transient
	private String menuName;

	@Transient
	private Long mainMenuId;
	@Transient
	private String mainMenuName;

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
	private List<UserMenu> subMenu;

	@Transient
	private List<RoleBusinessControl> roleBusinessControl;

	public UserMenu() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Long getMainMenuId() {
		return mainMenuId;
	}

	public void setMainMenuId(Long mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	public String getMainMenuName() {
		return mainMenuName;
	}

	public void setMainMenuName(String mainMenuName) {
		this.mainMenuName = mainMenuName;
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

	public List<UserMenu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<UserMenu> subMenu) {
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
