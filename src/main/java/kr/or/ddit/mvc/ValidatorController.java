package kr.or.ddit.mvc;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.model.UserVOJsr303;
import kr.or.ddit.user.model.UserVOValidator;

@RequestMapping("/validator")
@Controller
public class ValidatorController {

	//validator를 테스트 할 view(/WEB-INF/view/validator/view.jsp)
	//httpMethod : get
	@RequestMapping(value = "/view", method=RequestMethod.GET)
	public String view() {
		
		return "validator/view";
	}
	
	//view에서 입력한 값을 처리할 method
	//검증절차에서 문제가 있을 경우 최초 테스트 view에 가서 메세지를 출력
	//검증절차에 문제가 없을 경우 result.jsp로 이동
	//(/WEB-INF/view/validator/result.jsp)
	//httpMethod : post
	
	//BindingResult 객체는 검증하고자 하는 vo객체 뒤에 메서드 인자로 넣어야한다
	//메서드 인자 순서 주위
	@RequestMapping(value = "/validate", method=RequestMethod.POST)
	public String validate(UserVO userVO, BindingResult bindingResult, Model model) {
		new UserVOValidator().validate(userVO, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "validator/view";
		}
		
		model.addAttribute("userVO", userVO);
		return "validator/result";
	}
	
	@RequestMapping(value = "/validateJsr", method=RequestMethod.POST)
	public String validateJsr(@Valid UserVOJsr303 userVOJsr303, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "validator/view";
		}
		
		model.addAttribute("userVO", userVOJsr303);
		return "validator/result";
	}
	
}
