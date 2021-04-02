package org.mega.view.adm;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import org.mega.common.Lib;
import org.mega.dao.entity.UserBusiness;
import org.mega.dao.entity.Users;
import org.mega.dto.adm.ADM012Dto;
import org.mega.view.core.BaseBean;

@ManagedBean(name = "adm012Bean")
@ViewScoped
public class ADM012Bean extends BaseBean {
	private ADM012Dto dto;

	private Part image;
	private String imageName;

	public ADM012Bean() {
		super();
	}

	@Override
	public String getPageCode() {
		return ADM012;
	}

	@Override
	public String getBussinessCode() {
		return ADM01;
	}

	@Override
	public void initPage() throws Exception {
		dto = getDto();
		dto.setId(Lib.toLong(getParamPage(ID)));
		dto.setFromPage(getParamPage(FROM_PAGE));
		doService("initPage", dto);
	}

	/**
	 * Save user to database
	 * 
	 * @return
	 * @throws Exception
	 */
	public void saveAction() throws Exception {
		doService("save", dto);
		dto.setSelectAll1(false);
		dto.setSelectAll2(false);
		dto.setSelectAll3(false);
	}

	/**
	 * Back to List View
	 * 
	 * @return
	 * @throws Exception
	 */
	public String closeAction() throws Exception {
		fromCacheDto(true);
		return goPage(Lib.isEmpty(dto.getFromPage()) ? ADM010 : dto.getFromPage(), true);
	}

	/**
	 * Disable
	 * 
	 * @return
	 * @throws Exception
	 */
	public void disableAction() throws Exception {
		doService("disable", dto);
	}

	/**
	 * Enable user
	 * 
	 * @return
	 * @throws Exception
	 */
	public void enableAction() throws Exception {
		doService("enable", dto);
	}

	/**
	 * Close user
	 * 
	 * @return
	 * @throws Exception
	 */
	public void lockAction() throws Exception {
		doService("lock", dto);
	}

	/**
	 * Open
	 * 
	 * @return
	 * @throws Exception
	 */
	public void unlockAction() throws Exception {
		doService("unlock", dto);
	}

	/**
	 * Reset password
	 * 
	 * @throws Exception
	 */
	public void resetPasswordAction() throws Exception {
		dto.setEditPass(true);
		dto.getUser().setPassword("");
	}

	/**
	 * Delete user
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteAction() throws Exception {
		doService("delete", dto);
		return goPage(ADM010);
	}

	/**
	 * Change select role
	 * 
	 * @throws Exception
	 */
	public void changeSelectRoleAction() throws Exception {
		doService("changeSelectRole", dto);
	}

	/**
	 * Change employee
	 * 
	 * @throws Exception
	 */
	public void changeEmployeeAction() throws Exception {
		doService("changeEmployee", dto);
	}

	/**
	 * Upload image
	 * 
	 * @return
	 * @throws IOException
	 */
	public void uploadImageAction() throws IOException {
		Users user = dto.getUser();
		imageName = getFileName(image);
		String imageType = Lib.getImageType(imageName);
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {
			inputStream = image.getInputStream();
			BufferedImage image = ImageIO.read(inputStream);
			image = Lib.getScaledImage(image, 150, 150);
			outputStream = new ByteArrayOutputStream();

			if (image != null) {
				ImageIO.write(image, imageType, outputStream);
			}

			user.setImage(outputStream.toByteArray());
			user.setImageType(imageType);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	/**
	 * Delete image
	 * 
	 * @return
	 * @throws IOException
	 */
	public void deleteImageAction() throws IOException {
		Users user = dto.getUser();
		user.setImage(null);
		user.setImageType(null);
	}

	/**
	 * Extract file name from content-disposition header of file part
	 * 
	 * @param part
	 * @return
	 */
	private String getFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : partHeader.split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void selectAll1Action() throws Exception {
		List<UserBusiness> itemList = dto.getUserBusiness();
		boolean selectAll1 = dto.isSelectAll1();
		for (UserBusiness e : itemList) {
			if (selectAll1) {
				e.setLevelApprove1(true);
			} else {
				e.setLevelApprove1(false);
			}
		}
		dto.setUserBusiness(itemList);
	}

	public void selectAll2Action() throws Exception {
		List<UserBusiness> itemList = dto.getUserBusiness();
		boolean selectAll2 = dto.isSelectAll2();
		for (UserBusiness e : itemList) {
			if (selectAll2) {
				e.setLevelApprove2(true);
			} else {
				e.setLevelApprove2(false);
			}
		}
		dto.setUserBusiness(itemList);
	}

	public void selectAll3Action() throws Exception {
		List<UserBusiness> itemList = dto.getUserBusiness();
		boolean selectAll3 = dto.isSelectAll3();
		for (UserBusiness e : itemList) {
			if (selectAll3) {
				e.setLevelApprove3(true);
			} else {
				e.setLevelApprove3(false);
			}
		}
		dto.setUserBusiness(itemList);
	}

}
