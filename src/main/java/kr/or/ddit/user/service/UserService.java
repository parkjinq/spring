package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

@Service
public class UserService implements IUserService{

	IUserDao dao = new UserDao();
	
	@Override
	public List<UserVO> selectUserAll() {
		List<UserVO> list = dao.selectUserAll();
		return list;
	}

	@Override
	public UserVO selectUser(String userId) {
		UserVO userVO = dao.selectUser(userId);
		return userVO;
	}

	@Override
	public UserVO selectUserByVo(UserVO userVOInfo) {
		UserVO userVO = dao.selectUserByVo(userVOInfo);
		return userVO;
	}

	@Override
	public Map<String, Object> selectUserPageList(PageVO pageVO) {
		
		//페이지에 해당하는 유저리스트 조회
		List<UserVO> userList = dao.selectUserPageList(pageVO);
		
		//페이지 네비게이션을 위한 전체 유저수 조회
		int userCnt = dao.getUserCnt();
		
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("userList", userList);
		resultMap.put("pageCnt", (int) (Math.ceil((double)userCnt / pageVO.getPageSize())));
		
		return resultMap;
	}

	@Override
	public int getUserCnt() {
		int userCnt = dao.getUserCnt();
		return userCnt;
	}

	@Override
	public int insertUser(UserVO userVO) {
		int insertCnt = dao.insertUser(userVO);
		return insertCnt;
	}

	@Override
	public int deleteUSer(String userId) {
		int deleteCnt = dao.deleteUser(userId);
		return deleteCnt;
	}

	@Override
	public int updateUser(UserVO userVO) {
		int updateCnt = dao.updateUser(userVO);
		return updateCnt;
	}

}
