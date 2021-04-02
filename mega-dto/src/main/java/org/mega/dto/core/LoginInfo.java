package org.mega.dto.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.mega.constant.Constant;
import org.mega.dao.entity.Branch;
import org.mega.dao.entity.Company;
import org.mega.dao.entity.Department;
import org.mega.dao.entity.Employee;
import org.mega.dao.entity.Users;

public class LoginInfo implements Serializable {
	private static final long serialVersionUID = 6152914188865610649L;
	private String account;
	private Long userId;
	private String userName;
	private Users user;

	private Long employeeId;
	private String employeeName;
	private Employee employee;

	private Long companyId;
	private String companyName;
	private Company company;

	private Long branchId;
	private String branchName;
	private Branch branch;

	private Long departmentId;
	private String departmentName;
	private Department department;
	private Long groupId;
	private Long supervisorId;

	private Map<String, String> business = new HashMap<String, String>();
	private Map<String, Map<String, String>> mapControl = new HashMap<String, Map<String, String>>();
	private Map<String, Map<String, String>> mapActionControl = new HashMap<String, Map<String, String>>();
	private Map<String, Map<String, Boolean>> mapRendered = new HashMap<String, Map<String, Boolean>>();
	private Map<String, Map<String, Boolean>> mapDisabled = new HashMap<String, Map<String, Boolean>>();
	private Map<String, Map<String, Boolean>> mapHidden = new HashMap<String, Map<String, Boolean>>();
	private Map<String, Map<String, Boolean>> mapReadOnly = new HashMap<String, Map<String, Boolean>>();

	private Map<String, Integer> levelData = new HashMap<String, Integer>();
	private Map<String, Boolean> approveLevel1 = new HashMap<String, Boolean>();
	private Map<String, Boolean> approveLevel2 = new HashMap<String, Boolean>();
	private Map<String, Boolean> approveLevel3 = new HashMap<String, Boolean>();

	private boolean manager = false;
	private boolean managerBranch = false;
	private boolean managerCompany = false;

	private boolean superRoot = false;

	private Locale locale = Locale.forLanguageTag("vi-VN");
	private TimeZone zone = TimeZone.getTimeZone("GMT+07:00");

	private String sessionId;

	private String remoteAddr;
	private String remoteHost;
	private Integer remotePort;
	private String localName;
	private Integer localPort;
	private Integer serverPort;
	private String serverName;
	private String serverPath;

	public LoginInfo() {
		super();
	}

