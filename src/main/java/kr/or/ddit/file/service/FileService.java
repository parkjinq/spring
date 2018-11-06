package kr.or.ddit.file.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.file.dao.IFileDao;
import kr.or.ddit.file.model.FileVO;

@Service
public class FileService implements IFileService {

	@Resource(name="fileDao")
	private IFileDao fileDao;
	
	@Override
	public int insertFile(FileVO fileVO) {
		int insertCount = fileDao.insertFile(fileVO);
		return insertCount;
	}

	
}
