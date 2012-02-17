package ncu.cc.moonshine.controllers;

import java.util.List;

import ncu.cc.moonshine.domain.User;
import ncu.cc.moonshine.services.IUserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	private IUserService	userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("userList", userService.findAll());
		return "user/home";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.GET)
	public String addUser() {
		return "user/add";
	}
	
	@RequestMapping(value = "/modify/{userid}", method=RequestMethod.GET)
	public String modifyUser(@PathVariable Integer userid) {
		return "user/modify";
	}
	
	@RequestMapping(value = "/delete/{userid}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable Integer userid) {
		return "user/delete";
	}
}
