package org.mega.dto.core;

import java.io.Serializable;
import java.util.List;

public abstract class TableDto extends Dto implements Serializable {
	private static final long serialVersionUID = -5418808281986506361L;
	protected TableData tableData;
	private String pageSelected;
	private String quickSearch;
	private boolean isSearchQuick = true;
	private int splitterSearchPosition = 0;

	public TableDto() {
		super();
	}

	public TableData getTableData() {
		if (this.tableData == null) {
			this.tableData = new TableData();
		}
		return this.tableData;
	}

	public void setTableData(TableData tableData) {
		this.tableData = tableData;
	}

	/**
	 * Set data to cache
	 * 
	 * @param data
	 */
	public void setTableData(List<?> data) {
		if (this.tableData == null) {
			this.tableData = new TableData();
		}

		this.tableData.setData(data);
	}

	/**
	 * Set data for display
	 * 
	 * @param data
	 */
	abstract public void setDataTable(List<?> data);

	public String getPageSelected() {
		return pageSelected;
	}

	public void setPageSelected(String pageSelected) {
		this.pageSelected = pageSelected;
	}

	public String getQuickSearch() {
		return quickSearch;
	}

	public void setQuickSearch(String quickSearch) {
		this.quickSearch = quickSearch;
	}

	public boolean isSearchQuick() {
		return isSearchQuick;
	}

	public void setSearchQuick(boolean isSearchQuick) {
		this.isSearchQuick = isSearchQuick;
	}

	public int getSplitterSearchPosition() {
		return splitterSearchPosition;
	}

	public void setSplitterSearchPosition(int splitterSearchPosition) {
		this.splitterSearchPosition = splitterSearchPosition;
	}

}
