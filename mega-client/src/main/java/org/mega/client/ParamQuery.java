package org.mega.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ParamQuery implements Serializable {
	private static final long serialVersionUID = -1519818102421653940L;

	/**
	 * Refers to the query when using the EntityManager methods that create
	 * query objects.
	 */
	private String name;

	/** Native sql */
	private String sql;

	/** The class of the result. */
	private Class<?> resultClass;

	/** Bind an argument to a named parameter. */
	private Map<String, Object> param;

	/** The start position of the first result, numbered from 0 */
	private int startPosition;

	/** Maximum number of results to retrieve. */
	private int maxResult;

	private boolean clear = true;

	private boolean checkMaxRow = false;

	private boolean first = false;

	public ParamQuery() {
		param = new HashMap<String, Object>();
	}

	public void addParam(String key, Object value) {
		param.put(key, value);
	}

	public Object removeParam(String key) {
		if (param == null) {
			return null;
		}

		return param.remove(key);
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	private boolean last = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Class<?> getResultClass() {
		return resultClass;
	}

	public void setResultClass(Class<?> resultClass) {
		this.resultClass = resultClass;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public boolean isClear() {
		return clear;
	}

	public void setClear(boolean clear) {
		this.clear = clear;
	}

	public boolean isCheckMaxRow() {
		return checkMaxRow;
	}

	public void setCheckMaxRow(boolean checkMaxRow) {
		this.checkMaxRow = checkMaxRow;
	}

	/**
	 * Format search string
	 * 
	 * @param src
	 * @return
	 */
	public static String wildCardLike(String query) {
		return wildCardLike(query, false);
	}

	public static String wildCardLike(String query, boolean sensitive) {
		if (query == null || "".equals(query)) {
			return "%";
		}
		query = sensitive ? query : query.toLowerCase();
		query = query.replace("%", "!%");
		query = query.replace("_", "!_");
		return "%".concat(query).concat("%");
	}
}
