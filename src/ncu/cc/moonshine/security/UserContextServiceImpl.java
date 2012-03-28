package ncu.cc.moonshine.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("userContextService")
public class UserContextServiceImpl implements UserContextService {
	public String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
