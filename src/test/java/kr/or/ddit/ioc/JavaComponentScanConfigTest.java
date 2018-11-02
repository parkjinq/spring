package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {JavaComponentScanConfig.class})
public class JavaComponentScanConfigTest {

	@Resource(name="boardDao")
	private IBoardDao boardDao;
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	/**
	 * javaConfig를 통해 스프링 빈이 정상적으로 생성죄었는지 테스트
	 */
	@Test
	public void javaConfigTest() {
		/***Given***/

		/***When***/

		/***Then***/
		assertNotNull(boardDao);
		assertNotNull(boardService.getBoardDao());
		assertEquals(boardDao, boardService.getBoardDao());
	}

}
