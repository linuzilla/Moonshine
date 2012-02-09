package ncu.cc.moonshine;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/topbar")
public class TopbarController {
	

	@RequestMapping("/about")
	@ResponseBody
	public String about(ModelMap model) {
		return "topbar/about";
	}
}
