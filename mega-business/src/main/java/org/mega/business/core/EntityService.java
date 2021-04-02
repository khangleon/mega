package org.mega.business.core;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.mega.client.LocalService;
import org.mega.client.RemoteService;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless(mappedName = "MEGA", name = "entityService")
public class EntityService extends BaseService implements RemoteService, LocalService {
	public EntityService() {
		super();
	}
}
