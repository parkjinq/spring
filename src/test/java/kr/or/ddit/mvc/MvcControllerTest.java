package kr.or.ddit.mvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
import kr.or.ddit.util.FileUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:kr/or/ddit/config/spring/servlet-context.xml"
								, "classpath:kr/or/ddit/config/spring/root-context.xml"})
@WebAppConfiguration
public class MvcControllerTest {

	private Logger logger = (Logger) LoggerFactory.getLogger(MvcController.class);
	
	@Autowired
	private WebApplicationContext ctx;	//spring ioc 컨테이너
	
	private MockMvc mockMvc;			//dispatcher servlet(front controller)
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void mvcControllerTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(post("/mvc/view").param("userId", "brown")).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<String> rangers = (List<String>) mav.getModel().get("rangers");

		/***Then***/
		assertEquals(4, rangers.size());
	}
	
	@Test
	public void fileuploadView() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/mvc/fileuploadView")).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals("mvc/fileupload", mav.getViewName());
	}
	
	//확장자가 있는 경우
	@Test
	public void fileExtTest() {
		/***Given***/
		String fileName = "j.i.n.jpg";
		
		/***When***/
		String fileExt = FileUtil.getFileExt(fileName);
		logger.debug("fileExt : {}", fileExt);
		
		/***Then***/
		assertEquals(".jpg", fileExt);
	}
	
	//확장자가 없는 경우
	@Test
	public void fileExtTest2() {
		/***Given***/
		String fileName = "jin";
		
		/***When***/
		String fileExt = FileUtil.getFileExt(fileName);
		logger.debug("fileExt2 : {}", fileExt);
		
		/***Then***/
		assertEquals("", fileExt);
	}

}
