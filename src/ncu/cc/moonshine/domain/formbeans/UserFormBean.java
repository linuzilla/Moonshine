package ncu.cc.moonshine.domain.formbeans;

import java.util.Set;


import ncu.cc.moonshine.domain.User;

public class UserFormBean extends User {
	private static final long serialVersionUID = 221783350560536719L;
	private Set<String>		roleNames;

	public Set<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(Set<String> roleNames) {
		this.roleNames = roleNames;
	}
}
