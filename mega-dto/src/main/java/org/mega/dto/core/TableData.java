package org.mega.dto.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.mega.dao.core.DaoEntity;

public class TableData implements Serializable {
	private static final long serialVersionUID = -4074855600304724803L;
	public static final String ALL = "All";
	private int pageSize = 50;
	private int pageCount = 0;
	private String pageIndex = "1"; // Index of page from 1
	private int rowBegin = 0;
	private int rowEnd = 0;
	private int rowCount = 0; // Number of row in table
	private int rowIndex = 0; // Display on table
	private int rowSelected = 0; // Row checked
	private boolean allSelected = false;
	private boolean disablePrev = false;
	private boolean disableNext = false;
	private boolean disablePage = false;

	private List<?> data;
	private List<String> pageList;

	public TableData() {

	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		calPageSize();
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getRowBegin() {
		return rowBegin;
	}

	public void setRowBegin(int rowBegin) {
		this.rowBegin = rowBegin;
	}

	public int getRowEnd() {
		return rowEnd;
	}

	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getRowSelected() {
		return rowSelected;
	}

	public void setRowSelected(int rowSelected) {
		this.rowSelected = rowSelected;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
		calPageSize();
	}
	
	public void calPageSize() {
		if (data != null && !data.isEmpty()) {
			rowCount = data.size();
			pageCount = (int) Math.ceil((double) rowCount / pageSize);
			DaoEntity e = null;
			if (data.get(0) instanceof DaoEntity) {
				for (int i = 0; i < rowCount; i++) {
					e = (DaoEntity) data.get(i);
					e.setIndex(i + 1);
				}
			}
		} else {
			rowCount = 0;
			pageCount = 0;
		}

		disablePrev = true;
		if (pageCount < 2) {
			disableNext = true;
			disablePage = true;
		} else {
			disableNext = false;
			disablePage = false;
		}

		pageList = new ArrayList<String>();
		for (int i = 0; i < pageCount; i++) {
			pageList.add("" + (i + 1));
		}
		pageList.add(ALL);
	}

	public boolean isAllSelected() {
		return allSelected;
	}

	public void setAllSelected(boolean allSelected) {
		this.allSelected = allSelected;
	}

	public boolean isDisablePrev() {
		return disablePrev;
	}

	public void setDisablePrev(boolean disablePrev) {
		this.disablePrev = disablePrev;
	}

	public boolean isDisableNext() {
		return disableNext;
	}

	public void setDisableNext(boolean disableNext) {
		this.disableNext = disableNext;
	}

	public boolean isDisablePage() {
		return disablePage;
	}

	public void setDisablePage(boolean disablePage) {
		this.disablePage = disablePage;
	}

	public List<?> getPageData(int pageSelected) {
		List<Object> dataDisplay = new ArrayList<Object>();
		int fromIndex;
		int toIndex;
		if (pageSelected < this.pageCount) {
			fromIndex = pageSelected * this.pageSize;
			if ((fromIndex + this.pageSize) > this.rowCount) {
				toIndex = this.rowCount;
			} else {
				toIndex = fromIndex + this.pageSize;
			}

			for (int i = fromIndex; i < toIndex; i++) {
				dataDisplay.add(this.data.get(i));
			}

			this.rowBegin = fromIndex + 1;
			this.rowEnd = toIndex;
			this.pageIndex = "" + (pageSelected + 1);
			// this.pageSelected = pageSelected;
		}

		if (this.pageCount < 2) {
			disableNext = true;
			disablePrev = true;
		} else {
			disablePrev = pageSelected == 0;
			disableNext = pageSelected == (this.pageCount - 1);
		}

		return dataDisplay;
	}

	public List<?> getPageData() {
		int pageSelected = 0;
		if (ALL.equals(pageIndex)) {
			return allPageData();
		} else {
			pageSelected = (pageIndex == null || "".equals(pageIndex.trim())) ? 1 : Integer.valueOf(pageIndex.trim());
			pageSelected--;
		}
		return getPageData(pageSelected);
	}

	public List<?> nextPageData() {
		int pageSelected = 0;
		if (ALL.equals(pageIndex)) {
			return allPageData();
		} else {
			pageSelected = (pageIndex == null || "".equals(pageIndex.trim())) ? 0 : Integer.valueOf(pageIndex.trim());
			if (pageSelected == this.pageCount) {
				pageSelected--;
			}
		}
		return getPageData(pageSelected);
	}

	public List<?> prevPageData() {
		int pageSelected = 0;
		if (ALL.equals(pageIndex)) {
			return allPageData();
		} else {
			pageSelected = (pageIndex == null || "".equals(pageIndex.trim())) ? 0 : Integer.valueOf(pageIndex.trim());
			if (pageSelected > 1) {
				pageSelected -= 2;
			} else {
				pageSelected = 0;
			}
		}

		return getPageData(pageSelected);
	}

	public List<?> firstPageData() {
		return getPageData(0);
	}

	public List<?> lastPageData() {
		return getPageData(this.pageCount - 1);
	}

	public List<?> allPageData() {
		this.rowBegin = 1;
		this.rowEnd = this.rowCount;
		this.pageIndex = ALL;
		this.disableNext = true;
		this.disablePrev = true;
		return this.data;
	}

	public List<String> getPageList() {
		return pageList;
	}

	public void setPageList(List<String> pageList) {
		this.pageList = pageList;
	}

}
