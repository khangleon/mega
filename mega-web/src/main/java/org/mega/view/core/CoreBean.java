package org.mega.view.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PreDestroy;
import javax.ejb.EJBException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityExistsException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.servlet.ServletContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.mega.client.LocalService;
import org.mega.client.RemoteService;
import org.mega.common.Lib;
import org.mega.dto.core.Dto;
import org.mega.dto.core.LoginInfo;
import org.mega.dto.core.MessageDto;
import org.mega.view.util.JSFUtils;
import org.mega.view.util.ServiceUtils;

public abstract class CoreBean {
	public static final String ID = "id";
	public static final String PAGE_DTO = "pagedto";
	public static final String MENU_DTO = "menudto";
	public static final String FROM_PAGE = "fromPage";
	public static final String CACHE_DATA = "cacheData";

	public static final String SUPPLIER_ID = "supplierId";
	public static final String BRAND_ID = "brandId";
	public static final String CUSTOMER_ID = "customerId";

	public static final String TAB_ACTIVE = "tabActive";
	public static final String TAB_DETAIL_ACTIVE = "tabDetailActive";
	public static final String SROLL_WIDTH = "scrollWidth";
	public static final String SROLL_HEIGHT = "scrollHeight";

	private RemoteService remote;
	private LocalService local;
	private boolean isRemote = Lib.toBoolean(Lib.getSetting("ejb.app.remote"), false);
	protected LoginInfo loginInfo;
	protected MessageDto messageDto;
	protected Locale locale;
	protected TimeZone zone;

	protected boolean isCache = false;

	public CoreBean() {
		try {
			loginInfo = JSFUtils.findBean("loginInfo");
			messageDto = JSFUtils.findBean("messagedto");
			locale = loginInfo.getLocale();
			zone = loginInfo.getZone();

			String nameService = getPageCode() + "Service";
			if (isRemote) {
				remote = (RemoteService) ServiceUtils.getService(nameService, RemoteService.class);
			} else {
				local = (LocalService) ServiceUtils.getService(nameService, LocalService.class);
			}
		} catch (Exception e) {

		}
	}

	public abstract String getPageCode();

	public String getBussinessCode() {
		return null;
	}

	public String getTitle() {
		return Lib.getResource(null, getBussinessCode() + "title");
	}

