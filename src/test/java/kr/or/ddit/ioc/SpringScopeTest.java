package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:kr/or/ddit/ioc/application_context.xml"})
public class SpringScopeTest {

	@Resource(name="boardDao")
	private IBoardDao boardDao;
	
	@Resource(name="boardDaoSingleton")
	private IBoardDao boardDaoSingleton;
	
	@Resource(name="boardDaoSingleton")
	private IBoardDao boardDaoSingleton2;
	
	/**
	 * 스프링의 scope singleton 테스트
	 */
	@Test
	public void springScopeTest() {
		
		/***Given***/

		/***When***/

		/***Then***/
		assertNotEquals(boardDao, boardDaoSingleton);
		assertEquals(boardDaoSingleton2, boardDaoSingleton);
	}

}
