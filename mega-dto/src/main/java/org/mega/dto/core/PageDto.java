package org.mega.dto.core;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import org.mega.dao.core.DaoEntity;
import org.mega.dao.entity.Menu;

public class PageDto implements Serializable {
	private static final long serialVersionUID = -5674264378476334874L;
	private String pageId;
	private String layout;
	private String pageUrl;
	private String copyRight;
	private List<Menu> mainMenu;
	private Long mainSelected;
	private List<Menu> subMenu;
	private Long menuSelected;

	List<DaoEntity> dataList;

	private Integer unReadMessage = 0;
	private Integer remindEvent = 0;
	private Integer remindTask = 0;
	private Integer waitingApproval = 0;

	private List<MessageUnread> unReadMessageList;
	private List<MessageUnread> remindEventList;
	private List<MessageUnread> remindTaskList;
	private List<MessageUnread> waitingApprovalList;
	private int firstDayOfWeek = Calendar.MONDAY;

	public PageDto() {

	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		if (pageUrl == null || pageUrl.trim().isEmpty()) {
			pageUrl = "/empty.xhtml";
		}
		this.pageUrl = pageUrl;
	}

	public List<Menu> getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(List<Menu> mainMenu) {
		this.mainMenu = mainMenu;
	}

	public Long getMainSelected() {
		return mainSelected;
	}

	public void setMainSelected(Long mainSelected) {
		this.mainSelected = mainSelected;
	}

	public List<Menu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}

	public Long getMenuSelected() {
		return menuSelected;
	}

	public void setMenuSelected(Long menuSelected) {
		this.menuSelected = menuSelected;
	}

	public String getCopyRight() {
		return copyRight;
	}

	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}

	public List<DaoEntity> getDataList() {
		return dataList;
	}

	public void setDataList(List<DaoEntity> dataList) {
		if (dataList != null) {
			int len = dataList.size();
			DaoEntity e = null;
			for (int i = 0; i < len; i++) {
				e = dataList.get(i);
				e.setIndex(i);
			}
		}
		this.dataList = dataList;
	}

	public Integer getUnReadMessage() {
		return unReadMessage;
	}

	public void setUnReadMessage(Integer unReadMessage) {
		this.unReadMessage = unReadMessage;
	}

	public Integer getRemindEvent() {
		return remindEvent;
	}

	public void setRemindEvent(Integer remindEvent) {
		this.remindEvent = remindEvent;
	}

	public Integer getRemindTask() {
		return remindTask;
	}

	public void setRemindTask(Integer remindTask) {
		this.remindTask = remindTask;
	}

	public Integer getWaitingApproval() {
		return waitingApproval;
	}

	public void setWaitingApproval(Integer waitingApproval) {
		this.waitingApproval = waitingApproval;
	}

	public List<MessageUnread> getUnReadMessageList() {
		return unReadMessageList;
	}

	public void setUnReadMessageList(List<MessageUnread> unReadMessageList) {
		this.unReadMessageList = unReadMessageList;
	}

	public List<MessageUnread> getRemindEventList() {
		return remindEventList;
	}

	public void setRemindEventList(List<MessageUnread> remindEventList) {
		this.remindEventList = remindEventList;
	}

	public List<MessageUnread> getRemindTaskList() {
		return remindTaskList;
	}

	public void setRemindTaskList(List<MessageUnread> remindTaskList) {
		this.remindTaskList = remindTaskList;
	}

	public List<MessageUnread> getWaitingApprovalList() {
		return waitingApprovalList;
	}

	public void setWaitingApprovalList(List<MessageUnread> waitingApprovalList) {
		this.waitingApprovalList = waitingApprovalList;
	}

	public int getFirstDayOfWeek() {
		return firstDayOfWeek;
	}

	public void setFirstDayOfWeek(int firstDayOfWeek) {
		this.firstDayOfWeek = firstDayOfWeek;
	}

}
