package org.mega.business.adm;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.mega.client.LocalService;
import org.mega.client.RemoteService;
import org.mega.dao.entity.Users;
import org.mega.dto.adm.ADM011Dto;

/**
 * Session Bean implementation class ADM010Service
 * 
 * @author Admin
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless(mappedName = "MEGA", name = "adm011Service")
public class ADM011Service extends ADM01Service implements RemoteService, LocalService {

	public ADM011Service() {
		super();
	}

	public void initPage(ADM011Dto dto) {
		Users user = findEntity(Users.class, dto.getId());
		if (user == null) {
			user = new Users();
		}
		user.getBranch();

		dto.setUser(user);

		loadRole(dto);

		roleControl(dto);

		// Setup business flow
		businessFlow(dto);
	}
}
