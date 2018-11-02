package kr.or.ddit.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;
import kr.or.ddit.user.model.UserVO;

/*
 * servlet
 * 1.HttpServlet 클래스를 상속
 * 2.@WebServlet 혹은 web.xml에 url-mapping 등록
 * 3.doGet, doPost같은 doXXX메소드를 통해 서비스를 개발
 * 
 * spring controller
 * 1.@Controller 어노테이션 적용
 * 2.@RequestMapping 어노테이션 적용(class / method)
 */

@RequestMapping("/hello")
@Controller
public class HelloController {
	
	private Logger logger = (Logger) LoggerFactory.getLogger(HelloController.class);
	
	//사용자 요청 : localhost:8081/hello/hello.do url로 요청을 하게 되면 아래 메서드에서 요청처리
	//만약 클래스에 적용한 @RequestMapping("/hello") 부분을 삭제하게되면
	//localhost:8081/hello.do url로 요청에 대해 hello() 메서드에서 요청을 처리하게됨
	@RequestMapping("/hello.do")
	public String hello() {
	
		//viewName을 리턴
		//internalResourceViewResolver 설정에 의해
		//prefix + viewName + suffix 위치의 리소스로 응답을 위임한다.
		//prefix : /WEB-INF/view
		//suffix : .jsp
		//viewName : hello
		//>>/WEB-INF/view/hello.jsp
		//기본 : forward
		return "hello";
	}
	
	/*
	 * servlet doGet, doPost : 메서드인자 HttpServletRequest, HttpServletResponse 2개 고정 
	 * spring controller 메서드 : 비교적 자유롭게 구성이 가능
	 * 							HttpServletRequest, HttpServletResponse
	 * 							HttpSession,
	 * 							ValueObject,
	 * 							Model : view에서 표현할 데이터를 저장(request와 유사, 그러나 차이가 있음)
	 */
	@RequestMapping("/model")
	public String model(Model model) {
		List<String> rangers = new ArrayList<>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		
		//sevlet : request.setAttribute();
		model.addAttribute("rangers", rangers);
		
		
		return "hello";
	}
	
	@RequestMapping("/request")
	public String request(HttpServletRequest request, Model model) {
		
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		model.addAttribute("userId", userId);
		model.addAttribute("pass", pass);
		
		logger.debug("userId : " + userId);
		logger.debug("password : " + pass);
		
		return "hello";
	}
	
	//ValueObject의 속성이름과 동일한 이름의 파라미터를 자동으로 설정해준다.
	@RequestMapping("/vo")
	public String vo(UserVO userVO, Model model) {
		logger.debug("userVO : " + userVO);
		
		model.addAttribute("userVO", userVO);
		
		return "hello";
	}
	
	//spring mvc controller 메소드의 리턴타입
	//1.String : viewName
	//2.ModelAndView : 스프링 제공 객체
	//3.void : response 객체에 개발자가 직접 응답을 작성
	//ModelAndView
	@RequestMapping("/modelAndView")
	public ModelAndView modelAndView(HttpServletRequest request, ModelAndView mav) {
		
		List<String> rangers = new ArrayList<>();
		rangers.add(request.getParameter("ranger1"));
		rangers.add(request.getParameter("ranger2"));
		rangers.add(request.getParameter("ranger3"));
		
		mav.addObject("rangers", rangers);
		mav.setViewName("hello");
		
		return mav;
	}
	
	//void
	@RequestMapping("/void")
	public void voidMethod(HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		
		writer.write("<html>");
		writer.write("spring voidMethod");
		writer.write("</html>");
	}
}




























