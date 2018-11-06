package kr.or.ddit.file.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.config.db.SqlFactoryBuilder;
import kr.or.ddit.file.model.FileVO;

//spring bean으로 등록(이름을 안붙이면 class명에서 앞글자만 소문자로 변경한 텍스츠가 spring bean 이름)
@Repository
public class FileDao implements IFileDao{

	private SqlSessionFactory factory;
	
	@Override
	public int insertFile(FileVO fileVO) {
		factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		//sql호출
		int insertCount = session.insert("file.insertFile", fileVO);
		
		//트랜잭션 commit, session close
		session.commit();
		session.close();
		
		return insertCount;
	}

}
