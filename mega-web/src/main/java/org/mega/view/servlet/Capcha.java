package org.mega.view.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mega.common.Lib;

@WebServlet(name = "capcha", urlPatterns = { "/capcha" })
public class Capcha extends HttpServlet {
	private static final long serialVersionUID = -3154320917822245883L;

	public Capcha() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPostGet(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPostGet(req, res);
	}

	protected void doPostGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String capchaRandom = req.getParameter("capchaRandom");
		int capchaIndex = Lib.toInteger(req.getParameter("capchaIndex"), 0);
		String path = req.getServletContext().getRealPath("skins/image/capchabackground.png");

		OutputStream out = res.getOutputStream();
		try {
			res.setContentType("image/png");

			// Read from a URL
			File file = new File(path);
			if (file.exists()) {
				BufferedImage image = ImageIO.read(file);
				image = imageProcess(image, capchaRandom, capchaIndex);
				ImageIO.write(image, "png", out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

	public BufferedImage imageProcess(BufferedImage image, String capchaRandom, int capchaIndex) {
		if (image == null) {
			return image;
		}

		// Process image
		Graphics2D g = (Graphics2D) image.getGraphics();

		Font font = null;
		Color color = new Color(247, 129, 42);
//		Color capchaColor = new Color(166, 53, 9);

		char[] arrChar = capchaRandom.toCharArray();

		int w = 20;
		Random random = new Random();
		double rad = 0;
		int fontSize = 17;

		AffineTransform at = null;
		for (int i = 0; i < arrChar.length; i++) {
//			if (i == capchaIndex) {
//				g.setColor(capchaColor);
//			} else {
//				g.setColor(color);
//			}
			g.setColor(color);

			// Angle
			rad = Math.toRadians(30 - random.nextInt(60));
			at = new AffineTransform();
			if (rad < 0) {
				at.translate(0, 0);
			} else {
				at.translate(10, 0);
			}
			at.rotate(rad, 10 + i * w, 0);
			g.setTransform(at);

			// Font
			fontSize = 17 + random.nextInt(5);
			if (random.nextInt(2) > 0) {
				font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
			} else {
				font = new Font(Font.SANS_SERIF, Font.PLAIN, fontSize);
			}
			g.setFont(font);

			// Draw string to image
			g.drawString(String.valueOf(arrChar[i]), 5 + i * w, 22);
		}

		return image;
	}

}
