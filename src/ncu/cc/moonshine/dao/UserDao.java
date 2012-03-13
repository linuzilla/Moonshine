package ncu.cc.moonshine.dao;

import org.springframework.stereotype.Repository;

import ncu.cc.commons.jpa.BaseDao;
import ncu.cc.moonshine.domain.User;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao {
}
