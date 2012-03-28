package ncu.cc.webdev.services;

import java.util.Comparator;

import ncu.cc.webdev.domain.WebMenuItem;

public class WebMenuItemComparator implements Comparator<WebMenuItem> {
	@Override
	public int compare(WebMenuItem arg0, WebMenuItem arg1) {
		return arg0.getOrder() - arg1.getOrder();
	}
}
