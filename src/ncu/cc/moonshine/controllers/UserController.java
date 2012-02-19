package ncu.cc.moonshine.controllers;

import ncu.cc.moonshine.domain.User;
import ncu.cc.moonshine.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
	private @Autowired IUserService	userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("userList", userService.findAll());
		return "user/home";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute("userBean", new User());
		return "user/add";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addUser(@ModelAttribute("userBean") User userBean, Model model) {
		userService.addUser(userBean);
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/modify/{userid}", method=RequestMethod.GET)
	public String modifyUser(@PathVariable Integer userid) {
		return "user/modify";
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String deleteUser(@RequestParam("userId") Integer userid) {
		return "user/delete";
	}
}
