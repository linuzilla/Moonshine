package ncu.cc.moonshine.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/topbar")
public class TopbarController {
	@RequestMapping("/about")
	public String about(ModelMap model) {
		return "topbar/about";
	}
	
	@RequestMapping("/sitemap")
	public String siteMap(ModelMap model) {
		return "topbar/sitemap";
	}
}
