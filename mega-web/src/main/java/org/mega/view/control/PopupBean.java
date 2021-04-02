package org.mega.view.control;

import java.io.Serializable;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.mega.view.util.JSFUtils;

@ManagedBean(name = "popupBean")
@ViewScoped
public class PopupBean implements Serializable {
	private static final long serialVersionUID = 8426006816804538889L;
	
	public static final String TITLE = "popup_title";
	public static final String WINDOW_NAME = "popup_windowName";
	public static final String PAGE_URL = "popup_pageUrl";
	public static final String HANDLE_RETURN = "popup_handleReturn";
	
	private String title;
	private String pageUrl;
	private String windowName;
	private String handleReturn;

	public PopupBean() {
		super();
		try {
			initPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initPage() throws Exception {
		this.title = getParamPage(TITLE);
		this.windowName = getParamPage(WINDOW_NAME);
		this.pageUrl = getParamPage(PAGE_URL);
		this.handleReturn = getParamPage(HANDLE_RETURN);
	}
	
	/**
	 * Get parameter of page
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getParamPage(String key) {
		HashMap<String, Object> paramPage = JSFUtils.findBean("paramPage");
		T value = (T) paramPage.remove(key);
		return value;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWindowName() {
		return windowName;
	}

	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getHandleReturn() {
		return handleReturn;
	}

	public void setHandleReturn(String handleReturn) {
		this.handleReturn = handleReturn;
	}

}
