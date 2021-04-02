package org.mega.view.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.mega.client.LocalService;
import org.mega.client.RemoteService;
import org.mega.common.Lib;
import org.mega.dto.core.LoginDto;
import org.mega.dto.core.LoginInfo;
import org.mega.view.util.ServiceUtils;

public class SessionTimeoutListener implements HttpSessionListener {

	public SessionTimeoutListener() {
		super();
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("SessionTimeoutListener sessionCreated: " + se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("SessionTimeoutListener sessionDestroyed: " + se.getSession().getId());
		try {
			LoginDto dto = new LoginDto();
			HttpSession session = se.getSession();
			LoginInfo loginInfo = null;
			loginInfo = (LoginInfo) session.getAttribute("loginInfo");
			if (loginInfo != null) {
				dto.setLoginInfo(loginInfo);
				dto.setLogout(false);

				RemoteService remote = null;
				LocalService local = null;
				boolean isRemote = Lib.toBoolean(Lib.getSetting("ejb.app.remote"), false);
				String nameService = "loginService";
				if (isRemote) {
					remote = (RemoteService) ServiceUtils.getService(nameService, RemoteService.class);
					remote.doService(loginInfo, "logout", dto);
				} else {
					local = (LocalService) ServiceUtils.getService(nameService, LocalService.class);
					local.doService(loginInfo, "logout", dto);
				}
			}
		} catch (Exception e) {
			System.out.println("SessionTimeoutListener sessionDestroyed error: " + e.getMessage());
		}
	}

}
