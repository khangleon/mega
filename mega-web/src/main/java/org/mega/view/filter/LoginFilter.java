package org.mega.view.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mega.dto.core.LoginInfo;

public class LoginFilter implements Filter {
	private FilterConfig _filterConfig = null;
	private String loginPage;

	public LoginFilter() {
		super();
	}

	@Override
	public void init(FilterConfig filterConfig) {
		_filterConfig = filterConfig;
		loginPage = _filterConfig.getInitParameter("LOGINPAGE");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		if (req == null || res == null || chain == null) {
			return;
		}
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		LoginInfo loginInfo = null;
		if (session != null) {
			loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		}

		String uri = null;
		String servletPath = null;
		String contextPath = null;
		String urlLogin = null;
		if (loginInfo == null || loginInfo.getUserId() == null) {
			uri = request.getRequestURI();
			servletPath = request.getServletPath();
			contextPath = request.getContextPath();
			urlLogin = new StringBuffer().append(contextPath).append(servletPath).append(loginPage).toString();

			// If don't login page then go to login page
			if (!urlLogin.startsWith(uri)) {
				response.sendRedirect(urlLogin);
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		_filterConfig = null;
	}

}
