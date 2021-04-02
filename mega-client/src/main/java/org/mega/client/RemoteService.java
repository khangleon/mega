package org.mega.client;

import java.util.List;

import javax.ejb.Remote;

import org.mega.dto.core.LoginInfo;

@Remote
public interface RemoteService {
	Object[] doService(LoginInfo loginInfo, String serviceName, Object... dtos) throws Exception;
	
	<E> E findEntity(Class<E> entityClass, Object primaryKey);

	<E> E getEntity(ParamQuery paramQuery);

	<E> List<E> getEntities(ParamQuery paramQuery);
}
