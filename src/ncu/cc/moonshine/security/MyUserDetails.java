package ncu.cc.moonshine.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ncu.cc.moonshine.domain.Role;
import ncu.cc.moonshine.domain.User;

import org.springframework.beans.BeanUtils;
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

		BeanUtils.copyProperties(user, this);
		authorities = new HashSet<GrantedAuthority>();
		if (user.getRoles() != null && user.getRoles().size() > 0) {
			for (Role role: user.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
		}
	}

	public void addAuthoritie(GrantedAuthority authoritie) {
		if (this.authorities == null) {
			this.authorities = new HashSet<GrantedAuthority>();
		}
		this.authorities.add(authoritie);
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
