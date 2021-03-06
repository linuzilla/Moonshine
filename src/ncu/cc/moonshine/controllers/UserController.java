package ncu.cc.moonshine.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ncu.cc.moonshine.domain.Role;
import ncu.cc.moonshine.domain.User;
import ncu.cc.moonshine.domain.formbeans.UserFormBean;
import ncu.cc.moonshine.services.IRoleService;
import ncu.cc.moonshine.services.IUserService;
import ncu.cc.moonshine.services.SerializationService;
import ncu.cc.webdev.annotations.MenuItem;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@MenuItem(order=100, authorities={ "ROLE_ADMIN" })
@RequestMapping("/user")
public class UserController {
	@Autowired
	@Qualifier("dao")
	private IUserService		userService;
	@Autowired
	private IRoleService		roleService;
	@Autowired
	private SerializationService	serializationService;
	
	@MenuItem
	@RequestMapping(method=RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("userList", userService.findAll());
		return "user/home";
	}
	
	@MenuItem
	@RequestMapping(value = "/dwr", method=RequestMethod.GET)
	public String dwr(Model model) {
		return "user/dwr";
	}
	
	@ResponseBody
	@RequestMapping(value = "/json/{userName}", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	public String jsonGetUser(@PathVariable String userName) {
        String rc = serializationService.jsonSerialize(userService.getUserByName(userName));
        // System.out.println(rc);
        return rc;
	}
	
	@MenuItem
	@ResponseBody
	@RequestMapping(value = "/json", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	public String jsonGetAll() {
        String rc = serializationService.jsonSerialize(userService.findAll());
        // System.out.println(rc);
        return rc;
	}
	
	@MenuItem(authorities="ROLE_ADMIN")
	@RequestMapping(value = "/add", method=RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute("userBean", new UserFormBean());
		// model.addAttribute("userBean", new User());
		model.addAttribute("roles", roleService.findAll());
		return "user/add";
	}


	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addUser(@ModelAttribute("userBean") UserFormBean userBean, Model model)  {
		User user = userFrom2user(new User(), userBean);
		userService.addUser(user);
		return "redirect:/user";
	}

	private User userFrom2user(User user, UserFormBean userBean) {	
		BeanUtils.copyProperties(userBean, user);
		
		if (userBean.getRoleNames() != null) {
			List<Role> roleList = new ArrayList<Role>();
			
			for (Role role: roleService.findAll()) {
				for (String roleName: userBean.getRoleNames()) {
					if (roleName.equals(role.getRoleName())) {
						roleList.add(role);
					}
				}
			}
			user.setRoles(roleList);
		}
		return user;
	}
	
	@RequestMapping(value = "/modify/{userid}", method=RequestMethod.GET)
	public String modifyUser(@PathVariable Integer userid, Model model)  {
		UserFormBean formBean = new UserFormBean();
		User user = userService.getUserById(userid);
		BeanUtils.copyProperties(user, formBean);
		if (user.getRoles() != null && user.getRoles().size() > 0) {
			Set<String> roleNames = new HashSet<String>();
			for (Role role: user.getRoles()) {
				roleNames.add(role.getRoleName());
			}
			formBean.setRoleNames(roleNames);
		}
		model.addAttribute("userBean", formBean);
		model.addAttribute("roles", roleService.findAll());
		return "user/modify";
	}
	
	@RequestMapping(value = "/modify/{userid}", method=RequestMethod.POST)
	public String modifyUser(@ModelAttribute("userBean") UserFormBean userBean, @PathVariable Integer userid, Model model) {
		User u = userService.getUserById(userid);
		userBean.setUserId(u.getUserId());
		userBean.setCreatedBy(u.getCreatedBy());
		userService.modifyUser(userFrom2user(u, userBean));
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String deleteUser(@RequestParam("userId") Integer userid) {
		userService.deleteUser(userService.getUserById(userid));
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/delete/{userid}", method=RequestMethod.DELETE)
	public String deleteUserViaREST(@PathVariable Integer userid) {
		userService.deleteUser(userService.getUserById(userid));
		return "redirect:/user";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException(RuntimeException e) {
		e.printStackTrace();
		return "redirect:/";
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleException(Exception e) {
		return e.getMessage();
	}
}