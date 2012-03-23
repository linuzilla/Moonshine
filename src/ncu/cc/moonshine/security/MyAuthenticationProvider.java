package ncu.cc.moonshine.security;

import ncu.cc.moonshine.domain.MyUserDetails;
import ncu.cc.moonshine.domain.User;
import ncu.cc.moonshine.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("authenticationProvider")
public class MyAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	@Qualifier("dao")
	private IUserService    userService;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
		if (token.getCredentials() != null)
			if (userDetails.getPassword().equals(token.getCredentials().toString()))
				return;

		throw new BadCredentialsException("Bad credentials.");
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
		User user = userService.getUserByName(username);
		
		if (user == null)
			throw new UsernameNotFoundException(username);

		return new MyUserDetails(user);
	}

}