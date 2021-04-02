package org.mega.view.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.mega.client.LocalService;
import org.mega.client.ParamQuery;
import org.mega.client.RemoteService;
import org.mega.common.Lib;
import org.mega.dto.core.LoginInfo;
import org.mega.view.util.ServiceUtils;

/**
 * 
 * @author naphong
 *
 */
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = -9170378342561081693L;
	private RemoteService remote;
	private LocalService local;
	private boolean isRemote = Lib.toBoolean(Lib.getSetting("ejb.app.remote"), false);
	protected LoginInfo loginInfo;
	protected Locale locale;
	protected TimeZone zone;

	public BaseServlet() {
		super();
		String nameService = "entityService";
		if (isRemote) {
			remote = (RemoteService) ServiceUtils.getService(nameService, RemoteService.class);
		} else {
			local = (LocalService) ServiceUtils.getService(nameService, LocalService.class);
		}
	}

	/**
	 * 
	 * @param serviceName
	 * @param dtos
	 * @throws Exception
	 */
	public void doService(String serviceName, Object... dtos) throws Exception {
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
				for (int i = 0; i < len; i++) {
					if (serviceResult[i] != null && dtos[i] != null) {
						BeanUtils.copyProperties(dtos[i], serviceResult[i]);
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param entityClass
	 * @param primaryKey
	 * @return
	 */
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
	 * 
	 * @param paramQuery
	 * @return
	 */
	public <E> E getEntity(ParamQuery paramQuery) {
		E result = null;
		if (isRemote) {
			result = remote.getEntity(paramQuery);
		} else {
			result = local.getEntity(paramQuery);
		}
		return result;
	}

	/**
	 * 
	 * @param paramQuery
	 * @return
	 */
	public <E> List<E> getEntities(ParamQuery paramQuery) {
		List<E> result = new ArrayList<E>();
		if (isRemote) {
			result = remote.getEntities(paramQuery);
		} else {
			result = local.getEntities(paramQuery);
		}
		return result;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession httpSession = req.getSession(false);
		loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
		zone = loginInfo.getZone();
		locale = loginInfo.getLocale();
		doPostGet(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession httpSession = req.getSession(false);
		loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
		doPostGet(req, res);
	}

	protected abstract void doPostGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,
			IOException;
}
