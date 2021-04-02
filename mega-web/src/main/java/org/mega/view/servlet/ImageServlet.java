package org.mega.view.servlet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 7969212695780618072L;
	private String contentType = "image/jpeg";
	private String imageType = "jpeg";

	public ImageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPostGet(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPostGet(req, res);
	}

	protected void doPostGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		OutputStream out = res.getOutputStream();
		try {
			BufferedImage image = getImageData(req);
			if (image == null) {
				image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = image.createGraphics();
				g2.setColor(new Color(255, 255, 255));
				g2.fillRect(0, 0, 1, 1);
				g2.dispose();
			}
			res.setContentType(contentType);
			ImageIO.write(image, imageType, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

	public BufferedImage getImageData(HttpServletRequest req) {
		return null;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
}
