package kr.or.ddit.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/servlet-context.xml"
								 , "classpath:kr/or/ddit/config/spring/root-context.xml"})
@WebAppConfiguration
public class ControllerTestConfig {
	
	//webApplicationContext > mockMvc(dispatcherServlet)생성을 위해 필요
	@Autowired
	private WebApplicationContext ctx;	//spring ioc 컨테이너
	
	protected MockMvc mockMvc;			//dispatcher servlet(front controller)
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Ignore
	@Test
	public void test() {
		
	}
}
