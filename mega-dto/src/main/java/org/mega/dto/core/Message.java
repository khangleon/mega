package org.mega.dto.core;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = -3175319900739705675L;
	
	private String id;
	private String label;
	private String message;
	private int level;
	private String styleClass;

	public Message() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}
	
	public boolean isShowLabel() {
		return label != null && !label.trim().isEmpty();
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	
}
