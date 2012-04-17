package ncu.cc.moonshine.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class NcuPortalAuthenticationToken extends AbstractAuthenticationToken {
	private final Object credentials;
    private final Object principal;
    private Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	
    public NcuPortalAuthenticationToken(Object principal, Object credentials) {
    		super(null);
    		//public NcuPortalAuthenticationToken(Collection<? extends GrantedAuthority> arg0) {
		this.credentials = credentials;
		this.principal = principal;
	}
    
    public NcuPortalAuthenticationToken(Object principal) {
		super(null);
		this.credentials = null;
		this.principal = principal;
    }

	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
}
