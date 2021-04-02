package org.mega.dto.core;

import java.util.Date;
import java.util.List;

import org.mega.dao.entity.Menu;

public class LoginDto extends Dto {
	private static final long serialVersionUID = 7871443350734870089L;

	private String account;
	private String password;
	private String newPassword;
	private String retypePassword;
	private String capcha;
	private String capchaPlaceHolder;
	private String capchaRandom;
	private int capchaIndex;
	private boolean loginOk = false;
	private LoginInfo loginInfo;

	private List<Menu> mainMenu;
	private Menu mainDefault;
	private Menu subDefault;

	private Date loginFailedDate = new Date();
	private int loginFailedTimes = 0;

	private boolean changePassword = false;

	private String sessionId;
	private String remoteAddr;
	private String remoteHost;
	private Integer remotePort;
	private String localName;
	private Integer localPort;
	private Integer serverPort;
	private String serverName;
	private String serverPath;
	private boolean logout = true;

	public LoginDto() {
		super();
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public String getCapcha() {
		return capcha;
	}

	public void setCapcha(String capcha) {
		this.capcha = capcha;
	}

	public String getCapchaPlaceHolder() {
		return capchaPlaceHolder;
	}

	public void setCapchaPlaceHolder(String capchaPlaceHolder) {
		this.capchaPlaceHolder = capchaPlaceHolder;
	}

	public String getCapchaRandom() {
		return capchaRandom;
	}

	public void setCapchaRandom(String capchaRandom) {
		this.capchaRandom = capchaRandom;
	}

	public int getCapchaIndex() {
		return capchaIndex;
	}

	public void setCapchaIndex(int capchaIndex) {
		this.capchaIndex = capchaIndex;
	}

	public boolean isLoginOk() {
		return loginOk;
	}

	public void setLoginOk(boolean loginOk) {
		this.loginOk = loginOk;
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public List<Menu> getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(List<Menu> mainMenu) {
		this.mainMenu = mainMenu;
	}

	public Menu getMainDefault() {
		return mainDefault;
	}

	public void setMainDefault(Menu mainDefault) {
		this.mainDefault = mainDefault;
	}

	public Menu getSubDefault() {
		return subDefault;
	}

	public void setSubDefault(Menu subDefault) {
		this.subDefault = subDefault;
	}

	public boolean isChangePassword() {
		return changePassword;
	}

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public Integer getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(Integer remotePort) {
		this.remotePort = remotePort;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public Integer getLocalPort() {
		return localPort;
	}

	public void setLocalPort(Integer localPort) {
		this.localPort = localPort;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public Date getLoginFailedDate() {
		return loginFailedDate;
	}

	public void setLoginFailedDate(Date loginFailedDate) {
		this.loginFailedDate = loginFailedDate;
	}

	public int getLoginFailedTimes() {
		return loginFailedTimes;
	}

	public void setLoginFailedTimes(int loginFailedTimes) {
		this.loginFailedTimes = loginFailedTimes;
	}

	public boolean isLogout() {
		return logout;
	}

	public void setLogout(boolean logout) {
		this.logout = logout;
	}

}
