package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVO;

@Service("boardService")
public class BoardService implements IBoardService {
	
	@Resource(name ="boardDao")
	private IBoardDao boardDao;
	
	public BoardService() {
	}

	public BoardService(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public IBoardDao getBoardDao() {
		return boardDao;
	}
	public void setBoardDao(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public List<BoardVO> selectBoardList() {
		return boardDao.selectBoardList();
	}

}
