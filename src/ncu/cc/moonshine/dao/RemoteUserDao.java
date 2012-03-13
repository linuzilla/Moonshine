package ncu.cc.moonshine.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ncu.cc.commons.jpa.AbstractBaseDao;
import ncu.cc.moonshine.domain.RemoteUser;

@Repository
public class RemoteUserDao extends AbstractBaseDao<RemoteUser> implements IRemoteUserDao {
	@PersistenceContext(unitName = "remoteUnit")
	protected EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
