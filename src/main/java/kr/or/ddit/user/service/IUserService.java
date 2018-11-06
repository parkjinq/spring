package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

public interface IUserService {
	
	/**
	 * 
	* Method : selectUserAll
	* 작성자 : jin
	* 변경이력 :
	* @return 유저 전체 UserVO객체 리스트
	* Method 설명 : DB에 있는 모든 유저의 정보를 반환하는 메서드
	 */
	public List<UserVO> selectUserAll();
	
	/**
	 * 
	* Method : selectUser
	* 작성자 : jin
	* 변경이력 :
	* @param userId
	* @return UserVO객체
	* Method 설명 : 매개변수인 userId와 일치하는 UserVO를 반환하는 메서드
	 */
	public UserVO selectUser(String userId);
	
	/**
	 * 
	* Method : selectUserByVo
	* 작성자 : jin
	* 변경이력 :
	* @param userVO
	* @return UserVO객체
	* Method 설명 : 매개변수인 userVO와 일치하는 UserVO를 반환하는 메서드
	 */
	public UserVO selectUserByVo(UserVO userVO);
	
	/**
	 * 
	* Method : selectUserPageList
	* 작성자 : jin
	* 변경이력 :
	* @param pageVO
	* @return Map<String, Object> / UserVO리스트와 전체 유저수(int)
	* Method 설명 : pageVO조건에 맞는 양의 UserVO리스트와 전체 유저수(int) Map에 담아 반환한다.
	 */
	public Map<String, Object> selectUserPageList(PageVO pageVO);
	
	/**
	 * 
	* Method : getUserCnt
	* 작성자 : jin
	* 변경이력 :
	* @return 전체 유저수 int형 숫자
	* Method 설명 : DB에 있는 전체 유저 수 반환하는 메서드
	 */
	public int getUserCnt();
	
	/**
	 * 
	* Method : insertUser
	* 작성자 : jin
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 등록
	 */
	public int insertUser(UserVO userVO);
	
	/**
	 * 
	* Method : deleteUSer
	* 작성자 : jin
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	 */
	public int deleteUSer(String userId);
	
	/**
	 * 
	* Method : updateUser
	* 작성자 : jin
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 정보 수정
	 */
	public int updateUser(UserVO userVO);
}
