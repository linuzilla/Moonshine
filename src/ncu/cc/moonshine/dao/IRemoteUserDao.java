package ncu.cc.moonshine.dao;

import ncu.cc.commons.jpa.IHibernateBaseDao;
import ncu.cc.commons.jpa.IJpaBaseDao;
import ncu.cc.moonshine.domain.RemoteUser;

public interface IRemoteUserDao extends IJpaBaseDao<RemoteUser>,IHibernateBaseDao<RemoteUser> {
}
