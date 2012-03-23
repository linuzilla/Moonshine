package ncu.cc.webdev.domain;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WebMenuItem {
	private String				tag;
	private int					order;
	private List<WebMenuItem>	subMenu = new ArrayList<WebMenuItem>();
	private Class<?>				clazz;
	private Method				method;
	private WebMenuItem			parent = null;
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public List<WebMenuItem> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<WebMenuItem> subMenu) {
		this.subMenu = subMenu;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public WebMenuItem getParent() {
		return parent;
	}
	public void setParent(WebMenuItem parent) {
		this.parent = parent;
	}
}
