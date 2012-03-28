package ncu.cc.webdev.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserContextService<T extends UserDetails> {
	public String getCurrentUser();
	public T getPrincipal();
}
