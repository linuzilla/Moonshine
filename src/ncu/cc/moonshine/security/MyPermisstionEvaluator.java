package ncu.cc.moonshine.security;

import java.io.Serializable;

import ncu.cc.moonshine.domain.User;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class MyPermisstionEvaluator implements PermissionEvaluator {
	@Override
	public boolean hasPermission(Authentication auth, Object target, Object perm) {
		if (target instanceof User) {
			User user = (User) target;
			if ("modify".equals(perm)) {
				return auth.getName().equals(user.getCreatedBy());
			} else if ("list".equals(perm)) {
				if (auth.getName().equals(user.getCreatedBy())) {
					user.setDeletable(true);
				}
				return auth.getName().equals(user.getCreatedBy());
			}
		}
		throw new UnsupportedOperationException("hasPermission not supported for object <"+target+"> and permission <"+perm+">");
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId,
			String targetType, Object permission) {
		throw new UnsupportedOperationException("Not supported");
	}
}
