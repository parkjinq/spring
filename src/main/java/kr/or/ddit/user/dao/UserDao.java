package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.config.db.SqlFactoryBuilder;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao implements IUserDao{

	public List<UserVO> selectUserAll() {

		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();

		List<UserVO> userList = session.selectList("user.selectUserAll");
//		session.rollback();
//		session.commit();
		session.close();
		
		return userList;
	}

	public UserVO selectUser(String userId) {

		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();

		UserVO user = session.selectOne("user.selectUser", userId);
		session.close();
		
		return user;
	}

	public UserVO selectUserByVo(UserVO userVO) {

		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();

		UserVO user = session.selectOne("user.selectUserByVo", userVO);
		session.close();
		
		return user;
	}

	@Override
	public List<UserVO> selectUserPageList(PageVO pageVO) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();

		List<UserVO> userList = session.selectList("user.selectUserPageList", pageVO);
		session.close();
		
		return userList;
	}

	@Override
	public int getUserCnt() {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();

		int userCnt = session.selectOne("user.getUserCnt");
		session.close();
		
		return userCnt;
	}

	@Override
	public int insertUser(UserVO userVO) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();

		int insertCnt = session.insert("user.insertUser", userVO);
		session.commit();//데이터가 확정이 되니까 필요
		session.close();
		
		return insertCnt;
	}

	@Override
	public int deleteUser(String userId) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();

		int deleteCnt = session.delete("user.deleteUser", userId);
		session.commit();//데이터가 확정이 되니까 필요
		session.close();
		
		return deleteCnt;
	}

	@Override
	public int updateUser(UserVO userVO) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		int updateCnt = session.update("user.updateUser", userVO);
		
		session.commit();//데이터가 확정이 되니까 필요
		session.close();
		
		return updateCnt;
	}

}
