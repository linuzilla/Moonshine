package ncu.cc.webdev.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserContextServiceImpl implements UserContextService<UserDetails> {
	@Override
	public String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@Override
	public UserDetails getPrincipal() {
		// SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
