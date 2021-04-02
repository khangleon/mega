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
		@NamedQuery(name = "Currency.findAll", query = "select o from Currency o where o.deleted = 0 and o.companyId = :companyId order by o.sort"),
		@NamedQuery(name = "Currency.findByCode", query = "select o from Currency o where o.deleted = 0 and o.companyId = :companyId and o.code = :code"),
		@NamedQuery(name = "Currency.searchByCodeName", query = "select o from Currency o where o.deleted = 0 and o.companyId = :companyId and (lower(o.code) like :code or lower(o.name) like :name)") })
@Table(name = "currency")
public class Currency extends DaoEntity implements Serializable {
	private static final long serialVersionUID = 9218153150607073681L;

	private static final String PATTERN_ZERO = "000";
	private static final String PATTERN_FORMAT = "#,##0";
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "code")
	private String code;

	@Column(name = "code_en")
	private String codeEn;

	@Column(name = "code_num")
	private String codeNum;

	@Column(name = "name")
	private String name;

	@Column(name = "name_major")
	private String nameMajor;

	@Column(name = "name_minor")
	private String nameMinor;

	@Column(name = "name_en")
	private String nameEn;

	@Column(name = "name_major_en")
	private String nameMajorEn;

	@Column(name = "name_minor_en")
	private String nameMinorEn;

	@Column(name = "base_currency")
	private Integer baseCurrency = 0;

	@Column(name = "exchange_rate")
	private Double exchangeRate;

	@Column(name = "default_currency")
	private Integer defaultCurrency = 0;

	@Column(name = "display_decimal")
	private Integer displayDecimal = 0;

	@Column(name = "notes")
	private String notes;

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

	public Currency() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeEn() {
		return codeEn;
	}

	public void setCodeEn(String codeEn) {
		this.codeEn = codeEn;
	}

	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameMajor() {
		return nameMajor;
	}

	public void setNameMajor(String nameMajor) {
		this.nameMajor = nameMajor;
	}

	public String getNameMinor() {
		return nameMinor;
	}

	public void setNameMinor(String nameMinor) {
		this.nameMinor = nameMinor;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameMajorEn() {
		return nameMajorEn;
	}

	public void setNameMajorEn(String nameMajorEn) {
		this.nameMajorEn = nameMajorEn;
	}

	public String getNameMinorEn() {
		return nameMinorEn;
	}

	public void setNameMinorEn(String nameMinorEn) {
		this.nameMinorEn = nameMinorEn;
	}

	public boolean isBaseCurrency() {
		return this.baseCurrency > 0;
	}

	public void setBaseCurrency(boolean baseCurrency) {
		this.baseCurrency = baseCurrency ? 1 : 0;
	}

	public Double getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public boolean isDefaultCurrency() {
		return defaultCurrency > 0;
	}

	public void setDefaultCurrency(boolean defaultCurrency) {
		this.defaultCurrency = defaultCurrency ? 1 : 0;
	}

	public Integer getDisplayDecimal() {
		return displayDecimal;
	}

	public void setDisplayDecimal(Integer displayDecimal) {
		this.displayDecimal = displayDecimal;
	}

	public String getPattern() {
		String pattern = PATTERN_FORMAT;
		if (this.displayDecimal > 0) {
			pattern += "." + PATTERN_ZERO.substring(0, this.displayDecimal);
		}
		return pattern;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
		boolean ret = false;
		if ((name == null || name.trim().length() == 0)) {
			ret = true;
		}
		return ret;
	}

}
