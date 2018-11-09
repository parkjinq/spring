package kr.or.ddit.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDao;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.util.model.PageVO;

@Service
public class ProdService implements IProdService {

	@Resource(name = "prodDao")
	private IProdDao prodDao;
	
	@Override
	public Map<String, Object> selectProdPageList(PageVO pageVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<ProdVO> pdList = prodDao.selectProdPageList(pageVO);
		int pdCnt = prodDao.getProdCnt();
		
		resultMap.put("pdList", pdList);
		resultMap.put("pdCnt", (int) (Math.ceil((double)pdCnt / pageVO.getPageSize())));
		
		return resultMap;
	}

	@Override
	public ProdVO selectProd(String prod_id) {
		ProdVO prodVO = prodDao.selectProd(prod_id);
		return prodVO;
	}

	@Override
	public int getProdCnt() {
		int pdCnt = prodDao.getProdCnt();
		return pdCnt;
	}

	@Override
	public List<ProdVO> selectProdByLgu(String prod_lgu) {
		List<ProdVO> prodList = prodDao.selectProdByLgu(prod_lgu);
		return prodList;
	}

}
