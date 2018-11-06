package kr.or.ddit.mvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;
import kr.or.ddit.test.ControllerTestConfig;
import kr.or.ddit.util.FileUtil;

public class MvcControllerTest extends ControllerTestConfig {

	private Logger logger = (Logger) LoggerFactory.getLogger(MvcController.class);
	

	@SuppressWarnings("unchecked")
	@Test
	public void mvcControllerTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(post("/mvc/view").param("userId", "brown")).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
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
