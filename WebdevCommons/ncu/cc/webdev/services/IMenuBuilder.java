package ncu.cc.webdev.services;

import java.util.List;

import ncu.cc.webdev.domain.WebMenuItem;
import ncu.cc.webdev.domain.WebMenuNavigator;

public interface IMenuBuilder {
	public WebMenuItem findMenuItem(List<String> pathList);
	public WebMenuNavigator getNagivatorMenu();
}
