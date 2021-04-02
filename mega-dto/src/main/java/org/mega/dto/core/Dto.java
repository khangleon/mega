package org.mega.dto.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.mega.common.Lib;
import org.mega.constant.Constant;
import org.mega.dao.entity.BusinessFlow;

public class Dto implements Serializable {
	private static final long serialVersionUID = -3241330330336844273L;

	public static final String EMPTY_JSON = "{}";
	public static final String ALL = "all";

	// private String locale = "vi_VN";
	private Locale locale = Locale.getDefault();
	private MessageDto messageDto;

	private String fromPage;
	private String pageCode;
	private String businessCode;
	private String businessName;
	private List<BusinessFlow> nextBusinessList;
	private String nextBusinessCode;
	private String nextBusinessName;
	private Long nextBusinessId;
	private boolean nextBusinessAddMore = false;
	private boolean nextBusinessView = false;
	private Integer nextStage;
	private String prevBusinessCode;
	private String prevBusinessName;
	private Long prevBusinessId;
	private String standardAction = Constant.EXPORT_PDF;
	private List<SelectItem> exportTypeItems;

	private Long id;
	private String statusName;
	private String tabActive;
	private String tabDetailActive;
	private String reportPath;
	private byte[] dataExport;

	private Long idSelected;
	private int rowSelected;
	private int rowChanged;

	private Map<String, Boolean> renderedLogin;
	private Map<String, Boolean> disabledLogin;
	private Map<String, Boolean> hiddenLogin;
	private Map<String, Boolean> readOnlyLogin;

	private Map<String, Boolean> rendered;
	private Map<String, Boolean> disabled;
	private Map<String, Boolean> hidden;
	private Map<String, Boolean> readOnly;

