package org.mega.business.core;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.LockModeType;

import org.apache.log4j.Logger;
import org.mega.dao.core.DaoEntity;
import org.mega.dao.entity.MGenId;
import org.mega.dao.entity.MSeq;
import org.mega.dto.core.LoginInfo;

public class DAService extends DaoService {
	public static final Logger log = Logger.getLogger("MEGA");

	protected LoginInfo loginInfo;
	protected String businessCode;
	protected String businessName;
	protected String serviceName;

	public DAService() {
		super(new Locale("vi", "VN"), TimeZone.getTimeZone("GMT+07:00"));
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
		this.locale = loginInfo.getLocale();
		this.zone = loginInfo.getZone();
	}

	/**
	 * Gen id
	 * 
	 * @param name
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	synchronized public Long getGlobalId(String name) {
		Long id = 1L;
		MGenId genId = findEntity(MGenId.class, name, LockModeType.PESSIMISTIC_WRITE);
		if (genId != null) {
			id = genId.getValue() + 1;
			genId.setValue(id);
			mergeEntity(genId);
		} else {
			genId = new MGenId();
			genId.setName(name);
			genId.setValue(id);
			mergeEntity(genId, true);
		}
		return id;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	synchronized public Long getId(String name) {
		Long id = 1L;
		MGenId genId = findEntity(MGenId.class, name, LockModeType.PESSIMISTIC_WRITE);
		if (genId != null) {
			id = genId.getValue() + 1;
			genId.setValue(id);
			mergeEntity(genId);
		} else {
			genId = new MGenId();
			genId.setName(name);
			genId.setValue(id);
			mergeEntity(genId, true);
		}

		// Add suffix branch
		DecimalFormat df = new DecimalFormat("000000");
		if (loginInfo != null) {
			if (loginInfo.getBranchId() != null) {
				id = Long.valueOf(id.toString().concat(df.format(loginInfo.getBranchId())));
			} else {
				if (loginInfo.getCompanyId() != null) {
					id = Long.valueOf(id.toString().concat(df.format(loginInfo.getCompanyId())));
				}
			}
		}

		return id;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	synchronized public Long getSeq(String name) {
		Long seq = 1L;
		MSeq mseq = findEntity(MSeq.class, name, LockModeType.PESSIMISTIC_WRITE);
		if (mseq == null) {
			mseq = new MSeq();
			mseq.setName(name);
			mseq.setNameLogic(name);
			mseq.setValue(seq);
		} else {
			seq = mseq.getValue() + 1;
			mseq.setValue(seq);
		}
		mseq = mergeEntity(mseq);
		return seq;
	}

	synchronized public Long getSeq(String name, Long company) {
		Long seq = 1L;
		String logicName = name;
		name = company == null ? name : name + company + "0";
		MSeq mseq = findEntity(MSeq.class, name, LockModeType.PESSIMISTIC_WRITE);
		if (mseq == null) {
			mseq = new MSeq();
			mseq.setName(name);
			mseq.setNameLogic(logicName);
			mseq.setValue(seq);
		} else {
			seq = mseq.getValue() + 1;
			mseq.setValue(seq);
		}
		mseq = mergeEntity(mseq);
		return seq;
	}

	synchronized public Long getSeq(String name, Long company, Long branch) {
		Long seq = 1L;
		String logicName = name;
		name = branch == null ? name : branch + "0" + name;
		name = company == null ? name : company + "0" + name;
		MSeq mseq = findEntity(MSeq.class, name, LockModeType.PESSIMISTIC_WRITE);
		if (mseq == null) {
			mseq = new MSeq();
			mseq.setName(name);
			mseq.setNameLogic(logicName);
			mseq.setValue(seq);
		} else {
			seq = mseq.getValue() + 1;
			mseq.setValue(seq);
		}
		mseq = mergeEntity(mseq);
		return seq;
	}

	synchronized public Long getSeq(String name, Long company, Long branch, Long department) {
		Long seq = 1L;
		String logicName = name;
		name = department == null ? name : department + "0" + name;
		name = branch == null ? name : branch + "0" + name;
		name = company == null ? name : company + "0" + name;
		MSeq mseq = findEntity(MSeq.class, name, LockModeType.PESSIMISTIC_WRITE);
		if (mseq == null) {
			mseq = new MSeq();
			mseq.setName(name);
			mseq.setNameLogic(logicName);
			mseq.setValue(seq);
		} else {
			seq = mseq.getValue() + 1;
			mseq.setValue(seq);
		}
		mseq = mergeEntity(mseq);
		return seq;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	synchronized public Long getSeq(String name, Integer date) {
		Long seq = 1L;
		String logicName = name;
		name = date == null ? name : name + "_" + date;
		MSeq mseq = findEntity(MSeq.class, name, LockModeType.PESSIMISTIC_WRITE);
		if (mseq == null) {
			mseq = new MSeq();
			mseq.setName(name);
			mseq.setNameLogic(logicName);
			mseq.setValue(seq);
		} else {
			seq = mseq.getValue() + 1;
			mseq.setValue(seq);
		}
		mseq = mergeEntity(mseq);
		return seq;
	}

	synchronized public Long getSeq(String name, Long company, Integer date) {
		Long seq = 1L;
		String logicName = name;
		name = company == null ? name : name + company + "0";
		name = date == null ? name : name + "_" + date;
		MSeq mseq = findEntity(MSeq.class, name, LockModeType.PESSIMISTIC_WRITE);
		if (mseq == null) {
			mseq = new MSeq();
			mseq.setName(name);
			mseq.setNameLogic(logicName);
			mseq.setValue(seq);
		} else {
			seq = mseq.getValue() + 1;
			mseq.setValue(seq);
		}
		mseq = mergeEntity(mseq);
		return seq;
	}

	synchronized public Long getSeq(String name, Long company, Long branch, Integer date) {
		Long seq = 1L;
		String logicName = name;
		name = branch == null ? name : branch + "0" + name;
		name = company == null ? name : company + "0" + name;
		name = date == null ? name : name + "_" + date;
		MSeq mseq = findEntity(MSeq.class, name, LockModeType.PESSIMISTIC_WRITE);
		if (mseq == null) {
			mseq = new MSeq();
			mseq.setName(name);
			mseq.setNameLogic(logicName);
			mseq.setValue(seq);
		} else {
			seq = mseq.getValue() + 1;
			mseq.setValue(seq);
		}
		mseq = mergeEntity(mseq);
		return seq;
	}

	synchronized public Long getSeq(String name, Long company, Long branch, Long department, Integer date) {
		Long seq = 1L;
		String logicName = name;
		name = department == null ? name : department + "0" + name;
		name = branch == null ? name : branch + "0" + name;
		name = company == null ? name : company + "0" + name;
		name = date == null ? name : name + "_" + date;
		MSeq mseq = findEntity(MSeq.class, name, LockModeType.PESSIMISTIC_WRITE);
		if (mseq == null) {
			mseq = new MSeq();
			mseq.setName(name);
			mseq.setNameLogic(logicName);
			mseq.setValue(seq);
		} else {
			seq = mseq.getValue() + 1;
			mseq.setValue(seq);
		}
		mseq = mergeEntity(mseq);
		return seq;
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <E> E insertEntity(DaoEntity entity) {
		if (entity == null) {
			return null;
		}
		String entityName = entity.getClass().getSimpleName();

		Date currDate = getTimestamp();
		if (entity.getId() == null) {
			entity.setId(getId(entityName));
			entity.setCreatedId(this.loginInfo.getUserId());
			entity.setCreatedDate(currDate);
		}

		entity.setUpdatedId(this.loginInfo.getUserId());
		entity.setUpdatedDate(currDate);

		entity = persistEntity(entity);
		return (E) entity;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <E> E updateEntity(DaoEntity entity) {
		return updateEntity(entity, false);
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <E> E updateEntity(DaoEntity entity, boolean flush) {
		if (entity == null) {
			return null;
		}

		System.out.println("updateEntity " + entity.getClass().getSimpleName() + " with Username:"
				+ loginInfo.getUserName() + "    department:" + loginInfo.getDepartmentId() + "    branch:"
				+ loginInfo.getBranchId() + "    company:" + loginInfo.getCompanyId());

		Date currDate = getTimestamp();
		String entityName = entity.getClass().getSimpleName();
		if (entity.getId() == null) {
			entity.setId(getId(entityName));
			entity.setCreatedId(this.loginInfo.getUserId());
			entity.setCreatedDate(currDate);
		}
		System.out.println("updateEntity " + entity.getClass().getSimpleName() + " id: " + entity.getId());

		if (entity.isDeleted()) {
			entity.setDeletedId(this.loginInfo.getUserId());
			entity.setDeletedDate(currDate);
		}

		entity.setUpdatedId(this.loginInfo.getUserId());
		entity.setUpdatedDate(currDate);

		entity = mergeEntity(entity, flush);
		return (E) entity;
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <E> E deleteEntity(DaoEntity entity) {
		if (entity == null) {
			return null;
		}

		String entityName = entity.getClass().getSimpleName();
		if (entity.getId() == null) {
			throw new RuntimeException("Can't delete entity unknow id.");
		}

		Date currDate = getTimestamp();

		entity.setDeleted(true);
		entity.setDeletedId(this.loginInfo.getUserId());
		entity.setDeletedDate(currDate);

		entity = mergeEntity(entity);
		return (E) entity;
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <E> List<E> insertEntities(List<DaoEntity> entities) {
		if (entities == null) {
			return null;
		}

		Date currDate = getTimestamp();
		String entityName = null;
		String crud = null;
		for (DaoEntity entity : entities) {
			entityName = entity.getClass().getSimpleName();
			if (entity.getId() == null) {
				entity.setId(getId(entityName));
				entity.setCreatedId(this.loginInfo.getUserId());
				entity.setCreatedDate(currDate);
			}

			entity.setUpdatedId(this.loginInfo.getUserId());
			entity.setUpdatedDate(currDate);
		}

		entities = persistEntities(entities);

		return (List<E>) entities;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <E> List<E> updateEntities(List<E> entities) {
		return updateEntities(entities, false);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <E> List<E> updateEntities(List<E> entities, boolean flush) {
		if (entities == null) {
			return null;
		}

		Date currDate = getTimestamp();
		String entityName = null;
		String crud = null;
		DaoEntity entity;
		for (E e : entities) {
			entity = (DaoEntity) e;
			entityName = entity.getClass().getSimpleName();
			if (entity.getId() == null) {
				entity.setId(getId(entityName));
				entity.setCreatedId(this.loginInfo.getUserId());
				entity.setCreatedDate(currDate);
			}

			if (entity.isDeleted()) {
				entity.setDeletedId(this.loginInfo.getUserId());
				entity.setDeletedDate(currDate);
			}

			entity.setUpdatedId(this.loginInfo.getUserId());
			entity.setUpdatedDate(currDate);
		}

		entities = mergeEntities(entities, flush);

		return (List<E>) entities;
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public <E> List<E> deleteEntities(List<E> entities) {
		if (entities == null) {
			return null;
		}

		Date currDate = getTimestamp();
		DaoEntity entity;
		List<E> list = new ArrayList<E>();
		String entityName = null;
		for (E e : entities) {
			entity = (DaoEntity) e;
			if (entity.getId() == null) {
				continue;
			}
			entityName = entity.getClass().getSimpleName();
			entity.setDeleted(true);
			entity.setDeletedId(this.loginInfo.getUserId());
			entity.setDeletedDate(currDate);

			list.add((E) entity);
		}

		entities = mergeEntities(list);

		return (List<E>) entities;
	}

}
