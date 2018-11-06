package kr.or.ddit.file.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.file.model.FileVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/root-context.xml"})
public class FileDaoTest {

	@Resource(name= "fileDao")
	private IFileDao fileDao;
	
	@Test
	public void insertFileTest() {
		/***Given***/
		FileVO fileVO = new FileVO();
		fileVO.setFile_name("23530b3d-6809-4668-b9bc-9a47f12fc71f.png");
		fileVO.setFile_path("D:\\A_TeachingMaterial\\6.JspSrpgin\\upload");
		fileVO.setOrg_file_name("sally.png");
		fileVO.setFile_ext(".png");
		
		/***When***/
		int insertCount = fileDao.insertFile(fileVO);

		/***Then***/
		assertEquals(1, insertCount);
	}

}
