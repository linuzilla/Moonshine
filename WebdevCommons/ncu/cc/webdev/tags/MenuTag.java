package ncu.cc.webdev.tags;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.JspAwareRequestContext;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

public class MenuTag extends TagSupport {
	public static final String REQUEST_CONTEXT_PAGE_ATTRIBUTE =
            "org.springframework.web.servlet.tags.REQUEST_CONTEXT";
	
	private RequestContext	requestContext;

	@Override
	public int doStartTag() throws JspException {
		this.requestContext = (RequestContext) this.pageContext.
				getAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE);
		if (this.requestContext == null) {
			this.requestContext = new JspAwareRequestContext(this.pageContext);
			this.pageContext.setAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE, this.requestContext);
		}
		
		if (SecurityContextHolder.getContext() == null) {
			
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return super.doStartTag();
	}
}
