package ncu.cc.moonshine.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.webcomm.sso.ClsRsa;

public class NcuPortalFilter extends UsernamePasswordAuthenticationFilter {
	private String		validUsernamePattern = "^[_a-zA-Z0-9]+$";
	private String		keyURL = "http://140.115.182.219/SSOWeb/decodeKey";
	private UserDetailsProvider	userDetailsProvider;

	public String getKeyURL() {
		return keyURL;
	}

	public void setKeyURL(String keyURL) {
		this.keyURL = keyURL;
	}

	public UserDetailsProvider getUserDetailsProvider() {
		return userDetailsProvider;
	}

	public void setUserDetailsProvider(UserDetailsProvider userDetailsProvider) {
		this.userDetailsProvider = userDetailsProvider;
	}

	public String getValidUsernamePattern() {
		return validUsernamePattern;
	}

	public void setValidUsernamePattern(String validUsernamePattern) {
		this.validUsernamePattern = validUsernamePattern;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException {
		String loginName = req.getParameter("loginName");
		String serialNo = req.getParameter("sn");
		String groupName = req.getParameter("groupName");
		
		if (loginName != null && serialNo != null && groupName != null) {
			String userid = new ClsRsa().decodeUserID(loginName, serialNo, keyURL);
				
			if (userid != null && userid.matches(validUsernamePattern)) {
				return new NcuPortalAuthenticationToken(userDetailsProvider.retrieveUser(userid));
			}
		}

		return super.attemptAuthentication(req, res);
	}

//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res,
//			FilterChain chain) throws IOException, ServletException {
//		final HttpServletRequest request = (HttpServletRequest) req;
//        final HttpServletResponse response = (HttpServletResponse) res;
//        
//        if("GET".equals(request.getMethod())) {
//        		// If the incoming request is a POST, then we send it up
//        		// to the AbstractAuthenticationProcessingFilter.
//        		super.doFilter(request, response, chain);
//        } else {
//        		// If it's a GET, we ignore this request and send it
//        		// to the next filter in the chain.  In this case, that
//        		// pretty much means the request will hit the /login
//        		// controller which will process the request to show the
//        		// login page.
//        		chain.doFilter(request, response);
//        }
//		super.doFilter(req, res, chain);
//	}
}
