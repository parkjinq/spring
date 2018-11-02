package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.classic.Logger;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:kr/or/ddit/ioc/application_context_scan.xml"})
public class ComponentScanTest {
	
	private Logger logger =(Logger) LoggerFactory.getLogger(ComponentScanTest.class);

	@Resource(name="boardDao")
	private IBoardDao boardDao;
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	/**
	 * spring annotation scan을 통해 스프링 빈 생성이 정상적으로 되었는지 테스트
	 */
	@Test
	public void ComponentScanTest() {

		/***Given***/

		/***When***/
		List<BoardVO> boardVOs1 = boardDao.selectBoardList();
		for(BoardVO board : boardVOs1) {
			logger.debug("{}", board);
		}

		List<BoardVO> boardVOs2 = boardService.selectBoardList();
		for(BoardVO board : boardVOs2) {
			logger.debug("{}", board);
		}
		
		/***Then***/
		assertNotNull(boardDao);
		assertNotNull(boardService);
	}
	
	/**
	 * application_context에서 싱글톤으로 설정되었는 boardDao의 dao와 
	 * boardService를 통해 얻은 dao(이 dao도 결국은 같은 빈으로 생성되는 거임)가 같은지 비교하는 테스트
	 */
	@Test
	public void daoSingletonTest() {
		/***Given***/

		/***When***/

		/***Then***/
		assertEquals(boardDao, boardService.getBoardDao());
	}

}
