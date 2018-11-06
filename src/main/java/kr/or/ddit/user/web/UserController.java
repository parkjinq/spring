package kr.or.ddit.user.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@RequestMapping("/loginView")
	public String loginView() {
		
		return "login/login";
	}
	
	@RequestMapping(value="/loginProcess", method=RequestMethod.POST)
	public String loginProcess(HttpServletRequest request) {
		
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		if("brown".equals(userId) && "brownpass".equals(pass)) {
			return "main";
		} else {
			return "login/login";
		}
	}
}
