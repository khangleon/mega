package org.mega.business.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.mega.dto.core.Dto;
import org.mega.dto.core.LoginInfo;
import org.mega.dto.core.MessageDto;

public class BaseService extends DAService {
	protected MessageDto messageDto = new MessageDto();

	public BaseService() {
		super();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Object[] doService(LoginInfo loginInfo, String serviceName, Object... dtos) throws Exception {
		System.out.println(this.getClass().getSimpleName() + ": " + serviceName + "\n");
		messageDto = new MessageDto();
		Method serviceMethod = null;
		try {
			setLoginInfo(loginInfo);

			Class<?>[] parameterTypes = null;
			if (dtos != null && dtos.length > 0) {
				Object dto = dtos[0];
				if (dto != null && dto instanceof Dto) {
					this.businessCode = ((Dto) dto).getBusinessCode();
					this.businessName = ((Dto) dto).getBusinessName();
					this.serviceName = serviceName;
				}

				parameterTypes = new Class<?>[dtos.length];
				for (int i = 0; i < dtos.length; i++) {
					if (dtos[i] != null) {
						parameterTypes[i] = dtos[i].getClass();
					}
				}
			}

			try {
				if (parameterTypes != null) {
					serviceMethod = this.getClass().getMethod(serviceName, parameterTypes);
				} else {
					serviceMethod = this.getClass().getMethod(serviceName);
				}
			} catch (NoSuchMethodException e) {
				throw e;
			}

			if (serviceMethod != null) {
				serviceMethod.invoke(this, dtos);
				if (dtos != null && dtos.length > 0) {
					Object dto = dtos[0];
					if (dto != null && dto instanceof Dto) {
						((Dto) dto).setMessageDto(messageDto);
					}
				}
			}
		} catch (Exception e) {
			Throwable throwable = null;
			Exception f = null;
			if (e instanceof InvocationTargetException) {
				throwable = ((InvocationTargetException) e).getTargetException();
				if (throwable instanceof Exception) {
					f = (Exception) throwable;
				} else {
					f = new Exception(throwable.getMessage(), throwable);
				}
			} else {
				f = e;
			}
			throw f;
		}

		return dtos;
	}

}
