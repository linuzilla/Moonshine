package ncu.cc.webdev.services;

import java.util.List;

import ncu.cc.webdev.domain.WebMenuBar;

public class MenuBuilderImpl implements IMenuBuilder {
	private List<IMenuFinder>	menuFinders;
	private WebMenuBar			menuBar;
	
	public List<IMenuFinder> getMenuFinders() {
		return menuFinders;
	}

	public void setMenuFinders(List<IMenuFinder> menuFinders) {
		this.menuFinders = menuFinders;
	}

	public void init() {
		menuBar = new WebMenuBar();
		
		for (IMenuFinder menuFinder: this.menuFinders) {
			menuFinder.addItemsToMenuBar(menuBar);
		}
	}
}
