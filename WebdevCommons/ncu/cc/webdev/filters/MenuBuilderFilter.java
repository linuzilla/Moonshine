package ncu.cc.webdev.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ncu.cc.webdev.domain.WebMenuItem;
import ncu.cc.webdev.services.IMenuBuilder;

public class MenuBuilderFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(MenuBuilderFilter.class);
	private IMenuBuilder		menuBuilder;
	
	public IMenuBuilder getMenuBuilder() {
		return menuBuilder;
	}

	public void setMenuBuilder(IMenuBuilder menuBuilder) {
		this.menuBuilder = menuBuilder;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		req.getPathInfo();
		
		String requestURI = req.getRequestURI();
		String[]	path = requestURI.split("/");
		
		if (path.length >= 2) {
			List<String>	pathList = new ArrayList<String>();
			
			for (int i = 2; i < path.length; i++) {
				String	p = path[i];
		
				if (! path[i].matches("^[-_\\.0-9a-zA-Z]+$")) {
					p = "_";
				}
				System.out.println(i + ". [" + p + "]");
				pathList.add(p);
			}
			WebMenuItem menuItem = this.menuBuilder.findMenuItem(pathList);
			if (menuItem != null) {
				System.out.print("Menu found: " + menuItem.getTag());
				for (WebMenuItem parent = menuItem.getParent(); parent != null; parent = parent.getParent()) {
					System.out.print(" [" + parent.getTag() + "]");
				}
				System.out.println();
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
