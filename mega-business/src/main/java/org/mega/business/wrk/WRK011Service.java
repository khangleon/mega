package org.mega.business.wrk;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.mega.client.LocalService;
import org.mega.client.RemoteService;
import org.mega.dao.entity.Event;
import org.mega.dto.wrk.WRK011Dto;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless(mappedName = "MEGA", name = "wrk011Service")
public class WRK011Service extends WRK01Service implements RemoteService, LocalService {

	public WRK011Service() {
		super();
	}

	public void initPage(WRK011Dto dto) {
		Event event = findEntity(Event.class, dto.getId());
		if (event != null) {
			dto.setEventGuestValue(getGuestValue(dto.getId()));
		} else {
			event = new Event();
			event.setBranchId(loginInfo.getBranchId());
			event.setCompanyId(loginInfo.getCompanyId());
		}
		dto.setEvent(event);
		
		roleControl(dto);
	}

}
