package kr.or.ddit.file.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.file.model.FileVO;
import kr.or.ddit.test.ServiceDaoTestConfig;

public class FileDaoTest extends ServiceDaoTestConfig{

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
