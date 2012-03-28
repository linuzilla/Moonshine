package ncu.cc.webdev.filters;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import ncu.cc.webdev.domain.WebMenuItem;
import ncu.cc.webdev.services.IMenuBuilder;

public class MenuBuilderFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(MenuBuilderFilter.class);
	public static final String	MENU = "__MENU__";
	public static final String	SELECTED_ITEM = "__SELECTED_ITEM__";
	private static final String	MENU_SESSION_NAME = MenuBuilderFilter.class.getName() + ".MENU";
	private IMenuBuilder			menuBuilder;
	
	public IMenuBuilder getMenuBuilder() {
		return menuBuilder;
	}

	public void setMenuBuilder(IMenuBuilder menuBuilder) {
		this.menuBuilder = menuBuilder;
	}

	@Override
	public void destroy() {
	}
	
	private List<WebMenuItem> copyMenu(List<WebMenuItem> list, Collection<? extends GrantedAuthority> auth) {
		List<WebMenuItem> newList = new ArrayList<WebMenuItem>();
		
		for (WebMenuItem item: list) {
			if (item.getAuthorities() != null && item.getAuthorities().size() > 0) {
				if (auth == null || auth.size() == 0) continue;
				
				boolean qualified = false;
				for (GrantedAuthority au: auth) {
					if (item.getAuthorities().contains(au.getAuthority())) {
						qualified = true;
						break;
					}
				}
				if (! qualified) continue;
			}

			WebMenuItem newItem = new WebMenuItem();
			BeanUtils.copyProperties(item, newItem);
			
			if (item.getSubMenu() != null) {
				newItem.setSubMenu(copyMenu(item.getSubMenu(), auth));
			}
			
			newList.add(newItem);
		}
		return newList;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		// HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		String sessionName = MENU_SESSION_NAME + "::" + SecurityContextHolder.getContext().getAuthentication().getName();
		
		if (session.getAttribute(sessionName) == null) {
			session.setAttribute(sessionName,
					copyMenu(
							menuBuilder.getNagivatorMenu().getNavigatorBar(), 
							SecurityContextHolder.getContext().getAuthentication().getAuthorities()
					)
			);
			logger.info("Build Menu : " + sessionName);
		}
		
		req.setAttribute(MENU, session.getAttribute(sessionName));
				
//		req.getPathInfo();
//		
		String requestURI = req.getRequestURI();
		String[]	path = requestURI.split("/");
		
		if (path.length >= 2) {
			List<String>	pathList = new ArrayList<String>();
			
			for (int i = 2; i < path.length; i++) {
				String	p = path[i];
		
				if (! path[i].matches("^[-_\\.0-9a-zA-Z]+$")) {
					p = "_";
				}
				// StackTraceUtil.print1(i + ". [" + p + "]");
				pathList.add(p);
			}
			WebMenuItem menuItem = this.menuBuilder.findMenuItem(pathList);
			if (menuItem != null) {
				Deque<WebMenuItem> stack = new ArrayDeque<WebMenuItem>(); 
				for (; menuItem != null; menuItem = menuItem.getParent()) {
					stack.push(menuItem);
				}
				req.setAttribute(SELECTED_ITEM, stack);
//				StackTraceUtil.print1("Menu found: " + menuItem.getTag());
//				for (WebMenuItem parent = menuItem.getParent(); parent != null; parent = parent.getParent()) {
//					StackTraceUtil.print1(" [" + parent.getTag() + "]");
//				}
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
