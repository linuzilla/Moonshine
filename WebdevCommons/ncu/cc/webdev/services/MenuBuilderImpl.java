package ncu.cc.webdev.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ncu.cc.webdev.domain.WebMenuNavigator;
import ncu.cc.webdev.domain.WebMenuItem;

public class MenuBuilderImpl implements IMenuBuilder {
	private static final Logger logger = LoggerFactory.getLogger(MenuBuilderImpl.class);
	
	private List<IMenuFinder>	menuFinders;
	private WebMenuNavigator		nagivatorMenu;
	final private WebMenuItemComparator	comparator = new WebMenuItemComparator();
	
	public List<IMenuFinder> getMenuFinders() {
		return menuFinders;
	}

	public void setMenuFinders(List<IMenuFinder> menuFinders) {
		this.menuFinders = menuFinders;
	}

	public void init() {
		nagivatorMenu = new WebMenuNavigator();
		
		for (IMenuFinder menuFinder: this.menuFinders) {
			menuFinder.addItems(nagivatorMenu);
		}
		
		nagivatorMenu.reorder(comparator);
		
		logger.info("initialized");
	}

	public WebMenuNavigator getNagivatorMenu() {
		return nagivatorMenu;
	}

	@Override
	public WebMenuItem findMenuItem(List<String> pathList) {
		return findMenuItem(null, this.nagivatorMenu.getNavigatorBar(), pathList, 0);
	}

	private WebMenuItem findMenuItem(WebMenuItem rc, List<WebMenuItem> list,
			List<String> pathList, int i) {
		if (pathList.size() > i) {
			for (WebMenuItem item: list) {
				if (item.getTag().equals(pathList.get(i))) {
					return findMenuItem(item, item.getSubMenu(), pathList, i + 1);
				}
			}
		}
		return rc;
	}
}
