package ncu.cc.moonshine.dao;

import org.springframework.stereotype.Repository;

import ncu.cc.commons.jpa.BaseDao;
import ncu.cc.moonshine.domain.Role;

@Repository
public class RoleDao extends BaseDao<Role> implements IRoleDao {
}
