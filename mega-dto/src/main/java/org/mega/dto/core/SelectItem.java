package org.mega.dto.core;

import java.io.Serializable;

public class SelectItem implements Serializable {
	private static final long serialVersionUID = 2984804678901305980L;
	private String description = null;
	private boolean disabled = false;
	private String label = null;
	@SuppressWarnings({ "NonSerializableFieldInSerializableClass" })
	private Object value = null;
	@SuppressWarnings({ "NonSerializableFieldInSerializableClass" })
	private Object valueOther = null;

	public SelectItem() {
		this(null, null, null, false);
	}

	public SelectItem(Object value) {
		this(value, value == null ? null : value.toString(), null, false);
	}

	public SelectItem(Object value, String label) {
		this(value, label, null, false);
	}

	public SelectItem(Object value, String label, String description) {
		this(value, label, description, false);
	}

	public SelectItem(Object value, String label, String description, boolean disabled) {
		this.value = value;
		this.label = label;
		this.description = description;
		this.disabled = disabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Object getValue() {
		return value;
	}

	public Integer getIntValue() {
		return value == null ? null : Integer.valueOf(value.toString());
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValueOther() {
		return valueOther;
	}

	public void setValueOther(Object valueOther) {
		this.valueOther = valueOther;
	}

}