	public Dto() {
		this.rendered = new HashMap<String, Boolean>();
		this.disabled = new HashMap<String, Boolean>();
		this.hidden = new HashMap<String, Boolean>();
		this.readOnly = new HashMap<String, Boolean>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public MessageDto getMessageDto() {
		return messageDto;
	}

	public void setMessageDto(MessageDto messageDto) {
		this.messageDto = messageDto;
	}

	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getBusinessCode() {
		return businessCode == null ? "" : businessCode;
	}

	public void setBusinessCode(String businessStep) {
		this.businessCode = businessStep;
	}

	public String getBusinessName() {
		return businessName == null ? "" : businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessStage(Integer stage) {
		return Lib.getResource(getLocale(), "business.stage." + stage);
	}

	public List<BusinessFlow> getNextBusinessList() {
		return nextBusinessList == null ? new ArrayList<BusinessFlow>() : nextBusinessList;
	}

	public void setNextBusinessList(List<BusinessFlow> nextBusinessList) {
		this.nextBusinessList = nextBusinessList;
	}

	public String getNextBusinessCode() {
		return nextBusinessCode == null ? "" : nextBusinessCode;
	}

	public void setNextBusinessCode(String nextBusinessCode) {
		this.nextBusinessCode = nextBusinessCode;
	}

	public String getNextBusinessName() {
		return nextBusinessName == null ? "" : nextBusinessName;
	}

	public void setNextBusinessName(String nextBusinessName) {
		this.nextBusinessName = nextBusinessName;
	}

	public Long getNextBusinessId() {
		return nextBusinessId;
	}

	public void setNextBusinessId(Long nextBusinessId) {
		this.nextBusinessId = nextBusinessId;
	}

	public boolean isNextBusinessAddMore() {
		return nextBusinessAddMore;
	}

	public void setNextBusinessAddMore(boolean nextBusinessAddMore) {
		this.nextBusinessAddMore = nextBusinessAddMore;
	}

	public boolean isNextBusinessView() {
		return nextBusinessView;
	}

	public void setNextBusinessView(boolean nextBusinessView) {
		this.nextBusinessView = nextBusinessView;
	}

	public Integer getNextStage() {
		return nextStage;
	}

	public void setNextStage(Integer nextStage) {
		this.nextStage = nextStage;
	}

	public String getPrevBusinessCode() {
		return prevBusinessCode == null ? "" : prevBusinessCode;
	}

	public void setPrevBusinessCode(String prevBusinessCode) {
		this.prevBusinessCode = prevBusinessCode;
	}

	public String getPrevBusinessName() {
		return prevBusinessName == null ? "" : prevBusinessName;
	}

	public void setPrevBusinessName(String prevBusinessName) {
		this.prevBusinessName = prevBusinessName;
	}

	public Long getPrevBusinessId() {
		return prevBusinessId;
	}

	public void setPrevBusinessId(Long prevBusinessId) {
		this.prevBusinessId = prevBusinessId;
	}

	public String getStandardAction() {
		return standardAction;
	}

	public void setStandardAction(String standardAction) {
		this.standardAction = standardAction;
	}

	public List<SelectItem> getExportTypeItems() {
		if (exportTypeItems == null) {
			exportTypeItems = new ArrayList<SelectItem>();
			exportTypeItems.add(new SelectItem(Constant.EXPORT_PDF, Lib.getResource(getLocale(),
					"common.combo.exportpdf"), Lib.getResource(getLocale(), "common.combo.exportpdf"), false));
			exportTypeItems.add(new SelectItem(Constant.EXPORT_XLSX, Lib.getResource(getLocale(),
					"common.combo.exportexcelx"), Lib.getResource(getLocale(), "common.combo.exportexcelx"), false));
			exportTypeItems.add(new SelectItem(Constant.EXPORT_CSV, Lib.getResource(getLocale(),
					"common.combo.exportcsv"), Lib.getResource(getLocale(), "common.combo.unsupport"), true));
			exportTypeItems.add(new SelectItem(Constant.EXPORT_DOCX, Lib.getResource(getLocale(),
					"common.combo.exportdocx"), Lib.getResource(getLocale(), "common.combo.exportdocx"), false));
			exportTypeItems.add(new SelectItem(Constant.EXPORT_IMAGE, Lib.getResource(getLocale(),
					"common.combo.exportimage"), Lib.getResource(getLocale(), "common.combo.unsupport"), true));
			exportTypeItems.add(new SelectItem(Constant.EXPORT_HTML, Lib.getResource(getLocale(),
					"common.combo.exporthtml"), Lib.getResource(getLocale(), "common.combo.exporthtml"), false));
			exportTypeItems.add(new SelectItem(Constant.EXPORT_RTF, Lib.getResource(getLocale(),
					"common.combo.exportrtf"), Lib.getResource(getLocale(), "common.combo.unsupport"), true));
		}
		return exportTypeItems;
	}

	public void setExportTypeItems(List<SelectItem> exportTypeItems) {
		this.exportTypeItems = exportTypeItems;
	}

	public boolean isDisableNextBusiness() {
		return !(this.id != null && this.id > 0);
	}

	public boolean isDisablePrevBusiness() {
		return !(this.prevBusinessId != null && this.prevBusinessId > 0);
	}

	public String getTabActive() {
		return tabActive;
	}

	public void setTabActive(String tabActive) {
		this.tabActive = tabActive;
	}

	public String getTabDetailActive() {
		return tabDetailActive;
	}

	public void setTabDetailActive(String tabDetailActive) {
		this.tabDetailActive = tabDetailActive;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public byte[] getDataExport() {
		return dataExport;
	}

	public void setDataExport(byte[] dataExport) {
		this.dataExport = dataExport;
	}

	public Long getIdSelected() {
		return idSelected;
	}

	public void setIdSelected(Long idSelected) {
		this.idSelected = idSelected;
	}

	public int getRowSelected() {
		return rowSelected;
	}

	public void setRowSelected(int rowSelected) {
		this.rowSelected = rowSelected;
	}

	public int getRowChanged() {
		return rowChanged;
	}

	public void setRowChanged(int rowChanged) {
		this.rowChanged = rowChanged;
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

	public String getTimeUnitName(String value) {
		return Lib.getResource(getLocale(), "unit.time.".concat(value));
	}

	public String getLevelData(Integer value) {
		return Lib.getResource(getLocale(), "level.data.".concat("" + value));
	}

	public Map<String, Boolean> getRendered() {
		return rendered;
	}

	public void setRendered(Map<String, Boolean> rendered) {
		this.rendered = rendered;
	}

	public Map<String, Boolean> getDisabled() {
		return disabled;
	}

	public void setDisabled(Map<String, Boolean> disabled) {
		this.disabled = disabled;
	}

	public Map<String, Boolean> getHidden() {
		return hidden;
	}

	public void setHidden(Map<String, Boolean> hidden) {
		this.hidden = hidden;
	}

	public Map<String, Boolean> getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Map<String, Boolean> readOnly) {
		this.readOnly = readOnly;
	}

	public boolean rendered(String id) {
		Boolean value = this.rendered.get(id);
		return value != null ? value.booleanValue() : true;
	}

	public void rendered(boolean rendered, String... ids) {
		if (ids != null) {
			Boolean value = null;
			for (String id : ids) {
				value = this.renderedLogin.get(id);
				value = value == null ? true : value;
				this.rendered.put(id, rendered && value);
			}
		}
	}

	public void rendered(Map<String, Boolean> rendered) {
		if (rendered != null) {
			this.rendered.putAll(rendered);
		}
	}

	public void rendered(Map<String, Boolean> rendered, boolean clear) {
		rendered = rendered == null ? new HashMap<String, Boolean>() : rendered;
		if (clear) {
			this.rendered.clear();
			this.renderedLogin = rendered;
		}
		this.rendered.putAll(rendered);
	}

	public boolean disabled(String id) {
		Boolean value = this.disabled.get(id);
		return value != null ? value.booleanValue() : false;
	}

	public void disabled(boolean disabled, String... ids) {
		if (ids != null) {
			Boolean value = null;
			for (String id : ids) {
				value = this.disabledLogin.get(id);
				value = value == null ? false : value;
				this.disabled.put(id, disabled || value);
			}
		}
	}

	public void disabled(Map<String, Boolean> disabled) {
		if (disabled != null) {
			this.disabled.putAll(disabled);
		}
	}

	public void disabled(Map<String, Boolean> disabled, boolean clear) {
		disabled = disabled == null ? new HashMap<String, Boolean>() : disabled;
		if (clear) {
			this.disabled.clear();
			this.disabledLogin = disabled;
		}
		this.disabled.putAll(disabled);
	}

	public boolean hidden(String id) {
		Boolean value = this.hidden.get(id);
		return value != null ? value.booleanValue() : false;
	}

	public void hidden(boolean hidden, String... ids) {
		if (ids != null) {
			Boolean value = null;
			for (String id : ids) {
				value = this.hiddenLogin.get(id);
				value = value == null ? false : value;
				this.hidden.put(id, hidden || value);
			}
		}
	}

	public void hidden(Map<String, Boolean> hidden) {
		if (hidden != null) {
			this.hidden.putAll(hidden);
		}
	}

	public void hidden(Map<String, Boolean> hidden, boolean clear) {
		hidden = hidden == null ? new HashMap<String, Boolean>() : hidden;
		if (clear) {
			this.hidden.clear();
			this.hiddenLogin = hidden;
		}
		this.hidden.putAll(hidden);
	}

	public boolean readOnly(String id) {
		Boolean value = this.readOnly.get(id);
		return value != null ? value.booleanValue() : false;
	}

	public void readOnly(boolean readOnly, String... ids) {
		if (ids != null) {
			Boolean value = null;
			for (String id : ids) {
				value = this.readOnlyLogin.get(id);
				value = value == null ? false : value;
				this.readOnly.put(id, readOnly || value);
			}
		}
	}

	public void readOnly(Map<String, Boolean> readOnly) {
		if (readOnly != null) {
			this.readOnly.putAll(readOnly);
		}
	}

	public void readOnly(Map<String, Boolean> readOnly, boolean clear) {
		readOnly = readOnly == null ? new HashMap<String, Boolean>() : readOnly;
		if (clear) {
			this.readOnly.clear();
			this.readOnlyLogin = readOnly;
		}
		this.readOnly.putAll(readOnly);
	}
}
