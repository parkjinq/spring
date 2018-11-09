package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.util.model.PageVO;

public interface IProdDao {
	
	/**
	 * 
	* Method : selectProdPageList
	* 작성자 : jin
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : pageVO조건에 맞는 양의 ProdVO를 반환하는 메서드
	 */
	public List<ProdVO> selectProdPageList(PageVO pageVO);
	
	/**
	 * 
	* Method : selectProd
	* 작성자 : jin
	* 변경이력 :
	* @param prod_id
	* @return
	* Method 설명 : 인자 값으로 받은 prod_id에 해당하는 ProdVO를 반환하는 메서드
	 */
	public ProdVO selectProd(String prod_id);
	
	/**
	 * 
	* Method : getProdCnt
	* 작성자 : jin
	* 변경이력 :
	* @return
	* Method 설명 : 전체 prod수를 반환하는 메서드
	 */
	public int getProdCnt();
	
	/**
	 * 
	* Method : selectProdByLgu
	* 작성자 : jin
	* 변경이력 :
	* @param prod_lgu
	* @return
	* Method 설명 : 해당 prod리스트를 반환하는 메서드
	 */
	public List<ProdVO> selectProdByLgu(String prod_lgu);
}
