package ncu.cc.webdev.services;

import java.util.List;

import ncu.cc.webdev.domain.WebMenuNavigator;
import ncu.cc.webdev.domain.WebMenuItem;

public interface IMenuFinder {
	public void addItems(WebMenuNavigator menuBar);
}
