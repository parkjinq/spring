package kr.or.ddit.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
public class JsonViewController {

	@ModelAttribute
	public void modelAttribute(Model model) {
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		//{rangers : ["brown", "cony", "sally"]}
		
		model.addAttribute("rangers", rangers);
	}
	
	@RequestMapping("/rangersJsonViewObj")
	public View rangersJsonViewObj() {
		
		//json생성을 담당하는 view
		
		return new MappingJackson2JsonView();
	}
	
	//json 응답을 생성하는 url 요청
	//rangers 정보를 json으로 응답을 생성
	@RequestMapping("/rangersJsonView")
	public String rangersJsonView() {
		
		//컬렉션 object정보를 json문자열로 생성
		
		//json을 생성하는 view
		return "jsonView";

		//servlet-context에서 이걸 쓰겠다.
		//<!-- JSON 처리용 view -->
		//<bean id="jsonView" class="org.springframework.web.servlet.view.json.MappingJackson2JsonView">
		//</bean>
		//controller에서 리턴하는 viewName(jsonView)를 처리하기 위해
		//처리해줄 viewResolver를 우선순위에 따라서
		//1.beanNameViewResolver를 통해 view를 생성하려고 시도하게됨
		//2.jsonView하고 하는 이름의 빈이 있는지를 확인
		//	>존재
		//3.해당 viewName과 동일한 이름의 빈이 있으므로
		//	internalResourceViewResolver 까지 가지 않고 beanNameViewResolver에서 처리하게 됨
		
		//만약 viewname이 "hello"인 경우라면
		//처리해줄 viewResolver를 우선순위에 따라서
		//1.beanNameViewResolver를 통해 view를 생성하려고 시도하게됨
		//2.jsonView하고 하는 이름의 빈이 있는지를 확인
		// <bean id = "hello"... 없음
		//3.다음 우선순위인 internalResourceViewResolver가 처리하게됨
		// internalResourceViewResolver는 해당 리소스(hello.jsp)가 있는지 여부를 확인하지 않고 prefix, suffix를 무조건 반영하여 forword합니다.
		//그렇기 떄문에 오타, 혹은 존재하지 않는 파일(jsp)를 리턴하게되면 에러가 발생함
		//그런이유로 internalResourceViewResolver는 viewResolver 설정시 우선순위를 최하위로 한다.
	}
}
