package ncu.cc.moonshine.controllers;

import ncu.cc.webdev.annotations.MenuItem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {
	@RequestMapping(method = RequestMethod.GET)
	// @ResponseBody
	public String home() {
		return "index";
	}

	@MenuItem
	@RequestMapping(value="tilesDemo", method = RequestMethod.GET)
	public String tilesDemo() {
		return "demo/tiles";
	}
}
