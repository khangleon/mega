package org.mega.view.filter;

import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.view.facelets.ResourceResolver;

/**
 * @author Pham My Man
 */
public class FilesystemResourceResolver extends ResourceResolver {
	@Override
	public URL resolveUrl(String s) {
		try {
			return new URL("file", "", "E:/projects/mega/mega-web/src/main/webapp" + s);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}