package ncu.cc.moonshine.controllers;

import ncu.cc.moonshine.domain.Role;
import ncu.cc.moonshine.services.IRoleService;
import ncu.cc.webdev.annotations.MenuItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@MenuItem(order=200)
@RequestMapping("/role")
public class RoleController {
	public static final String	HOME_PAGE = "role/home";
	public static final String	ADD_PAGE = "role/add";
	public static final String	ADD_OK = "redirect:/role";
	public static final String	MODIFY_PAGE = "role/modify";
	public static final String	MODIFY_OK = "redirect:/role";
	public static final String	DELETE_OK = "redirect:/role";
	public static final String	ERROR_PAGE = "redirect:/";
	public static final String	DWR_PAGE = "role/dwr";
	
	@Autowired
	private IRoleService roleService;
	
	@MenuItem
	@RequestMapping(method=RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("roles", roleService.findAll());
		return HOME_PAGE;
	}
	
	@MenuItem
	@RequestMapping(value = "/add", method=RequestMethod.GET)
	public String addRole(ModelMap model) {
		model.addAttribute("roleBean", new Role());
		return ADD_PAGE;
	}
	
	@MenuItem
	@RequestMapping(value = "/dwr", method=RequestMethod.GET)
	public String dwr() {
		return DWR_PAGE;
	}

	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addRole(@ModelAttribute("roleBean") Role userRole, ModelMap model) {
		roleService.addRole(userRole);
		return ADD_OK;
	}
	
	@RequestMapping(value = "/modify/{roleId}", method=RequestMethod.GET)
	public String modifyRole(@PathVariable Integer roleId, ModelMap model) {
		model.addAttribute("roleBean", roleService.getRoleById(roleId));
		return MODIFY_PAGE;
	}
	
	@RequestMapping(value = "/modify/{roleId}", method=RequestMethod.POST)
	public String modifyUser(@ModelAttribute("roleBean") Role roleBean, @PathVariable Integer roleId, ModelMap model) {
		roleBean.setRoleId(roleId);
		roleService.modifyRole(roleBean);
		return MODIFY_OK;
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String deleteUser(@RequestParam("roleId") Integer roleId) {
		roleService.deleteRoleById(roleId);
		return DELETE_OK;
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException(RuntimeException e) {
		e.printStackTrace();
		return ERROR_PAGE;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleException(Exception e) {
		return e.getMessage();
	}
}
