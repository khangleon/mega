package org.mega.business.adm;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.mega.client.LocalService;
import org.mega.client.ParamQuery;
import org.mega.client.RemoteService;
import org.mega.constant.Constant;
import org.mega.dao.entity.Users;
import org.mega.dto.adm.ADM010Dto;

/**
 * Session Bean implementation class ADM010Service
 * 
 * @author Admin
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless(mappedName = "MEGA", name = "adm010Service")
public class ADM010Service extends ADM01Service implements RemoteService, LocalService {

	public ADM010Service() {
		super();
	}

	public void initPage(ADM010Dto dto) {
		// Setup business flow
		businessFlow(dto);
	}

	/**
	 * Search quick data
	 * 
	 * @param dto
	 */
	public void searchQuick(ADM010Dto dto) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Users.searchQuick");

		String query = dto.getQuickSearch();
		query = ParamQuery.wildCardLike(query);

		Long branchId = null;
		if (Constant.LEVEL_DATA_COMPANY.equals(loginInfo.getLevelData(dto.getBusinessCode()))) {
			branchId = 0L;
		} else {
			branchId = loginInfo.getBranchId();
		}

		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		paramQuery.addParam("branchId", branchId);
		paramQuery.addParam("account", query);
		paramQuery.addParam("name", query);
		paramQuery.addParam("active", 1);
		paramQuery.addParam("open", 1);

		List<Users> tableData = getEntities(paramQuery);
		dto.setTableData(tableData);
		dto.setDataTable(dto.getTableData().firstPageData());
	}

	/**
	 * Search advance data
	 * 
	 * @param dto
	 */
	public void searchAdvance(ADM010Dto dto) {
		ParamQuery paramQuery = new ParamQuery();
		paramQuery.setName("Users.searchAdvance");

		String account = dto.getAccount();
		String name = dto.getName();
		String phone = dto.getPhone();
		String email = dto.getEmail();
		Integer status = dto.getStatus();
		account = ParamQuery.wildCardLike(account);
		name = ParamQuery.wildCardLike(name);
		phone = ParamQuery.wildCardLike(phone);
		email = ParamQuery.wildCardLike(email);

		Long branchId = null;
		if (Constant.LEVEL_DATA_COMPANY.equals(loginInfo.getLevelData(dto.getBusinessCode()))) {
			branchId = 0L;
		} else {
			branchId = loginInfo.getBranchId();
		}

		paramQuery.addParam("companyId", loginInfo.getCompanyId());
		paramQuery.addParam("branchId", branchId);
		paramQuery.addParam("account", account);
		paramQuery.addParam("name", name);
		paramQuery.addParam("phone", phone);
		paramQuery.addParam("email", email);
		paramQuery.addParam("status", status);
		paramQuery.addParam("active", Users.STATUS_ACTIVE.equals(status) ? 1 : 0);
		paramQuery.addParam("open", Users.STATUS_OPEN.equals(status) ? 1 : 0);
		paramQuery.addParam("changePassword", Users.STATUS_RESET.equals(status) ? 1 : 0);
		paramQuery.addParam("userGroup", dto.getGroup());

		List<Users> tableData = getEntities(paramQuery);
		dto.setTableData(tableData);
		dto.setDataTable(dto.getTableData().firstPageData());
	}

}
