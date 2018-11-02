package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVO;

public interface IBoardService {

	List<BoardVO> selectBoardList();
	
	public IBoardDao getBoardDao();

	void setBoardDao(IBoardDao boardDao);
}