	/**
	 * Get current url
	 * 
	 * @return
	 */
	public String getUrl() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ectx = context.getExternalContext();
		StringBuffer url = new StringBuffer(ectx.getRequestScheme());
		url.append("://");
		url.append(ectx.getRequestServerName());
		url.append(":");
		url.append(ectx.getRequestServerPort());
		url.append(ectx.getRequestContextPath());
		url.append(ectx.getRequestServletPath());
		return url.toString();
	}

	@PreDestroy
	public void release() {
		System.out.println("Destroy View Bean " + getPageCode());

		// Release dto
		// JSFUtils.setBean(getPageCode() + "dto", null);
	}

	/**
	 * Get dto of page
	 * 
	 * @return
	 */
	public <D> D getDto() throws Exception {
		D dto = JSFUtils.findBean(getPageCode() + "dto");
		isCache = isCacheDto();
		if (isCache) {
			D dtoCache = getCacheDto();
			if (dtoCache != null) {
				BeanUtils.copyProperties(dto, dtoCache);
				if (dto instanceof Dto) {
					putParamPage(ID, ((Dto) dto).getId());
					putParamPage(FROM_PAGE, ((Dto) dto).getFromPage());
				}
			}
		}

		if (dto instanceof Dto) {
			((Dto) dto).setBusinessCode(getBussinessCode());
			((Dto) dto).setPageCode(getPageCode());

			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ServletContext context = (ServletContext) ec.getContext();
			String reportPath = Lib.getSetting("report.path.template");
			if (Lib.isEmpty(reportPath)) {
				reportPath = context.getRealPath("WEB-INF/classes/report");
			}
			((Dto) dto).setReportPath(reportPath);
			((Dto) dto).setLocale(locale);

			((Dto) dto).rendered(loginInfo.getRendered(getBussinessCode()), true);
			((Dto) dto).disabled(loginInfo.getDisabled(getBussinessCode()), true);
			((Dto) dto).hidden(loginInfo.getHidden(getBussinessCode()), true);
			((Dto) dto).readOnly(loginInfo.getReadOnly(getBussinessCode()), true);
		}
		return dto;
	}

	public <D> D getDto(String name) {
		D dto = JSFUtils.findBean(name);
		if (dto instanceof Dto) {
			((Dto) dto).setBusinessCode(getBussinessCode());
			((Dto) dto).setPageCode(getPageCode());
		}
		return dto;
	}

	public void fromCacheDto(boolean cache) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put(CACHE_DATA, cache);
	}

	public boolean isCacheDto() {
		FacesContext context = FacesContext.getCurrentInstance();
		return Lib.toBoolean(context.getExternalContext().getSessionMap().remove(CACHE_DATA), false);
	}

	/**
	 * Get dto from cache
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <D> D getCacheDto() {
		FacesContext context = FacesContext.getCurrentInstance();
		D dto = (D) context.getExternalContext().getSessionMap().remove(getPageCode() + "_DTO_CACHE");
		return dto;
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
		if (value == null) {
			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			value = (T) params.get(key);
		}
		return value;
	}

	/**
	 * Pass parameter to next page
	 * 
	 * @param key
	 * @param value
	 */
	public <T> void putParamPage(String key, T value) {
		HashMap<String, Object> paramPage = JSFUtils.findBean("paramPage");
		paramPage.put(key, value);
	}

	/**
	 * Put dto to cache
	 * 
	 * @throws Exception
	 */
	public void putCacheDto() throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put(getPageCode() + "_DTO_CACHE", getDto());
	}

	public <E> E findEntity(Class<E> entityClass, Object primaryKey) {
		E entity = null;
		if (isRemote) {
			entity = remote.findEntity(entityClass, primaryKey);
		} else {
			entity = local.findEntity(entityClass, primaryKey);
		}
		return entity;
	}

	/**
	 * Call service
	 * 
	 * @param serviceName
	 * @param dtos
	 * @throws Exception
	 */
	public void doService(String serviceName, Object... dtos) throws Exception {
		long t1 = System.currentTimeMillis();
		Object[] serviceResult = null;
		if (isRemote) {
			serviceResult = remote.doService(loginInfo, serviceName, dtos);
		} else {
			serviceResult = local.doService(loginInfo, serviceName, dtos);
		}

		// Process service result
		if (isRemote) {
			if (dtos != null && serviceResult != null) {
				int len1 = dtos.length;
				int len2 = serviceResult.length;
				int len = len1 > len2 ? len1 : len2;
				BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
				beanUtilsBean.getConvertUtils().register(false, true, -1);
				for (int i = 0; i < len; i++) {
					if (serviceResult[i] != null && dtos[i] != null) {
						BeanUtils.copyProperties(dtos[i], serviceResult[i]);
					}
				}

				if (len2 > 0) {
					Object dto = serviceResult[0];
					if (dto != null && dto instanceof Dto) {
						messageDto.copyMessages(((Dto) dto).getMessageDto());
					}
				}
			}
		}
		long t2 = System.currentTimeMillis();
		System.out.println("doService: " + serviceName + " in " + (t2 - t1) + " ms");
	}

	public void doAction() throws Exception {
		messageDto.clearMessages();
	}

	/**
	 * Handle action from view
	 * 
	 * @param actionName
	 * @return
	 * @throws Exception
	 */
	public String doAction(String actionName) throws Exception {
		long t1 = System.currentTimeMillis();
		messageDto.clearMessages();
		Method actionMethod = null;
		String ret = null;
		try {
			try {
				actionMethod = this.getClass().getMethod(actionName);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				throw e;
			}

			if (actionMethod != null) {
				ret = (String) actionMethod.invoke(this);
			}
		} catch (Exception e) {
			e = processException(e);
			if (e != null) {
				throw e;
			}
		}
		long t2 = System.currentTimeMillis();
		int length = FacesContext.getCurrentInstance().getExternalContext().getRequestContentLength();
		System.out.println("doAction: " + actionName + " in " + (t2 - t1) + " ms. Request Content Length: " + length
				+ " byte");
		return ret;
	}

	public String doAction(String actionName, String param) throws Exception {
		messageDto.clearMessages();

		Method actionMethod = null;
		String ret = null;
		try {
			try {
				actionMethod = this.getClass().getMethod(actionName, String.class);
			} catch (NoSuchMethodException e) {
				throw e;
			}

			if (actionMethod != null) {
				ret = (String) actionMethod.invoke(this, param);
			}
		} catch (Exception e) {
			e = processException(e);
			if (e != null) {
				throw e;
			}
		}
		return ret;
	}

	public String doAction(String actionName, String param1, String param2) throws Exception {
		messageDto.clearMessages();

		Method actionMethod = null;
		String ret = null;
		try {
			try {
				actionMethod = this.getClass().getMethod(actionName, String.class, String.class);
			} catch (NoSuchMethodException e) {
				throw e;
			}

			if (actionMethod != null) {
				ret = (String) actionMethod.invoke(this, param1, param2);
			}
		} catch (Exception e) {
			e = processException(e);
			if (e != null) {
				throw e;
			}
		}
		return ret;
	}

	private Exception processException(Exception e) {
		e.printStackTrace();

		Exception exp = null;
		Throwable throwable = null;
		if (e instanceof InvocationTargetException) {
			throwable = ((InvocationTargetException) e).getTargetException();
			if (throwable instanceof Exception) {
				e = (Exception) throwable;
			} else {
				e = new Exception(throwable.getMessage(), throwable);
			}
		}

		if (throwable instanceof EJBException) {
			throwable = throwable.getCause();
			if (throwable instanceof OptimisticLockException) {
				messageDto.showError(Lib.getMessage(locale, "EM9903"));
			} else if (throwable instanceof EntityExistsException) {
				messageDto.showError(Lib.getMessage(locale, "EM9901", "-803"));
			} else {
				SQLException sqlException = checkSQLException(throwable);
				if (sqlException != null) {
					messageDto.showFatal(getMessageSQLException(sqlException));
				} else {
					Throwable t = throwable;
					while (t != null) {
						messageDto.showFatal("Cause: " + t + ". Message: " + t.getMessage());
						t = t.getCause();
					}
				}
			}
		} else if (throwable instanceof PersistenceException) {
			if (throwable instanceof RollbackException) {
				throwable = throwable.getCause();
			}

			if (throwable instanceof OptimisticLockException) {
				messageDto.showError(Lib.getMessage(locale, "EM9903"));
			} else if (throwable instanceof EntityExistsException) {
				messageDto.showError(Lib.getMessage(locale, "EM9901", "-803"));
			} else {
				SQLException sqlException = checkSQLException(throwable);
				if (sqlException != null) {
					messageDto.showFatal(getMessageSQLException(sqlException));
				} else {
					Throwable t = throwable;
					while (t != null) {
						messageDto.showFatal("Cause: " + t + ". Message: " + t.getMessage());
						t = t.getCause();
					}
				}
			}
		} else {
			SQLException sqlException = checkSQLException(throwable);
			if (sqlException != null) {
				messageDto.showFatal(getMessageSQLException(sqlException));
			} else {
				Throwable t = throwable;
				while (t != null) {
					messageDto.showFatal("Cause: " + t + ". Message: " + t.getMessage());
					t = t.getCause();
				}
			}
		}

		return exp;
	}

	/**
	 * Get SQLException if exist.
	 * 
	 * @param t
	 * @return
	 */
	private SQLException checkSQLException(Throwable t) {
		Throwable temp = t;
		while (temp != null) {
			if (temp instanceof SQLException) {
				return (SQLException) temp;
			}
			temp = temp.getCause();
		}
		return null;
	}

	/**
	 * Get message SQLException
	 * 
	 * @param ex
	 * @return
	 */
	private String getMessageSQLException(SQLException ex) {
		String message = "";
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				String sqlState = ((SQLException) e).getSQLState();
				int errorCode = ((SQLException) e).getErrorCode();
				if (!ignoreSQLException(sqlState)) {
					if (sqlState == null) {
						message = "The SQL state is not defined!";
					} else {
						if (errorCode == -803) {
							// Duplicate PK
							message = Lib.getMessage(locale, "EM9901", errorCode);
						} else if (errorCode == 100) {
							// No row data for delete
							message = Lib.getMessage(locale, "EM9902", errorCode);
						} else {
							// Other error
							message = Lib.getMessage(locale, "EM9900", sqlState, errorCode, e.getMessage());
						}
						break;
					}
				}
			}
		}
		return message;
	}

	/**
	 * Check SQLException
	 * 
	 * @param sqlState
	 * @return
	 */
	private static boolean ignoreSQLException(String sqlState) {
		if (sqlState == null) {
			return false;
		}

		// X0Y32: Jar file already exists in schema
		if (sqlState.equalsIgnoreCase("X0Y32")) {
			return true;
		}

		// 42Y55: Table already exists in schema
		if (sqlState.equalsIgnoreCase("42Y55")) {
			return true;
		}

		return false;
	}

}
