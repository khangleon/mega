package org.mega.dao.core;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Transient;

import net.sf.json.JSONObject;

public abstract class DaoEntity implements Serializable {
	private static final long serialVersionUID = 985462071838620953L;

	public static Integer DELETED = 1;
	public static String EMPTY = "";

	public static String STYLE_ERROR = "error";

	public abstract Long getId();

	public abstract void setId(Long id);

	public abstract boolean isDeleted();

	public abstract void setDeleted(boolean deleted);

	public abstract Long getDeletedId();

	public abstract void setDeletedId(Long deletedId);

	public abstract Date getDeletedDate();

	public abstract void setDeletedDate(Date deletedDate);

	public abstract Long getCreatedId();

	public abstract void setCreatedId(Long createdId);

	public abstract Date getCreatedDate();

	public abstract void setCreatedDate(Date createdDate);

	public abstract Long getUpdatedId();

	public abstract void setUpdatedId(Long updateId);

	public abstract Date getUpdatedDate();

	public abstract void setUpdatedDate(Date updatedDate);

	public abstract Integer getVersion();

	public abstract void setVersion(Integer version);

	public abstract boolean isSelected();

	public abstract void setSelected(boolean selected);

	public abstract int getIndex();

	public abstract void setIndex(int index);

	@Transient
	private boolean rowError = false;

	public boolean isRowError() {
		return rowError;
	}

	public void setRowError(boolean rowError) {
		this.rowError = rowError;
	}

	public String getRowStyle() {
		String style = "";
		if (isRowError()) {
			style = STYLE_ERROR;
		}
		return style;
	}

	private static HashMap<String, String[]> entityFields = new HashMap<String, String[]>();
	private static HashMap<String, Method[]> entityMethods = new HashMap<String, Method[]>();

	public String getRowData() {
		String className = this.getClass().getSimpleName();
		String[] fieldNames = entityFields.get(className);
		Method[] methods = entityMethods.get(className);
		if (fieldNames == null) {
			getMethods();
			fieldNames = entityFields.get(className);
			methods = entityMethods.get(className);
			if (fieldNames == null) {
				return "{}";
			}
		}
		JSONObject json = new JSONObject();
		Object value = null;
		for (int i = 0; i < fieldNames.length; i++) {
			try {
				value = methods[i].invoke(this);
				json.put(fieldNames[i], value);
			} catch (Exception e) {
				continue;
			}
		}
		return json.toString();
	}

	private void getMethods() {
		Class<?> clazz = this.getClass();
		try {
			ArrayList<String> fieldNames = new ArrayList<String>();
			ArrayList<Method> methods = new ArrayList<Method>();

			Field[] fields = clazz.getDeclaredFields();
			String className = clazz.getSimpleName();
			String fieldName = "";
			String methodName = null;
			Method method = null;
			for (Field field : fields) {
				try {
					if (field.getAnnotation(Column.class) != null) {
						fieldName = field.getName();
						methodName = fieldName.substring(0, 1).toUpperCase().concat(fieldName.substring(1));
						try {
							method = clazz.getMethod("get" + methodName);
						} catch (NoSuchMethodException e) {
							try {
								method = clazz.getMethod("is" + methodName);
							} catch (NoSuchMethodException f) {
								method = null;
							}
						}

						if (fieldName != null && method != null) {
							fieldNames.add(fieldName);
							methods.add(method);
						}
					}
				} catch (Exception e) {
					continue;
				}
			}
			entityFields.put(className, fieldNames.toArray(new String[fieldNames.size()]));
			entityMethods.put(className, methods.toArray(new Method[methods.size()]));
		} catch (Exception e) {
			return;
		}
	}

}
