package ncu.cc.moonshine.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import ncu.cc.webdev.annotations.MenuItem;
import ncu.cc.webdev.util.StackTraceUtil;

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

	@MenuItem(order=900)
	@RequestMapping(value="tilesDemo", method = RequestMethod.GET)
	public String tilesDemo() {
		return "demo/tiles";
	}
	
	@RequestMapping(value="uncaughtException", method = RequestMethod.GET)
	public String uncaughtException() {
		StackTraceUtil.print1("uncaughtException");
		return "uncaughtException";
	}
	
	@RequestMapping(value="resourceNotFound", method = RequestMethod.GET)
	public String resourceNotFound(HttpServletRequest req) {
		String uri;
		
		try {
			uri = URLDecoder.decode((String) req.getAttribute("javax.servlet.error.request_uri"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			uri = "";
		}
		
		String message = (String) req.getAttribute("javax.servlet.error.message");
		Integer code = (Integer) req.getAttribute("javax.servlet.error.status_code");
		
		String referer = req.getHeader("referer");
		
		StackTraceUtil.print1("RequestURI=" + uri);
		StackTraceUtil.print1("Message=" + message);
		StackTraceUtil.print1("Code=" + code);
		StackTraceUtil.print1("Referer:" + referer);
		
		return "resourceNotFound";
	}
}
