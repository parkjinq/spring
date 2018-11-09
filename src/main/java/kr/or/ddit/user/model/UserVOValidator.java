package kr.or.ddit.user.model;

import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ch.qos.logback.classic.Logger;

public class UserVOValidator implements Validator {

	Logger logger = (Logger) LoggerFactory.getLogger(UserVOValidator.class);
	
	//clazz 객체가 UserVO로부터 생성 가능한지 여부를 판단
	@Override
	public boolean supports(Class<?> clazz) {
		return UserVO.class.isAssignableFrom(clazz);
	}
	
	//실제 검증 로직(값이 비어있는지, 글자수 제한, 정규식, 전화번호 포맷팅)
	@Override
	public void validate(Object target, Errors errors) {
		//validation 대상 객체
		//target > userVO
		UserVO userVO = (UserVO) target;
		
		if(userVO.getUserId() == null || userVO.getUserId().equals("")) {
			errors.rejectValue("userId", "required");
		}
		
		//pass 길이가 5보다 작으면 error:lengthNotEnough
		if(userVO.getPass() == null || userVO.getPass().length() < 5) {
			errors.rejectValue("pass", "lengthNotEnough");
		}
		
		//name 공백 에러
		if(userVO.getName() == null || userVO.getName().equals("")) {
			errors.rejectValue("name", "required");
		}
	}
}
