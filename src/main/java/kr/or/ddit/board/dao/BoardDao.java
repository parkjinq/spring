package kr.or.ddit.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVO;

//어노테이션을 통한 빈 등록
//bean 어노테이션  : @Controller, @Service, @Repository, @Component
//controller, service, dao, 일반객체
//@Controller, @Service, @Repository > @Component 기반 확장 어노테이션
//어노테이션 자체가 특별한 기능을 담고 있는 것은 아님
//개발에 자주 사용되는 모듈을 분리하여 명명한 것에 불과

/**
 * BoardDao.java
 * @author jin
 * @version 1.0
 * 
 * 
 */

//이름을 생략하면 클래스 첫글자를 소문자로 한 이름이 spring bean 이름이 된다.
@Repository("boardDao")
public class BoardDao implements IBoardDao {

	@Override
	public List<BoardVO> selectBoardList() {
		//db에서 조회 해온 데이터라고 가정하자
		BoardVO boardVO1 = new BoardVO(1, "자유게시판", "brown");
		BoardVO boardVO2 = new BoardVO(2, "공지사항", "cony");
		BoardVO boardVO3 = new BoardVO(3, "질의응답", "sally");
		List<BoardVO> boardVOs = new ArrayList<BoardVO>();
		boardVOs.add(boardVO1);
		boardVOs.add(boardVO2);
		boardVOs.add(boardVO3);
		return boardVOs;
	}

}
