package ncu.cc.moonshine.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsProvider {
	public UserDetails retrieveUser(String username);
}
