package ncu.cc.webdev.domain;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WebMenuBar {
	private List<WebMenuItem>	menuBar = new ArrayList<WebMenuItem>();
	
	public void addMenuItem(String path, Class<?> clazz, Method method) {
		String[]		paths = path.split("/");
	}
}
