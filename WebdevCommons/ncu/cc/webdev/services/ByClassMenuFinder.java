package ncu.cc.webdev.services;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import ncu.cc.webdev.annotations.MenuItem;
import ncu.cc.webdev.domain.WebMenuBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class ByClassMenuFinder implements IMenuFinder {
	private static final Logger logger = LoggerFactory.getLogger(ByClassMenuFinder.class);
	private List<String>		packageName;
	private IClassFinder		finder;

	public void setPackageName(List<String>packageName) {
		this.packageName = packageName;
	}

	public IClassFinder getFinder() {
		return finder;
	}

	public void setFinder(IClassFinder finder) {
		this.finder = finder;
	}

	public void addItemsToMenuBar(WebMenuBar menuBar) {
		try {
			for (String name: packageName) {
				Class<?>[] classes = finder.findClassesByPackage(name);
				
				for (int i = 0; i < classes.length; i++) {
					findMenuByClass(menuBar, classes[i]);
				}
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void findMenuByClass(WebMenuBar menuBar, Class<?> clazz) {
		String[]	urls;
		
		if (clazz.isAnnotationPresent(Controller.class)) {
			urls = null;
			if (clazz.isAnnotationPresent(RequestMapping.class)) {			
				urls = clazz.getAnnotation(RequestMapping.class).value();

				if (clazz.isAnnotationPresent(MenuItem.class)) {
					for (int i = 0; i < urls.length; i++) {
						analyzePath(menuBar, urls[i], clazz, null);
					}
				}
			}
			Method[] methods = clazz.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				findMenuByMethod(menuBar, clazz, urls, methods[i]);
			}
		}
	}

	private void findMenuByMethod(WebMenuBar menuBar, Class<?> clazz, String[] parentUrls, Method method) {
		if (method.isAnnotationPresent(RequestMapping.class)) {
			if (method.isAnnotationPresent(MenuItem.class)) {
				String[] urls = method.getAnnotation(RequestMapping.class).value();
				if (parentUrls != null && parentUrls.length >= 1) {
					for (int j = 0; j < parentUrls.length; j++) {
						for (int i = 0; i < urls.length; i++) {
							analyzePath(menuBar, parentUrls[j] + urls[i], clazz, method);
						}
					}
				} else {
					for (int i = 0; i < urls.length; i++) {
						analyzePath(menuBar, urls[i], clazz, method);
					}
				}
			}
		}
	}

	private void analyzePath(WebMenuBar menuBar, String path, Class<?> clazz, Method method) {
//		String[]	paths = path.split("/");
		logger.info(path);
		menuBar.addMenuItem(path, clazz, method);
//		
//		System.out.printf("{%s}:", clazz.getName());
//		for (int i = 1; i < paths.length; i++) {
//			System.out.printf("[%s]", paths[i]);
//		}
//		if (method != null) {
//			System.out.printf("  Method:[%s]", method.getName());
//		}
//		System.out.println();
	}
}
