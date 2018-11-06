package kr.or.ddit.hello;

import static org.junit.Assert.*;
//import static class명 없이 클래스를 사용한다.
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;
import kr.or.ddit.user.model.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:kr/or/ddit/config/spring/servlet-context.xml"
								, "classpath:kr/or/ddit/config/spring/root-context.xml"})
@WebAppConfiguration					//spring ioc 컨테이너 구성을 web 환경에 맞게 구성
public class HelloControllerTest {

	private Logger logger = (Logger) LoggerFactory.getLogger(HelloControllerTest.class);
	
	@Autowired
	private WebApplicationContext ctx;	//spring ioc 컨테이너
	
	private MockMvc mockMvc;			//dispatcher servlet(front controller)
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	/**
	 * 
	* Method : test
	* 작성자 : jin
	* 변경이력 :
	* Method 설명 : Controller에서 반환하는 값과 view이름이 동일한지 비교하는 테스트
	 * @throws Exception 
	 */
	@Test
	public void helloTest() throws Exception {
		/***Given***/

		/***When***/
		//dispatcher servlet으로 요청 전송
		//Line 4 : get() 왜 사용가능한지 있음
		MvcResult mvcResult = mockMvc.perform(get("/hello/hello.do")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("hello", mav.getViewName());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void modelTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/hello/model")).andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<String> rangers = (List<String>) mav.getModel().get("rangers");
//		mav.getModelMap().get("rangers");
		
		for(String str : rangers) {
			logger.debug("ranger : {}", str);
		}
		
		/***Then***/
		//1.view 이름
		assertEquals("hello", viewName);
		
		//2.rangers 속성(model)
		assertEquals(3, rangers.size());
	}
	
	@Test
	public void requestTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/hello/request").param("userId", "brown").param("pass", "brownpass")).andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		String userId = (String) mav.getModel().get("userId");
		String pass = (String) mav.getModel().get("pass");
		
		/***Then***/
		assertEquals("hello", viewName);
		assertEquals("brown", userId);
		assertEquals("brownpass", pass);
	}
	
	@Test
	public void vo() throws Exception {
		/***Given***/
		UserVO userVO = new UserVO();
		userVO.setUserId("cony");
		userVO.setPass("conypass");
		MvcResult mvcResult = mockMvc.perform(get("/hello/vo").param("userId", "cony").param("pass", "conypass")).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO result = (UserVO) mav.getModel().get("userVO");
		
		/***Then***/
		assertEquals("hello", viewName);
		assertEquals("cony", result.getUserId());
		assertEquals("conypass", result.getPass());

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void modelAndView() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/hello/modelAndView").param("ranger1", "brown").param("ranger2", "cony").param("ranger3", "sally")).andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<String> result = (List<String>) mav.getModel().get("rangers");
		for(String str : result) {
			logger.debug("ranger : {}", str);
		}
		
		/***Then***/
		assertEquals("hello", viewName);
		assertEquals(3, result.size());
	}

}
