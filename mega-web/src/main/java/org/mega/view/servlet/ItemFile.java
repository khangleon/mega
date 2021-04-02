package org.mega.view.servlet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ItemFile extends BaseServlet {
	private static final long serialVersionUID = -2763957004643105654L;
	private String contentType = "image/jpeg";
	private String imageType = "jpeg";

	public ItemFile() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPostGet(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPostGet(req, res);
	}

	protected void doPostGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// String fileImageData = getFileImageData(req);
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

	public byte[] getFileData(HttpServletRequest req) {
		return null;
	}

	public String getFileImageData(HttpServletRequest req) {
		return null;
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
