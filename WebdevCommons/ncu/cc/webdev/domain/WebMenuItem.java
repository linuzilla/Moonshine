package ncu.cc.webdev.domain;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WebMenuItem implements Serializable {
	private static final long serialVersionUID = 3120179413720631830L;
	private String				tag;
	private int					order;
	private List<WebMenuItem>	subMenu = new ArrayList<WebMenuItem>();
	private Class<?>				clazz;
	private transient Method		method;
	private Set<String>			authorities;
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
	public Set<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}
}
