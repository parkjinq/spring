package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.config.db.SqlFactoryBuilder;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.util.model.PageVO;

@Repository
public class ProdDao implements IProdDao{
	
	private SqlSessionFactory factory = null;
	private SqlSession session = null;
	
	private ProdDao() {
		factory = SqlFactoryBuilder.getSqlSessionFactory();
		session = factory.openSession();
	}
	
	@Override
	public List<ProdVO> selectProdPageList(PageVO pageVO) {
		List<ProdVO> pdList = session.selectList("prod.selectProdPageList", pageVO);
		return pdList;
	}

	@Override
	public ProdVO selectProd(String prod_id) {
		ProdVO prodVO = session.selectOne("prod.selectProd", prod_id); 
		return prodVO;
	}

	@Override
	public int getProdCnt() {
		int pdCnt = session.selectOne("prod.getProdCnt");
		return pdCnt;
	}

	@Override
	public List<ProdVO> selectProdByLgu(String prod_lgu) {
		List<ProdVO> prodList = session.selectList("board.selectProdByLgu", prod_lgu);
		return prodList;
	}

}
