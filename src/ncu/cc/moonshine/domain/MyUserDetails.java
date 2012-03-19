package ncu.cc.moonshine.domain;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails extends User implements UserDetails {
	private static final long serialVersionUID = -8012145502657504272L;
	private Set<GrantedAuthority>	authorities;
	
	public MyUserDetails() {
		super();
	}
	
	public MyUserDetails(User user) {
		super();
		try {
			BeanUtils.copyProperties(this, user);
			authorities = new HashSet<GrantedAuthority>();
			if (user.getRoles() != null && user.getRoles().size() > 0) {
				for (Role role: user.getRoles()) {
					authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return super.getEmail();
	}

	@Override
	public String getUsername() {
		return super.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
