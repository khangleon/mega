package org.mega.dto.wrk;

import java.io.Serializable;

public class WRK012Dto extends WRK011Dto implements Serializable {
private static final long serialVersionUID = 4743250805221610790L;
	
	private String locationJSON = EMPTY_JSON;

	public WRK012Dto() {
		super();
	}

	public String getLocationJSON() {
		return locationJSON;
	}

	public void setLocationJSON(String locationJSON) {
		this.locationJSON = locationJSON;
	}

}
