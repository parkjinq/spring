package kr.or.ddit.util;

public class FileUtil {
	
	/**
	 * 
	* Method : getFileExt
	* 작성자 : jin
	* 변경이력 :
	* @param fileName
	* @return
	* Method 설명 : 파일 확장자 추출
	 */
	public static String getFileExt(String fileName) {
		
//		int lastDotIndex = fileName.lastIndexOf(".");
//		String str = lastDotIndex == -1 ? "" : fileName.substring(lastDotIndex);
//		return str;
		
		String[] strArr = fileName.split("[.]");
		String result = strArr.length > 1 ? "." + strArr[strArr.length-1]  : "";
		return result;
	}
}
