package org.mega.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

/**
 * 
 * @author naphong
 *
 */
public abstract class BaseComplete extends BaseServlet {
	private static final long serialVersionUID = -100685018300420969L;
	private static final String CONTENT_TYPE = "application/json; charset=UTF-8";
	public static final int MAX_RESULT = 50;

	public BaseComplete() {
		super();
	}

	public void doPostGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		try {
			JSONArray data = searchData(req);
			String json = "{}";
			if (data != null && !data.isEmpty()) {
				json = data.toString();
			}

			System.out.println("doPostGet json = " + json);

			out.write(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

	public abstract JSONArray searchData(HttpServletRequest req);
}
