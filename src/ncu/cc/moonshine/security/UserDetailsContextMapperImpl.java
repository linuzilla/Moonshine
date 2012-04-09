package ncu.cc.moonshine.security;

import java.util.Collection;

import ncu.cc.moonshine.domain.User;
import ncu.cc.moonshine.services.IUserService;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

public class UserDetailsContextMapperImpl implements UserDetailsContextMapper {
	private IUserService		userService;
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx,
			String username, Collection<? extends GrantedAuthority> authorities) {
		User u = userService.getUserByName(username);
		MyUserDetails userDetails = null;
		
		if (u != null) {
			userDetails = new MyUserDetails(u);
		} else {
			userDetails = new MyUserDetails();
			userDetails.setName(username);
		}
		
		for (GrantedAuthority ga: authorities) {
			userDetails.addAuthoritie(ga);
		}
		
		return userDetails;
	}

	@Override
	public void mapUserToContext(UserDetails userDetails, DirContextAdapter ctx) {
		throw new UnsupportedOperationException("LdapUserDetailsMapper only supports reading from a context. Please" +
                "use a subclass if mapUserToContext() is required.");
	}

}
