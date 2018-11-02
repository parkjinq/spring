package kr.or.ddit.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;

@Configuration
public class JavaConfig {
	
	@Bean(name="boardDao")
	public IBoardDao getBoardDao() {
		return new BoardDao();
	}
	
	@Bean(name= "boardService")
	public IBoardService boardService() {
		IBoardService boardService = new BoardService();
		boardService.setBoardDao(getBoardDao());
		return boardService;
	}
}
