package kr.or.ddit.file.service;

import kr.or.ddit.file.model.FileVO;

public interface IFileService {
	/**
	 * 
	* Method : insertFile
	* 작성자 : jin
	* 변경이력 :
	* @return
	* Method 설명 : 파읿정보 저장
	 */
	public int insertFile(FileVO fileVO);
}
