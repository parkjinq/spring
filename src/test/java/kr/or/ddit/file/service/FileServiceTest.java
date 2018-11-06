package kr.or.ddit.file.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.file.model.FileVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/root-context.xml"})
public class FileServiceTest {

	@Resource(name="fileService")
	private IFileService fileService;
	
	@Test
	public void fileServiceTest() {
		/***Given***/
		FileVO fileVO = new FileVO();
		fileVO.setFile_name("23530b3d-6809-4668-b9bc-9a47f12fc71f.jpg");
		fileVO.setFile_path("D:\\A_TeachingMaterial\\6.JspSrpgin\\upload");
		fileVO.setOrg_file_name("sally.jpg");
		fileVO.setFile_ext(".jpg");
		
		/***When***/
		int insertCount = fileService.insertFile(fileVO);

		/***Then***/
		assertEquals(1, insertCount);
	}

}
