package ncu.cc.moonshine.services;

import java.util.List;

import ncu.cc.moonshine.domain.Role;

public interface IRoleService {
	public List<Role> findAll();
	public Role getRoleById(Integer roleId);
	public Role getRoleByName(String name);
	public void addRole(Role role);
	public void deleteRole(Role role);
	public void modifyRole(Role role);
	public void deleteRoleById(Integer roleId);
}