	public LoginInfo(boolean init) {
		this.userId = 0L;
		this.userName = "root";

		this.user = new Users();
		this.user.setId(this.userId);
		this.user.setName(this.userName);

		this.employeeId = 10101L;
		this.employeeName = "Super Suntech";

		this.employee = new Employee();
		this.employee.setId(this.employeeId);
		this.employee.setName(this.employeeName);

		this.companyId = 1L;
		this.companyName = "Suntech";
		this.branchId = 101L;
		this.branchName = "Suntech";
		this.departmentId = 10101L;
		this.departmentName = "Suntech";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Map<String, String> getBusiness() {
		return business;
	}

	public void setBusiness(Map<String, String> business) {
		this.business = business;
	}

	public Map<String, Map<String, String>> getMapControl() {
		return mapControl;
	}

	public void setMapControl(Map<String, Map<String, String>> mapControl) {
		this.mapControl = mapControl;
	}

	public Map<String, Map<String, String>> getMapActionControl() {
		return mapActionControl;
	}

	public void setMapActionControl(Map<String, Map<String, String>> mapActionControl) {
		this.mapActionControl = mapActionControl;
	}

	public Map<String, Integer> getLevelData() {
		return levelData;
	}

	public void setLevelData(Map<String, Integer> levelData) {
		this.levelData = levelData;
	}

	public Integer getLevelData(String business) {
		Integer levelData = this.levelData.get(business);
		return levelData != null ? levelData : Constant.LEVEL_DATA_SELF;
	}

	public boolean isLevelDataSelf(String business) {
		Integer levelData = this.levelData.get(business);
		return Constant.LEVEL_DATA_SELF.equals(levelData);
	}

	public boolean isLevelDataGroup(String business) {
		Integer levelData = this.levelData.get(business);
		return Constant.LEVEL_DATA_GROUP.equals(levelData);
	}

	public boolean isLevelDataDepartment(String business) {
		Integer levelData = this.levelData.get(business);
		return Constant.LEVEL_DATA_DEPARTMENT.equals(levelData);
	}

	public boolean isLevelDataBranch(String business) {
		Integer levelData = this.levelData.get(business);
		return Constant.LEVEL_DATA_BRANCH.equals(levelData);
	}

	public boolean isLevelDataCompany(String business) {
		Integer levelData = this.levelData.get(business);
		return Constant.LEVEL_DATA_COMPANY.equals(levelData);
	}

	public Map<String, Map<String, Boolean>> getMapRendered() {
		return mapRendered;
	}

	public void setMapRendered(Map<String, Map<String, Boolean>> mapRendered) {
		this.mapRendered = mapRendered;
	}

	public Map<String, Map<String, Boolean>> getMapDisabled() {
		return mapDisabled;
	}

	public void setMapDisabled(Map<String, Map<String, Boolean>> mapDisabled) {
		this.mapDisabled = mapDisabled;
	}

	public Map<String, Map<String, Boolean>> getMapHidden() {
		return mapHidden;
	}

	public void setMapHidden(Map<String, Map<String, Boolean>> mapHidden) {
		this.mapHidden = mapHidden;
	}

	public Map<String, Map<String, Boolean>> getMapReadOnly() {
		return mapReadOnly;
	}

	public void setMapReadOnly(Map<String, Map<String, Boolean>> mapReadOnly) {
		this.mapReadOnly = mapReadOnly;
	}

	public Map<String, Boolean> getRendered(String business) {
		return mapRendered != null ? mapRendered.get(business) : new HashMap<String, Boolean>();
	}

	public Map<String, Boolean> getDisabled(String business) {
		return mapDisabled != null ? mapDisabled.get(business) : new HashMap<String, Boolean>();
	}

	public Map<String, Boolean> getHidden(String business) {
		return mapHidden != null ? mapHidden.get(business) : new HashMap<String, Boolean>();
	}

	public Map<String, Boolean> getReadOnly(String business) {
		return mapReadOnly != null ? mapReadOnly.get(business) : new HashMap<String, Boolean>();
	}

	public Map<String, Boolean> getApproveLevel1() {
		return approveLevel1;
	}

	public void setApproveLevel1(Map<String, Boolean> approveLevel1) {
		this.approveLevel1 = approveLevel1;
	}

	public Map<String, Boolean> getApproveLevel2() {
		return approveLevel2;
	}

	public void setApproveLevel2(Map<String, Boolean> approveLevel2) {
		this.approveLevel2 = approveLevel2;
	}

	public Map<String, Boolean> getApproveLevel3() {
		return approveLevel3;
	}

	public void setApproveLevel3(Map<String, Boolean> approveLevel3) {
		this.approveLevel3 = approveLevel3;
	}

	public int getApproveLevel(String business) {
		int level = 0;
		if (isApproveLevel3(business)) {
			level = 3;
		} else if (isApproveLevel2(business)) {
			level = 2;
		} else if (isApproveLevel1(business)) {
			level = 1;
		}
		return level;
	}

	public boolean isApproveLevel1(String business) {
		Boolean approve = approveLevel1.get(business);
		return approve != null ? approve.booleanValue() : false;
	}

	public boolean isApproveLevel2(String business) {
		Boolean approve = approveLevel2.get(business);
		return approve != null ? approve.booleanValue() : false;
	}

	public boolean isApproveLevel3(String business) {
		Boolean approve = approveLevel3.get(business);
		return approve != null ? approve.booleanValue() : false;
	}

	public int getApproveLevel(String business, Object detail) {
		int level = 0;
		if (isApproveLevel3(business, detail)) {
			level = 3;
		} else if (isApproveLevel2(business, detail)) {
			level = 2;
		} else if (isApproveLevel1(business, detail)) {
			level = 1;
		}
		return level;
	}

	public boolean isApproveLevel1(String business, Object detail) {
		if (detail != null) {
			business += "-" + detail;
		}
		Boolean approve = approveLevel1.get(business);
		return approve != null ? approve.booleanValue() : false;
	}

	public boolean isApproveLevel2(String business, Object detail) {
		if (detail != null) {
			business += "-" + detail;
		}
		Boolean approve = approveLevel2.get(business);
		return approve != null ? approve.booleanValue() : false;
	}

	public boolean isApproveLevel3(String business, Object detail) {
		if (detail != null) {
			business += "-" + detail;
		}
		Boolean approve = approveLevel3.get(business);
		return approve != null ? approve.booleanValue() : false;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	public boolean isManagerBranch() {
		return managerBranch;
	}

	public void setManagerBranch(boolean managerBranch) {
		this.managerBranch = managerBranch;
	}

	public boolean isManagerCompany() {
		return managerCompany;
	}

	public void setManagerCompany(boolean managerCompany) {
		this.managerCompany = managerCompany;
	}

	public boolean isSuperRoot() {
		return superRoot;
	}

	public void setSuperRoot(boolean superRoot) {
		this.superRoot = superRoot;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public TimeZone getZone() {
		return zone;
	}

	public void setZone(TimeZone zone) {
		this.zone = zone;
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

}
