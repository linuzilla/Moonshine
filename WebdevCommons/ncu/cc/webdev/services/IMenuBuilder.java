package ncu.cc.webdev.services;

import java.util.List;

import ncu.cc.webdev.domain.WebMenuItem;

public interface IMenuBuilder {
	public WebMenuItem findMenuItem(List<String> pathList);
}
