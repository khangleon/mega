/*
 * %W% %E%
 *
 * Copyright (c) 2014, AISOFT and/or its affiliates. All rights reserved.
 * AISOFT Business Management System PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.mega.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_seq")
public class MSeq implements Serializable {
	private static final long serialVersionUID = 1007097230898300492L;

	@Id
	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "value")
	private Long value;

	@Column(name = "name_logic")
	private String nameLogic;

	@Column(name = "seq_date")
	private Integer seqDate;

	public MSeq() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getValue() {
		return this.value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getNameLogic() {
		return nameLogic;
	}

	public void setNameLogic(String nameLogic) {
		this.nameLogic = nameLogic;
	}

	public Integer getSeqDate() {
		return this.seqDate;
	}

	public void setSeqDate(Integer seqDate) {
		this.seqDate = seqDate;
	}
}
