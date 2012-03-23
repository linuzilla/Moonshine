package ncu.cc.webdev.domain;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ncu.cc.webdev.annotations.MenuItem;

public class WebMenuNavigator {
	private static final Logger logger = LoggerFactory.getLogger(WebMenuNavigator.class);
	private List<WebMenuItem>	menuBar = new ArrayList<WebMenuItem>();

	public List<WebMenuItem> getNavigatorBar() {
		return menuBar;
	}

	public void setNavigatorBar(List<WebMenuItem> navigatorBar) {
		this.menuBar = navigatorBar;
	}

	public WebMenuItem findMenuItem(String path) {
		if (path == null || "".equals(path)) return null;
		for (WebMenuItem item: menuBar) {
			if (item.getTag().equals(path)) {
				return item;
			}
		}
		return null;
	}

	public void addMenuItem(String path, Class<?> clazz, Method method) {
		String[]	paths = path.split("/");
		
		logger.info(path + ", " + clazz.getName() + ", " + (method == null ? "null" : method.getName()));
		List<WebMenuItem>	list = menuBar;
		WebMenuItem parent = null;
		
		
		for (int i = 1; i < paths.length; i++) {
			WebMenuItem	ptr = null;
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).getTag().equals(paths[i])) {
					ptr = list.get(j);
					break;
				}
			}
			
			if (ptr == null) {
				ptr = new WebMenuItem();
				ptr.setTag(paths[i]);
				ptr.setParent(parent);
				list.add(ptr);
				//System.out.printf("[%s][%d][%s] (New)%n", path, i, paths[i]);
			} else {
				//System.out.printf("[%s][%d][%s] (Old)%n", path, i, paths[i]);
			}

			if (i == paths.length - 1) {
				ptr.setClazz(clazz);
				ptr.setMethod(method);
				
				addRequiredRoles(ptr, clazz.getAnnotation(MenuItem.class));
				
				if (method != null) {
					addRequiredRoles(ptr, method.getAnnotation(MenuItem.class));		
				}
			}
			
			list = ptr.getSubMenu();
			parent = ptr;
		}
	}

	private void addRequiredRoles(WebMenuItem ptr, MenuItem menuItem) {
//		if (menuItem != null) {
//			String[] roles = menuItem.role();
//			if (roles != null && rolesManager != null) {
//				for (int j = 0; j < roles.length; j++) {
//					if ("".equals(roles[j])) continue;
//					IUserRole<?> r = rolesManager.getRole(roles[j]);
//					if (r != null) {
//						ptr.addRole(r);
//					} else {
//						logger.warn("Role: '" + roles[j] + "' not found (" +
//								ptr.getClazz().getName() + " [" +
//								(ptr.getMethod() == null ? "" : ptr.getMethod().getName()) +
//								"])"
//						);
//					}
//				}
//			}
//		}
	}
	
	private void printMenu(List<WebMenuItem> list, int level) {
		for (int i = 0; i < list.size(); i++) {
			String leadingBlank = "";
			for (int j = 0; j < level; j++) {
				leadingBlank += "   ";
			}
			logger.info(leadingBlank + "[" + list.get(i).getTag() + "]");
			printMenu(list.get(i).getSubMenu(), level + 1);
		}
	}
	public void printMenu() {
		printMenu(this.menuBar, 0);
	}
}
