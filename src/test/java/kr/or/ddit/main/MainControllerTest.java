package kr.or.ddit.main;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.ControllerTestConfig;

public class MainControllerTest extends ControllerTestConfig {
	
	//시간제한도 걸 수 있다 milliseconds
	@Test(timeout=5000)
	public void mainControllerTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/main")).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("main", viewName);
	}

}
