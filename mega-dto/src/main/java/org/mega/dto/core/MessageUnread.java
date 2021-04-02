package org.mega.dto.core;

import java.io.Serializable;
import java.util.Date;

public class MessageUnread implements Serializable {
	private static final long serialVersionUID = -300207837149877469L;

	private String url;
	private String picName;
	private String value;
	private String content;
	private String detail;
	private Date date;

	public MessageUnread() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
