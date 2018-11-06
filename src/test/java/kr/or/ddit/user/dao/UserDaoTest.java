package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.test.ServiceDaoTestConfig;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

import org.junit.Before;
import org.junit.Test;

public class UserDaoTest extends ServiceDaoTestConfig {
	
	@Resource(name="userDao")
	private IUserDao userDao;
	private String userId = "brown";
	private String pass = "brownpass";
	private final String TEST_USER_ID = "testUser12";
	
	@Before
	public void before(){
		System.out.println("─────────────────────────");
		System.out.println("	@Before");
		System.out.println("─────────────────────────");
	}
	
	@Test
	public void selectUserAllTest() {

		/*** Given : 주어진 상황 ***/
		List<UserVO> userList;
		
		/*** When : 어떤 동작 수행(메소드 호출) ***/

		userList = userDao.selectUserAll();
		
		System.out.println("- selectUserAllTest()");
		System.out.println(userList);

		/*** Then : 결과가 어떠해야하는지 정의 ***/
		assertEquals(109, userList.size());
	}

	@Test
	public void selectUserTest() {
		/*** Given ***/
		UserVO user;
		
		/*** When ***/
		user = userDao.selectUser(userId);
		
		System.out.println("\n- selectUserTest()");
		System.out.println(user);

		/*** Then ***/
		assertEquals("브라운", user.getName());
		assertEquals("brown", user.getUserId());
		assertNotEquals("샐리", user.getName());
		assertNotNull(user);
	}

	@Test
	public void selectUserByVoTest() {
		/*** Given ***/
		UserVO userVo = new UserVO();
		userVo.setUserId(userId);
		userVo.setPass(pass);
		UserVO user;
		
		/*** When ***/
		user = userDao.selectUserByVo(userVo);
		
		System.out.println("\n- selectUserByVoTest()");
		System.out.println(user);

		/*** Then ***/
		assertEquals("브라운", user.getName());
		assertEquals("brown", user.getUserId());
		assertNotEquals("샐리", user.getName());
		assertNotNull(user);
	}
	
	@Test
	public void selectUserPageList() {
		/*** Given ***/
		PageVO pageVO = new PageVO(5, 10);
		List<UserVO> userList;
		
		/*** When ***/
		userList = userDao.selectUserPageList(pageVO);
		
		System.out.println("\n- selectUserPageList()");
		System.out.println(userList);
		
		/*** Then ***/
		assertEquals(10, userList.size());
	}
	
	@Test
	public void getUserCnt() {
		/*** Given ***/
		int userCnt;
		
		/*** When ***/
		userCnt = userDao.getUserCnt();
		
		System.out.println("\n- getUserCnt()");
		System.out.println(userCnt);
		
		/*** Then ***/
		assertEquals(109, userCnt);
	}
	
	//이런거 운영DB로 테스트해서는 안된다
	@Test
	public void insertUserTest(){
		/***Given***/
		UserVO userVO = new UserVO();
		userVO.setUserId(TEST_USER_ID);
		userVO.setName("test");
		userVO.setPass("test");
		userVO.setAddr1("test");
		userVO.setAddr2("test");
		userVO.setZipcd("test");
		GregorianCalendar gc = new GregorianCalendar(2018, 7, 8);
		userVO.setBirth(new Date(gc.getTimeInMillis()));
		userVO.setEmail("test@test.test");
		userVO.setTel("test-test-test");

		/***When***/
		int insertCnt = userDao.insertUser(userVO);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
//	
//	//이런거 운영DB로 테스트해서는 안된다
//	@Test
//	public void deleteUSerTest(){
//		/***Given***/
//		String userId = TEST_USER_ID;
//
//		/***When***/
//		int deleteCnt = userDao.deleteUser(userId);
//		
//		/***Then***/
//		assertEquals(1, deleteCnt);
//	}
	
	@Test
	public void updateUserTest(){
		/***Given***/
		String test = "updateTest";
		UserVO userVO = new UserVO();
		userVO.setUserId("fileTest");
		userVO.setName(test);
		userVO.setPass(test);
		userVO.setAddr1(test);
		userVO.setAddr2(test);
		userVO.setEmail(test + "@" + test + ".com");
		userVO.setZipcd("99999");
		GregorianCalendar gc = new GregorianCalendar(2018, 10, 10);
		userVO.setBirth(new Date(gc.getTimeInMillis()));
		userVO.setTel("111-1111-1111");
		userVO.setProfile("profile/ddit.png");
		
		/***When***/
		int updateCnt = userDao.updateUser(userVO);

		/***Then***/
		assertEquals(1, updateCnt);
	}
}
