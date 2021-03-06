package ncu.cc.moonshine.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import flexjson.JSONSerializer;

import ncu.cc.moonshine.dao.IRoleDao;
import ncu.cc.moonshine.domain.Role;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleDao		roleDao;
	
	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public Role getRoleById(Integer roleId) {
		return roleDao.findById(roleId);
	}

	@Override
	public Role getRoleByName(String name) {
		List<Role> role = roleDao.findByProperty("roleName", name);
		if (role != null && role.size() > 0) {
			return role.get(0);
		}
		return null;
	}

	@Transactional
	@Override
	public void addRole(Role role) {
		roleDao.persist(role);
	}

	@Transactional
	@Override
	public void deleteRole(Role role) {
		roleDao.delete(role);
	}

	@Transactional
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#role, 'modify')")
	public void modifyRole(Role role) {
		roleDao.merge(role);
	}

	@Transactional
	@Override
	public void deleteRoleById(Integer roleId) {
		roleDao.remove(roleDao.findById(roleId));
	}

	public String getRoleInJson(String roleName) {
		return new JSONSerializer().serialize(getRoleByName(roleName));
	}
}
