package org.mega.business.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.mega.business.core.BaseService;
import org.mega.client.ParamQuery;
import org.mega.common.Lib;
import org.mega.constant.Constant;
import org.mega.dao.entity.Branch;
import org.mega.dao.entity.Brand;
import org.mega.dao.entity.BusinessFlow;
import org.mega.dao.entity.Company;
import org.mega.dao.entity.Country;
import org.mega.dao.entity.Currency;
import org.mega.dao.entity.Department;
import org.mega.dao.entity.Employee;
import org.mega.dao.entity.EmployeeGroup;
import org.mega.dao.entity.Location;
import org.mega.dao.entity.SysSetting;
import org.mega.dao.entity.Unit;
import org.mega.dao.entity.UserBusiness;
import org.mega.dto.core.Dto;
import org.mega.dto.core.SelectItem;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.HtmlExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CommonService extends BaseService {
	public static final String EMPTY_JSON = "{}";

	public static final String IM0001 = "IM0001";
	public static final String IM0002 = "IM0002";
	public static final String IM0003 = "IM0003";
	public static final String IM0004 = "IM0004";
	public static final String IM0005 = "IM0005";
	public static final String IM0006 = "IM0006";
	public static final String IM0007 = "IM0007";
	public static final String IM0008 = "IM0008";
	public static final String IM0009 = "IM0009";
	public static final String IM0010 = "IM0010";
	public static final String IM0011 = "IM0011";
	public static final String IM0012 = "IM0012";
	public static final String IM0013 = "IM0013";
	public static final String IM0014 = "IM0014";
	public static final String IM0015 = "IM0015";
	public static final String IM0016 = "IM0016";
	public static final String IM0017 = "IM0017";
	public static final String IM0018 = "IM0018";
	public static final String IM0019 = "IM0019";
	public static final String IM0020 = "IM0020";
	public static final String IM0021 = "IM0021";

	public static final String EM0001 = "EM0001";
	public static final String EM0002 = "EM0002";
	public static final String EM0003 = "EM0003";
	public static final String EM0004 = "EM0004";
	public static final String EM0005 = "EM0005";
	public static final String EM0006 = "EM0006";
	public static final String EM0007 = "EM0007";
	public static final String EM0008 = "EM0008";
	public static final String EM0009 = "EM0009";
	public static final String EM0010 = "EM0010";
	public static final String EM0011 = "EM0011";
	public static final String EM0012 = "EM0012";
	public static final String EM0013 = "EM0013";
	public static final String EM0014 = "EM0014";
	public static final String EM0015 = "EM0015";
	public static final String EM0016 = "EM0016";
	public static final String EM0017 = "EM0017";
	public static final String EM0018 = "EM0018";
	public static final String EM0019 = "EM0019";
	public static final String EM0020 = "EM0020";
	public static final String EM0021 = "EM0021";
	public static final String EM0022 = "EM0022";
	public static final String EM0023 = "EM0023";
	public static final String EM0024 = "EM0024";
	public static final String EM0025 = "EM0025";
	public static final String EM0026 = "EM0026";
	public static final String EM0027 = "EM0027";
	public static final String EM0028 = "EM0028";
	public static final String EM0029 = "EM0029";
	public static final String EM0030 = "EM0030";
	public static final String EM0031 = "EM0031";
	public static final String EM0032 = "EM0032";
	public static final String EM0033 = "EM0033";
	public static final String EM0034 = "EM0034";
	public static final String EM0035 = "EM0035";
	public static final String EM0036 = "EM0036";
	public static final String EM0037 = "EM0037";
	public static final String EM0038 = "EM0038";
	public static final String EM0039 = "EM0039";
	public static final String EM0040 = "EM0040";
	public static final String EM0041 = "EM0041";
	public static final String EM0042 = "EM0042";
	public static final String EM0043 = "EM0043";
	public static final String EM0044 = "EM0044";
	public static final String EM0045 = "EM0045";

	public static final String EM1100 = "EM1100";

	public CommonService() {
		super();
	}

	/**
	 * 
	 * @param dto
	 */
	public void businessFlow(Dto dto) {
		// Current business
		BusinessFlow bussinessFlow = getBusinessFlow(dto.getBusinessCode(), loginInfo.getCompanyId());
		if (bussinessFlow != null) {
			dto.setBusinessName(bussinessFlow.getName());
		}

		// Next business
		List<BusinessFlow> nextBusinessList = getNextBusinessList(dto.getBusinessCode(), loginInfo.getCompanyId());
		if (nextBusinessList != null && !nextBusinessList.isEmpty()) {
			bussinessFlow = nextBusinessList.get(0);
			if (bussinessFlow != null) {
				dto.setNextBusinessCode(bussinessFlow.getCode());
				dto.setNextBusinessName(bussinessFlow.getName());
				dto.setNextStage(bussinessFlow.getStage());
				dto.setNextBusinessAddMore(bussinessFlow.isAddMore());
			}
		}
		dto.setNextBusinessList(nextBusinessList);

		// Previous business
		bussinessFlow = getBusinessFlow(dto.getPrevBusinessCode(), loginInfo.getCompanyId());
		if (bussinessFlow != null) {
			dto.setPrevBusinessName(bussinessFlow.getName());
		}
	}

	/**
	 * 
	 * @param code
	 * @param companyId
	 * @return
	 */
	public BusinessFlow getBusinessFlow(String code, Long companyId) {
		BusinessFlow businessFlow = null;
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("BusinessFlow.findByCode");
		paramQuery.addParam("code", code);
		paramQuery.addParam("companyId", companyId);
		businessFlow = getEntity(paramQuery);
		return businessFlow;
	}

	/**
	 * 
	 * @param code
	 * @param companyId
	 * @return
	 */
	public List<BusinessFlow> getNextBusinessList(String code, Long companyId) {
		List<BusinessFlow> nextBusinessList = null;
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("BusinessFlow.findNextBusiness");
		paramQuery.addParam("prevStep", code);
		paramQuery.addParam("companyId", companyId);
		nextBusinessList = getEntities(paramQuery);
		return nextBusinessList;
	}

	/**
	 * Get role approve
	 * 
	 * @param business
	 * @return
	 */
	public List<UserBusiness> getUserBusiness(Long userId) {
		List<UserBusiness> list = null;
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("UserBusiness.findByUser");
		paramQuery.addParam("userId", userId);
		list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get role approve
	 * 
	 * @param business
	 * @return
	 */
	public List<UserBusiness> getUserBusiness(String business) {
		List<UserBusiness> list = null;
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("UserBusiness.findByBusiness");
		paramQuery.addParam("business", business);
		list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get role approve
	 * 
	 * @param business
	 * @return
	 */
	public UserBusiness getUserBusiness(String business, Long userId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("UserBusiness.findByUserBusiness");
		paramQuery.addParam("userId", userId);
		paramQuery.addParam("business", business);
		paramQuery.setFirst(true);
		UserBusiness entity = getEntity(paramQuery);
		return entity;
	}

	/**
	 * Get level approve
	 * 
	 * @param business
	 * @param userId
	 * @return
	 */
	public int getApproveLevel(String business, Long userId) {
		UserBusiness userBusiness = getUserBusiness(business, userId);
		int level = 0;
		if (userBusiness != null) {
			if (userBusiness.isLevelApprove3()) {
				level = 3;
			} else if (userBusiness.isLevelApprove2()) {
				level = 2;
			} else if (userBusiness.isLevelApprove1()) {
				level = 1;
			}
		}
		return level;
	}

	/**
	 * 
	 * @return
	 */
	public List<SysSetting> getSysSetting() {
		List<SysSetting> list = null;
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("SysSetting.findAll");
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		list = getEntities(paramQuery);
		return list;
	}

	public List<SysSetting> getSysSetting(String category) {
		List<SysSetting> list = null;
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("SysSetting.findByCategory");
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		paramQuery.addParam("category", category);
		list = getEntities(paramQuery);
		return list;
	}

	public List<SysSetting> getSysSetting(String category, String key) {
		List<SysSetting> list = null;
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("SysSetting.findByKey");
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		paramQuery.addParam("category", category);
		paramQuery.addParam("key", key);
		list = getEntities(paramQuery);
		return list;
	}

	public SysSetting getSysSettingItemValue(String category, String key) {
		List<SysSetting> list = null;
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("SysSetting.findByKey");
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		paramQuery.addParam("category", category);
		paramQuery.addParam("key", key);
		list = getEntities(paramQuery);
		return list.get(0);
	}

	public String getSysSettingValue(String category, String key) {
		List<SysSetting> list = null;
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("SysSetting.findByKey");
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		paramQuery.addParam("category", category);
		paramQuery.addParam("key", key);
		list = getEntities(paramQuery);
		return (list != null && !list.isEmpty()) ? list.get(0).getValue() : null;
	}

	public List<SysSetting> getSysSetting(String category, String key, String value) {
		List<SysSetting> list = null;
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("SysSetting.findByValue");
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		paramQuery.addParam("category", category);
		paramQuery.addParam("key", key);
		paramQuery.addParam("value", value);
		list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get Expire day
	 * 
	 * @return
	 */
	public int getPasswordExpiryDate() {
		int value = 60;
		List<SysSetting> setting = getSysSetting("COMMON", "PasswordExpiry");
		if (setting != null && !setting.isEmpty()) {
			try {
				value = Integer.parseInt(setting.get(0).getValue());
			} catch (Exception e) {
				value = 60;
			}
		}
		return value;
	}

	/**
	 * Get items SysSetting
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<SelectItem> getSelectItems(List<SysSetting> list) {
		return getSelectItems(list, null);
	}

	public List<SelectItem> getSelectItems(List<SysSetting> list, String emptyLabel) {
		return getSelectItems(list, null, null);
	}

	public List<SelectItem> getSelectItems(List<SysSetting> list, Object value, String emptyLabel) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		if (emptyLabel != null) {
			items.add(new SelectItem((value == null ? "0" : value), emptyLabel));
		}
		if (list != null) {
			for (SysSetting e : list) {
				items.add(new SelectItem(e.getValue(), e.getName()));
			}
		}
		return items;
	}

	/**
	 * Get prefix of code
	 * 
	 * @param key
	 * @return
	 */
	public String getCodePrefix(String key) {
		List<SysSetting> list = getSysSetting("CODE_PREFIX", key);
		String prefix = "";
		if (list != null && !list.isEmpty()) {
			prefix = list.get(0).getValue();
		}
		return prefix;
	}

	/**
	 * Get list Business
	 * 
	 * @return
	 */
	public List<BusinessFlow> getBusinessFlowList(Long companyId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("BusinessFlow.findAllBusiness");
		paramQuery.addParam("companyId", companyId);
		List<BusinessFlow> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get items for Business
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<SelectItem> getBusinessFlowItems(List<BusinessFlow> list) {
		return getBusinessFlowItems(list, null);
	}

	public List<SelectItem> getBusinessFlowItems(List<BusinessFlow> list, String emptyLabel) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		if (emptyLabel != null) {
			items.add(new SelectItem("", emptyLabel));
		}
		if (list != null) {
			for (BusinessFlow e : list) {
				items.add(new SelectItem(e.getCode(), e.getName()));
			}
		}
		return items;
	}

	/**
	 * Get list Department
	 * 
	 * @return
	 */
	public List<Branch> getBranchList(Long companyId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Branch.findByCompanyId");
		paramQuery.addParam("companyId", companyId);
		List<Branch> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get items for Department
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<SelectItem> getBranchItems(List<Branch> list) {
		return getBranchItems(list, null);
	}

	public List<SelectItem> getBranchItems(List<Branch> list, String emptyLabel) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		if (emptyLabel != null) {
			items.add(new SelectItem(0L, emptyLabel));
		}
		if (list != null) {
			for (Branch e : list) {
				items.add(new SelectItem(e.getId(), e.getName()));
			}
		}
		return items;
	}

	/**
	 * Get list Department
	 * 
	 * @return
	 */
	public List<Department> getDepartmentList(Long branchId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Department.findByBranchId");
		paramQuery.addParam("branchId", branchId == null ? 0L : branchId);
		List<Department> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get items for Department
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<SelectItem> getDepartmentItems(List<Department> list) {
		return getDepartmentItems(list, null);
	}

	public List<SelectItem> getDepartmentItems(List<Department> list, String emptyLabel) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		if (emptyLabel != null) {
			items.add(new SelectItem(0L, emptyLabel));
		}
		if (list != null) {
			for (Department e : list) {
				items.add(new SelectItem(e.getId(), e.getName()));
			}
		}
		return items;
	}

	/**
	 * Get list EmployeeGroup
	 * 
	 * @return
	 */
	public List<EmployeeGroup> getEmployeeGroupList(Long departmentId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("EmployeeGroup.findByDepartmentId");
		paramQuery.addParam("departmentId", departmentId == null ? 0L : departmentId);
		paramQuery.addParam("branchId", loginInfo.getBranchId());
		List<EmployeeGroup> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get items for EmployeeGroup
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<SelectItem> getEmployeeGroupItems(List<EmployeeGroup> list) {
		return getEmployeeGroupItems(list, null);
	}

	public List<SelectItem> getEmployeeGroupItems(List<EmployeeGroup> list, String emptyLabel) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		if (emptyLabel != null) {
			items.add(new SelectItem(0L, emptyLabel));
		}
		if (list != null) {
			for (EmployeeGroup e : list) {
				items.add(new SelectItem(e.getId(), e.getName()));
			}
		}
		return items;
	}

	/**
	 * Get list Manager
	 * 
	 * @return
	 */
	public List<Employee> getManagerList() {
		return getManagerList(0L, 0L);
	}

	/**
	 * Get list Manager
	 * 
	 * @return
	 */
	public List<Employee> getManagerList(Long branchId) {
		return getManagerList(branchId, 0L);
	}

	/**
	 * Get list Manager
	 * 
	 * @return
	 */
	public List<Employee> getManagerList(Long branchId, Long departmentId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Employee.findManager");
		paramQuery.addParam("departmentId", departmentId == null ? 0L : departmentId);
		paramQuery.addParam("branchId", branchId == null ? 0L : branchId);
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		List<Employee> list = getEntities(paramQuery);
		return list;
	}

	public Employee getEmployee(String code) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Employee.findByCode");
		paramQuery.addParam("code", code);
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		paramQuery.setFirst(true);
		Employee entity = getEntity(paramQuery);
		return entity;
	}

	public List<Employee> getEmployeeList(Long companyId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Employee.findAll");
		paramQuery.addParam("companyId", companyId);
		List<Employee> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get list Manager
	 * 
	 * @return
	 */
	public List<Employee> getEmployeeList(Long departmentId, Long groupId) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Employee.findEmployee");
		paramQuery.addParam("departmentId", departmentId);
		paramQuery.addParam("groupId", groupId);
		paramQuery.addParam("branchId", loginInfo.getBranchId());
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		List<Employee> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get items for Manager
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<SelectItem> getEmployeeItems(List<Employee> list) {
		return getEmployeeItems(list, null);
	}

	public List<SelectItem> getEmployeeItems(List<Employee> list, String emptyLabel) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		if (emptyLabel != null) {
			items.add(new SelectItem(0L, emptyLabel));
		}
		if (list != null) {
			for (Employee e : list) {
				items.add(new SelectItem(e.getId(), e.getName()));
			}
		}
		return items;
	}

	/**
	 * 
	 * @return
	 */
	public List<Brand> getBrandList() {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Brand.findAll");
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		List<Brand> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get items for brand
	 * 
	 * @param list
	 * @return
	 */
	public List<SelectItem> getBrandItems(List<Brand> list) {
		return getBrandItems(list, null);
	}

	public List<SelectItem> getBrandItems(List<Brand> list, String emptyLabel) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		if (emptyLabel != null) {
			items.add(new SelectItem(0L, emptyLabel));
		}
		if (list != null) {
			for (Brand e : list) {
				items.add(new SelectItem(e.getId(), e.getName()));
			}
		}
		return items;
	}

	/**
	 * 
	 * @return
	 */
	public String getBrandJSON(List<Brand> dataList) {
		String ret = EMPTY_JSON;
		if (dataList != null && !dataList.isEmpty()) {
			JSONArray data = new JSONArray();
			JSONObject json = null;
			for (Brand e : dataList) {
				json = new JSONObject();
				json.put("value", e.getId());
				json.put("name", e.getName());
				json.put("label", e.getCode().concat(" ").concat(e.getName()));
				json.put("desc", e.getName());
				data.add(json);
			}
			ret = data.toString();
		}
		return ret;
	}

	/**
	 * 
	 * @return
	 */
	public List<Location> getLocationList() {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Location.findAll");
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		List<Location> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * 
	 * @return
	 */
	public String getLocationJSON(List<Location> dataList) {
		String ret = EMPTY_JSON;
		if (dataList != null && !dataList.isEmpty()) {
			JSONArray data = new JSONArray();
			JSONObject json = null;
			for (Location e : dataList) {
				json = new JSONObject();
				json.put("value", e.getId());
				json.put("name", e.getName());
				json.put("label", e.getCode().concat(" ").concat(e.getName()));
				json.put("desc", e.getName());
				data.add(json);
			}
			ret = data.toString();
		}
		return ret;
	}

	/**
	 * 
	 * @return
	 */
	public List<Country> getCountryList() {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Country.findAll");
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		List<Country> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * 
	 * @return
	 */
	public String getCountryJSON(List<Country> dataList) {
		String ret = EMPTY_JSON;
		if (dataList != null && !dataList.isEmpty()) {
			JSONArray data = new JSONArray();
			JSONObject json = null;
			for (Country e : dataList) {
				json = new JSONObject();
				json.put("value", e.getId());
				json.put("name", e.getName());
				json.put("label", e.getCode().concat(" ").concat(e.getName()));
				json.put("desc", e.getName());
				data.add(json);
			}
			ret = data.toString();
		}
		return ret;
	}

	public Currency getDefaultCurrency() {
		return getDefaultCurrency(getCurrencyList());
	}

	public Currency getDefaultCurrency(List<Currency> list) {
		Currency currency = null;
		for (Currency e : list) {
			if (e.isDefaultCurrency()) {
				currency = e;
				break;
			}
		}
		return currency;
	}

	public int getDisplayDecimal(String code) {
		Currency currency = getCurrency(code);
		if (currency == null) {
			currency = getDefaultCurrency();
		}
		int ret = 0;
		if (currency != null) {
			ret = currency.getDisplayDecimal();
		}
		return ret;
	}

	public Currency getCurrency(String code) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Currency.findByCode");
		paramQuery.addParam("code", code);
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		Currency currency = getEntity(paramQuery);
		return currency;
	}

	public boolean isBaseCurrency(String code) {
		Currency currency = getCurrency(code);
		if (currency != null) {
			return currency.isBaseCurrency();
		}
		return false;
	}

	/**
	 * Get currency
	 * 
	 * @return
	 */
	public List<Currency> getCurrencyList() {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Currency.findAll");
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		List<Currency> list = getEntities(paramQuery);
		return list;
	}

	/**
	 * Get items for currency
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<SelectItem> getCurrencyItems(List<Currency> list) {
		return getCurrencyItems(list, null);
	}

	public List<SelectItem> getCurrencyItems(List<Currency> list, String emptyLabel) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		if (emptyLabel != null) {
			items.add(new SelectItem("0", emptyLabel));
		}
		if (list != null) {
			SelectItem item = null;
			for (Currency e : list) {
				item = new SelectItem(e.getCode(), e.getCode());
				item.setValueOther(e.getExchangeRate());
				items.add(item);
			}
		}
		return items;
	}

	public String getLabel(List<SelectItem> items, Object value) {
		String label = null;
		if (value != null && items != null) {
			for (SelectItem e : items) {
				if (value.equals(e.getValue())) {
					label = e.getLabel();
					break;
				}
			}
		}
		return label;
	}

	/**
	 * Get Unit of Item
	 * 
	 * @param itemId
	 * @return
	 */
	public List<SelectItem> getUnitItems() {
		return getUnitItems(getUnitList());
	}

	/**
	 * Get items for brand
	 * 
	 * @param list
	 * @return
	 */
	public List<SelectItem> getUnitItems(List<Unit> list) {
		return getUnitItems(list, null);
	}

	public List<SelectItem> getUnitItems(List<Unit> list, String emptyLabel) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		if (emptyLabel != null) {
			items.add(new SelectItem(0L, emptyLabel));
		}
		if (list != null) {
			for (Unit e : list) {
				items.add(new SelectItem(e.getId(), e.getName()));
			}
		}
		return items;
	}

	/**
	 * Get Unit of Item
	 * 
	 * @param itemId
	 * @return
	 */
	public List<Unit> getUnitList() {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Unit.findAll");
		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		List<Unit> list = getEntities(paramQuery);
		return list;
	}

	public String skuCode(String itemCode, String lot, String model, String serial, Date expireDate, Long unitId) {
		StringBuffer code = new StringBuffer(itemCode);
		code.append("-");
		code.append(Lib.isEmpty(lot) ? "0" : lot.trim());
		code.append("-");
		code.append(Lib.isEmpty(model) ? "0" : model.trim());
		code.append("-");
		code.append(Lib.isEmpty(serial) ? "0" : serial.trim());
		code.append("-");
		code.append(expireDate == null ? "0" : Lib.dt2Str(expireDate, "yyyyMMdd", zone, locale));
		code.append("-");
		code.append(unitId);
		return code.toString();
	}

	/**
	 * Get report file template
	 * 
	 * @param dto
	 * @param reportName
	 * @param companyId
	 * @param branchId
	 * @return
	 */
	public File getReportTemplate(Dto dto, String reportName, Long companyId, Long branchId) {
		File reportFile = null;
		String reportPath = dto.getReportPath();
		Branch branch = findEntity(Branch.class, branchId);
		Company company = null;
		if (branch != null) {
			company = branch.getCompany();
			if (company == null) {
				company = findEntity(Company.class, companyId);
			}
		} else {
			branch = loginInfo.getBranch();
		}

		if (company == null) {
			company = loginInfo.getCompany();
		}

		String companyReportPath = Lib.toString(company.getReportPath(), "");
		String branchReportPath = Lib.toString(branch.getReportPath(), "");

		String reportFileName = reportPath + File.separator + companyReportPath + File.separator + branchReportPath
				+ File.separator + reportName + Constant.EXTENSION_JASPER;
		reportFile = new File(reportFileName);

		if (!reportFile.exists()) {
			reportFileName = reportPath + File.separator + companyReportPath + File.separator + reportName
					+ Constant.EXTENSION_JASPER;
			reportFile = new File(reportFileName);
			if (!reportFile.exists()) {
				reportFileName = reportPath + File.separator + reportName + Constant.EXTENSION_JASPER;
				reportFile = new File(reportFileName);
			}
		}

		if (!reportFile.exists()) {
			messageDto.showError(Lib.getMessage(dto.getLocale(), "EM2002", reportName));
		}
		return reportFile;
	}

	public String getBackgroundTemplate(String realPath, String fileName) {
		realPath = realPath + (fileName == null ? "/Background.png" : ("/" + fileName));
		File reportFile = null;
		reportFile = new File(realPath);
		return reportFile.exists() ? realPath : null;
	}

	public String getBackgroundTemplate(String realPath) {
		return getBackgroundTemplate(realPath, null);
	}

	/**
	 * Export data from template
	 * 
	 * @param exporter
	 * @param reportName
	 * @param fileName
	 * @param param
	 * @param data
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] export(Exporter exporter, Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		if (!reportFile.exists()) {
			return null;
		}
		if (param == null) {
			param = new HashMap<String, Object>();
		}

		String reportPath = reportFile.getPath();

		param.put("SUBREPORT_DIR", reportPath);
		param.put("REPORT_PATH", reportPath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRDataSource dataSource = null;
		if (data == null) {
			dataSource = new JREmptyDataSource();
		} else {
			dataSource = new JRBeanCollectionDataSource(data, false);
		}

		JasperReport report = (JasperReport) JRLoader.loadObject(reportFile);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, param, dataSource);

		// Export data
		ExporterInput input = new SimpleExporterInput(jasperPrint);
		ExporterOutput output = new SimpleOutputStreamExporterOutput(baos);
		exporter.setExporterInput(input);
		exporter.setExporterOutput(output);
		exporter.exportReport();

		return baos.toByteArray();
	}

	@SuppressWarnings("rawtypes")
	public byte[] export(Exporter exporter, Map<String, Object> param, List<?> data, String reportFileName)
			throws Exception {
		File reportFile = new File(reportFileName);
		return export(exporter, param, data, reportFile);
	}

	/**
	 * Export data
	 * 
	 * @param param
	 * @param data
	 * @param reportFile
	 * @return
	 * @throws Exception
	 */
	public byte[] pdf(Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		return export(new JRPdfExporter(), param, data, reportFile);
	}

	/**
	 *
	 * @param reportName
	 * @param fileName
	 * @param param
	 * @param data
	 * @throws Exception
	 */
	public byte[] xls(Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		return export(new JRXlsExporter(), param, data, reportFile);
	}

	/**
	 *
	 * @param reportName
	 * @param fileName
	 * @param param
	 * @param data
	 * @throws Exception
	 */
	public byte[] jxl(Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		return export(new JRXlsExporter(), param, data, reportFile);
	}

	/**
	 *
	 * @param reportName
	 * @param fileName
	 * @param param
	 * @param data
	 * @throws Exception
	 */
	public byte[] csv(Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		return export(new JRCsvExporter(), param, data, reportFile);
	}

	/**
	 *
	 * @param reportName
	 * @param fileName
	 * @param param
	 * @param data
	 * @throws Exception
	 */
	public byte[] odt(Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		return export(new JROdtExporter(), param, data, reportFile);
	}

	/**
	 *
	 * @param reportName
	 * @param fileName
	 * @param param
	 * @param data
	 * @throws Exception
	 */
	public byte[] ods(Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		return export(new JROdsExporter(), param, data, reportFile);
	}

	/**
	 *
	 * @param reportName
	 * @param fileName
	 * @param param
	 * @param data
	 * @throws Exception
	 */
	public byte[] docx(Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		return export(new JRDocxExporter(), param, data, reportFile);
	}

	/**
	 *
	 * @param reportName
	 * @param fileName
	 * @param param
	 * @param data
	 * @throws Exception
	 */
	public byte[] xlsx(Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		return export(new JRXlsxExporter(), param, data, reportFile);
	}

	/**
	 *
	 * @param reportName
	 * @param fileName
	 * @param param
	 * @param data
	 * @throws Exception
	 */
	public byte[] pptx(Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		return export(new JRPptxExporter(), param, data, reportFile);
	}

	/**
	 *
	 * @param reportName
	 * @param fileName
	 * @param param
	 * @param data
	 * @throws Exception
	 */
	public byte[] xml(Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		return export(new JRXmlExporter(), param, data, reportFile);
	}

	/**
	 *
	 * @param reportName
	 * @param fileName
	 * @param param
	 * @param data
	 * @throws Exception
	 */
	public byte[] html(Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		return export(new HtmlExporter(), param, data, reportFile);
	}

	/**
	 *
	 * @param reportName
	 * @param fileName
	 * @param param
	 * @param data
	 * @throws Exception
	 */
	public byte[] rtf(Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		return export(new JRRtfExporter(), param, data, reportFile);
	}

	/**
	 * Export JasperPrint
	 * 
	 * @param param
	 * @param data
	 * @param reportFile
	 * @return
	 * @throws Exception
	 */
	public JasperPrint export(Map<String, Object> param, List<?> data, File reportFile) throws Exception {
		if (!reportFile.exists()) {
			return null;
		}
		if (param == null) {
			param = new HashMap<String, Object>();
		}

		String reportPath = reportFile.getPath();

		param.put("SUBREPORT_DIR", reportPath);
		param.put("REPORT_PATH", reportPath);
		JRDataSource dataSource = null;
		if (data == null) {
			dataSource = new JREmptyDataSource();
		} else {
			dataSource = new JRBeanCollectionDataSource(data, false);
		}

		JasperReport report = (JasperReport) JRLoader.loadObject(reportFile);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, param, dataSource);
		return jasperPrint;
	}

	/**
	 * Merge report
	 * 
	 * @param reports
	 * @return
	 * @throws Exception
	 */
	protected JasperPrint mergeReport(List<JasperPrint> reports) throws Exception {
		JasperPrint mergeReport = null;
		if (Lib.isNotEmpty(reports)) {
			mergeReport = reports.get(0);
			if (reports.size() > 1) {
				for (int index = 1; index < reports.size(); index++) {
					Iterator<JRPrintPage> i = reports.get(index).getPages().iterator();
					while (i.hasNext()) {
						mergeReport.addPage(i.next());
					}
				}
			}
		}

		return mergeReport;
	}

	protected JasperPrint mergeReport(JasperPrint... reports) throws Exception {
		JasperPrint mergeReport = null;
		if (reports != null && reports.length > 0) {
			mergeReport = reports[0];
			for (int i = 1; i < reports.length; i++) {
				Iterator<JRPrintPage> it = reports[i].getPages().iterator();
				while (it.hasNext()) {
					mergeReport.addPage(it.next());
				}
			}
		}

		return mergeReport;
	}

	/**
	 * Merge multiple pdf into one pdf
	 * 
	 * @param list
	 *            of pdf input stream
	 * @param outputStream
	 *            output file output stream
	 * @throws DocumentException
	 * @throws IOException
	 */
	public byte[] mergePdf(byte[]... pdfs) {
		byte[] pdfMerger = null;
		if (pdfs == null) {
			return null;
		}

		if (pdfs.length == 1) {
			return pdfs[0];
		}
		Document document = null;
		ByteArrayOutputStream outputStream = null;
		try {
			outputStream = new ByteArrayOutputStream();
			document = new Document();
			PdfWriter writer;

			writer = PdfWriter.getInstance(document, outputStream);

			document.open();
			PdfContentByte cb = writer.getDirectContent();

			PdfReader reader = null;
			for (int i = 0; i < pdfs.length; i++) {
				reader = new PdfReader(pdfs[i]);
				for (int j = 1; i <= reader.getNumberOfPages(); j++) {
					document.newPage();
					// import the page from source pdf
					PdfImportedPage page = writer.getImportedPage(reader, j);
					// add the page to the destination pdf
					cb.addTemplate(page, 0, 0);
				}
				reader.close();
			}

			outputStream.flush();
			pdfMerger = outputStream.toByteArray();

		} catch (Exception e) {
			pdfMerger = null;
		} finally {
			if (document != null && document.isOpen()) {
				document.close();
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e) {

				}
			}
		}

		return pdfMerger;
	}

	public static void mergePdf(List<InputStream> list, OutputStream outputStream)
			throws DocumentException, IOException {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		document.open();
		PdfContentByte cb = writer.getDirectContent();

		for (InputStream in : list) {
			PdfReader reader = new PdfReader(in);
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				document.newPage();
				// import the page from source pdf
				PdfImportedPage page = writer.getImportedPage(reader, i);
				// add the page to the destination pdf
				cb.addTemplate(page, 0, 0);
			}
		}

		outputStream.flush();
		document.close();
		outputStream.close();
	}

	/**
	 * Export to data
	 * 
	 * @param exporter
	 * @param jasperPrint
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] export(Exporter exporter, JasperPrint jasperPrint) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// Export data
		ExporterInput input = new SimpleExporterInput(jasperPrint);
		ExporterOutput output = new SimpleOutputStreamExporterOutput(baos);
		exporter.setExporterInput(input);
		exporter.setExporterOutput(output);
		exporter.exportReport();

		return baos.toByteArray();
	}

	/**
	 * Export to pdf data
	 * 
	 * @param jasperPrint
	 * @return
	 * @throws Exception
	 */
	public byte[] pdf(JasperPrint jasperPrint) throws Exception {
		return export(new JRPdfExporter(), jasperPrint);
	}

	public byte[] xls(JasperPrint jasperPrint) throws Exception {
		return export(new JRXlsExporter(), jasperPrint);
	}

	public byte[] xlsx(JasperPrint jasperPrint) throws Exception {
		return export(new JRXlsxExporter(), jasperPrint);
	}

	public byte[] docx(JasperPrint jasperPrint) throws Exception {
		return export(new JRDocxExporter(), jasperPrint);
	}

	public byte[] csv(JasperPrint jasperPrint) throws Exception {
		return export(new JRCsvExporter(), jasperPrint);
	}

	public byte[] img(JasperPrint jasperPrint) throws Exception {
		return export(new JRPdfExporter(), jasperPrint); // edit
	}

	public byte[] rtf(JasperPrint jasperPrint) throws Exception {
		return export(new JRRtfExporter(), jasperPrint);
	}

	public byte[] html(JasperPrint jasperPrint) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// Export data
		HtmlExporter exporter = new HtmlExporter();
		ExporterInput input = new SimpleExporterInput(jasperPrint);
		HtmlExporterOutput output = new SimpleHtmlExporterOutput(baos);

		exporter.setExporterInput(input);
		exporter.setExporterOutput(output);
		exporter.exportReport();

		return baos.toByteArray();
	}

	/**
	 * Export to data
	 * 
	 * @param jasperPrint
	 *            , standarType
	 * @return
	 * @throws Exception
	 */
	public byte[] exportFile(JasperPrint jasperPrint, String standarType) throws Exception {
		byte[] data = null;
		if (Constant.EXPORT_PDF.equals(standarType)) {
			data = pdf(jasperPrint);
		} else if (Constant.EXPORT_XLSX.equals(standarType)) {
			data = xlsx(jasperPrint);
		} else if (Constant.EXPORT_CSV.equals(standarType)) {
			data = csv(jasperPrint);
		} else if (Constant.EXPORT_DOCX.equals(standarType)) {
			data = docx(jasperPrint);
		} else if (Constant.EXPORT_IMAGE.equals(standarType)) {
			data = img(jasperPrint);
		} else if (Constant.EXPORT_HTML.equals(standarType)) {
			data = html(jasperPrint);
		} else if (Constant.EXPORT_RTF.equals(standarType)) {
			data = rtf(jasperPrint);
		}
		return data;
	}

	/**
	 * Export to string
	 * 
	 * @param jasperPrint
	 *            , standarType
	 * @return
	 * @throws Exception
	 */
	public String exportTemplate(String template, String standarType) throws Exception {
		String fileName = "";
		if (Constant.EXPORT_PDF.equals(standarType)) {
			fileName = template;
		} else if (Constant.EXPORT_XLSX.equals(standarType)) {
			fileName = template.concat("xlsx");
		} else if (Constant.EXPORT_CSV.equals(standarType)) {
			fileName = template.concat("csv");
		} else if (Constant.EXPORT_DOCX.equals(standarType)) {
			fileName = template.concat("docx");
		} else if (Constant.EXPORT_IMAGE.equals(standarType)) {
			fileName = template.concat("image");
		} else if (Constant.EXPORT_HTML.equals(standarType)) {
			fileName = template.concat("html");
		}
		return fileName;
	}

	public Date getBeginMorning() {
		Calendar cal = Calendar.getInstance(zone, locale);
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		return date;
	}

	public Date getEndMorning() {
		Calendar cal = Calendar.getInstance(zone, locale);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		return date;
	}

	public Date getBeginEvening() {
		Calendar cal = Calendar.getInstance(zone, locale);
		cal.set(Calendar.HOUR_OF_DAY, 13);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		return date;
	}

	public Date getEndEvening() {
		Calendar cal = Calendar.getInstance(zone, locale);
		cal.set(Calendar.HOUR_OF_DAY, 17);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		return date;
	}

	public int getBeginMorningHour() {
		int begin = 8;

		return begin;
	}

	public int getEndMorningHour() {
		int begin = 12;

		return begin;
	}

	public int getBeginEveningHour() {
		int begin = 13;

		return begin;
	}

	public int getEndEveningHour() {
		int begin = 17;

		return begin;
	}

	public int getBeginMorningMinute() {
		int begin = 0;

		return begin;
	}

	public int getEndMorningMinute() {
		int begin = 0;

		return begin;
	}

	public int getBeginEveningMinute() {
		int begin = 0;

		return begin;
	}

	public int getEndEveningMinute() {
		int begin = 0;

		return begin;
	}

	/**
	 * Get number day of work
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public double getWorkDay(Date startDate, Date endDate) {
		double day = 0;
		double minute = 0;
		Calendar start = Calendar.getInstance(zone, locale);
		Calendar end = Calendar.getInstance(zone, locale);
		start.setTime(startDate);
		end.setTime(endDate);

		double startDay = start.get(Calendar.DAY_OF_YEAR);
		double startHour = start.get(Calendar.HOUR_OF_DAY);
		double startMinute = start.get(Calendar.MINUTE);

		double endDay = end.get(Calendar.DAY_OF_YEAR);
		double endHour = end.get(Calendar.HOUR_OF_DAY);
		double endMinute = end.get(Calendar.MINUTE);

		// Get from setting
		double hourWork = 8;
		double morningStartH = 8;
		double morningStartM = 0;
		double morningEndH = 12;
		double morningEndM = 0;
		double eveningStartH = 13;
		double eveningStartM = 0;
		double eveningEndH = 17;
		double eveningEndM = 0;

		if (startHour < morningStartH) {
			startHour = morningStartH;
			startMinute = morningStartM;
		} else if (startHour >= morningEndH && startHour < eveningStartH) {
			startHour = eveningStartH;
			startMinute = eveningStartM;
		} else if (startHour >= eveningEndH) {
			startDay++;
			startHour = morningStartH;
			startMinute = morningStartM;
		}

		if (endHour >= eveningEndH) {
			endHour = eveningEndH;
			endMinute = eveningEndM;
		} else if (endHour > morningEndH && endHour < eveningStartH) {
			endHour = morningEndH;
			endMinute = morningEndM;
		} else if (endHour <= morningStartH) {
			endDay--;
			endHour = eveningEndH;
			endMinute = eveningEndM;
		}

		start.set(Calendar.DAY_OF_YEAR, (int) startDay);
		start.set(Calendar.HOUR_OF_DAY, (int) startHour);
		start.set(Calendar.MINUTE, (int) startMinute);

		end.set(Calendar.DAY_OF_YEAR, (int) endDay);
		end.set(Calendar.HOUR_OF_DAY, (int) endHour);
		end.set(Calendar.MINUTE, (int) endMinute);

		if (startDay == endDay) {
			// Start equal End
			if (!isOffDay(start)) {
				if (startHour < morningEndH && endHour > eveningStartH) {
					minute = ((morningEndH - startHour) * 60 - startMinute + morningEndM)
							+ ((endHour - eveningStartH) * 60 - eveningStartM + endMinute);
					day = minute / 60 / hourWork;
				} else {
					minute = (endHour - startHour) * 60 - startMinute + endMinute;
					day = minute / 60 / hourWork;
				}
			}
		} else {
			double minuteStartDate = 0;
			double minuteEndDate = 0;

			if (!isOffDay(start)) {
				if (startHour < morningEndH) {
					minuteStartDate = ((morningEndH - startHour) * 60 - startMinute + morningEndM)
							+ ((eveningEndH - eveningStartH) * 60 - eveningStartM + eveningEndM);
				} else {
					minuteStartDate = (endHour - startHour) * 60 - startMinute + endMinute;
				}
			}

			if (!isOffDay(end)) {
				if (endHour > eveningStartH) {
					minuteEndDate = ((endHour - eveningStartH) * 60 - eveningStartM + endMinute)
							+ ((morningEndH - morningStartH) * 60 - morningStartM + morningEndM);
				} else {
					minuteEndDate = (endHour - startHour) * 60 - startMinute + endMinute;
				}
			}

			day = (minuteStartDate + minuteEndDate) / 60 / hourWork;

			int diffDay = (int) (endDay - startDay);
			if (diffDay > 1) {
				for (int i = 1; i < diffDay; i++) {
					start.add(Calendar.DATE, 1);
					if (!isOffDay(start)) {
						day++;
					}
				}
			}
		}

		return day;
	}

	public double getWorkDay(Date startDate, Date startTime, Date endDate, Date endTime) {
		Calendar date = null;
		Calendar time = null;
		if (startTime != null) {
			date = Calendar.getInstance(zone, locale);
			time = Calendar.getInstance(zone, locale);
			date.setTime(startDate);
			time.setTime(startTime);

			date.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
			date.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
			startDate = date.getTime();
		}

		if (endDate != null) {
			date = Calendar.getInstance(zone, locale);
			time = Calendar.getInstance(zone, locale);
			date.setTime(endDate);
			time.setTime(endTime);

			date.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
			date.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
			endDate = date.getTime();
		}

		return getWorkDay(startDate, endDate);
	}

	/**
	 * Get next work day
	 * 
	 * @param startDate
	 * @param numaOfDay
	 * @return
	 */
	public Date getNextWorkDay(Date startDate, Double numOfDay) {
		Date endDate = null;
		if (numOfDay == null || numOfDay == 0) {
			return startDate;
		}

		double minute = 0;
		double minuteWorkDay = 0;
		Calendar start = Calendar.getInstance(zone, locale);
		Calendar end = Calendar.getInstance(zone, locale);
		start.setTime(startDate);

		double startDay = start.get(Calendar.DAY_OF_YEAR);
		double startHour = start.get(Calendar.HOUR_OF_DAY);
		double startMinute = start.get(Calendar.MINUTE);

		double endDay = 0;

		// Get from setting
		double hourWork = 8;
		double morningStartH = 8;
		double morningStartM = 0;
		double morningEndH = 12;
		double morningEndM = 0;
		double eveningStartH = 13;
		double eveningStartM = 0;
		double eveningEndH = 17;
		double eveningEndM = 0;

		minute = numOfDay * hourWork * 60;
		minuteWorkDay = hourWork * 60;

		if (startHour < morningStartH) {
			startHour = morningStartH;
			startMinute = morningStartM;
		} else if (startHour > morningEndH && startHour < eveningStartH) {
			startHour = eveningStartH;
			startMinute = eveningStartM;
		} else if (startHour >= eveningEndH) {
			startDay++;
			startHour = morningStartH;
			startMinute = morningStartM;
		}
		start.set(Calendar.DAY_OF_YEAR, (int) startDay);
		start.set(Calendar.HOUR_OF_DAY, (int) startHour);
		start.set(Calendar.MINUTE, (int) startMinute);

		while (isOffDay(start)) {
			start.add(Calendar.DATE, 1);
			startDay++;
			startHour = morningStartH;
			startMinute = morningStartM;
		}

		// Set again start date
		start.set(Calendar.DAY_OF_YEAR, (int) startDay);
		start.set(Calendar.HOUR_OF_DAY, (int) startHour);
		start.set(Calendar.MINUTE, (int) startMinute);

		// Get minute remain in day
		double minuteStartDate = 0;
		double minuteMorning = 0;
		double minuteEvening = 0;
		if (startHour < morningEndH) {
			minuteMorning = (morningEndH - startHour) * 60 - startMinute + morningEndM;
			minuteEvening = (eveningEndH - eveningStartH) * 60 - eveningStartM + eveningEndM;
		} else {
			minuteEvening = (eveningEndH - startHour) * 60 - startMinute + eveningEndM;
		}

		minuteStartDate = minuteMorning + minuteEvening;

		if (minute > minuteStartDate) {
			// Add day
			endDay = startDay + 1;
			minute -= minuteStartDate;
			start.add(Calendar.DATE, 1);
			while (isOffDay(start)) {
				start.add(Calendar.DATE, 1);
				endDay++;
			}

			while (minute > minuteWorkDay) {
				endDay++;
				start.add(Calendar.DATE, 1);
				if (!isOffDay(start)) {
					minute -= minuteWorkDay;
				}
			}

			// Calculate end hour
			minuteMorning = (morningEndH - morningStartH) * 60 - morningStartM + morningEndM;
			if (minute > minuteMorning) {
				// Evening
				end.set(Calendar.DAY_OF_YEAR, (int) endDay);
				end.set(Calendar.HOUR_OF_DAY, (int) eveningStartH);
				end.set(Calendar.MINUTE, (int) eveningStartM);
				// Add minute
				end.add(Calendar.MINUTE, (int) (minute - minuteMorning));
			} else {
				// Morning
				end.set(Calendar.DAY_OF_YEAR, (int) endDay);
				end.set(Calendar.HOUR_OF_DAY, (int) morningStartH);
				end.set(Calendar.MINUTE, (int) morningStartM);
				// Add minute
				end.add(Calendar.MINUTE, (int) (minute));
			}

		} else {
			// Internal day
			if (minuteMorning > 0 && minute > minuteMorning) {
				end.set(Calendar.DAY_OF_YEAR, (int) startDay);
				end.set(Calendar.HOUR_OF_DAY, (int) eveningStartH);
				end.set(Calendar.MINUTE, (int) eveningStartM);
				// Add minute
				end.add(Calendar.MINUTE, (int) (minute - minuteMorning));
			} else {
				end.set(Calendar.DAY_OF_YEAR, (int) startDay);
				end.set(Calendar.HOUR_OF_DAY, (int) startHour);
				end.set(Calendar.MINUTE, (int) startMinute);
				// Add minute
				end.add(Calendar.MINUTE, (int) (minute));
			}
		}

		endDate = end.getTime();
		return endDate;
	}

	public Date getNextWorkDay(Date startDate, Date startTime, double numaOfDay) {
		Calendar date = null;
		Calendar time = null;
		if (startTime != null) {
			date = Calendar.getInstance(zone, locale);
			time = Calendar.getInstance(zone, locale);
			date.setTime(startDate);
			time.setTime(startTime);

			date.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
			date.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
			startDate = date.getTime();
		}

		return getNextWorkDay(startDate, numaOfDay);
	}

	/**
	 * Check off day
	 * 
	 * @param date
	 * @return
	 */
	public boolean isOffDay(Calendar date) {
		if (date != null) {
			return Calendar.SATURDAY == date.get(Calendar.DAY_OF_WEEK)
					|| Calendar.SUNDAY == date.get(Calendar.DAY_OF_WEEK);
		}
		return true;
	}

}
