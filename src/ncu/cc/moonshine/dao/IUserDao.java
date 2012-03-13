package ncu.cc.moonshine.dao;

import ncu.cc.commons.jpa.IHibernateBaseDao;
import ncu.cc.commons.jpa.IJpaBaseDao;
import ncu.cc.moonshine.domain.User;

public interface IUserDao extends IJpaBaseDao<User>,IHibernateBaseDao<User> {
}
