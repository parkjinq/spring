package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ch.qos.logback.classic.Logger;

public class PerformanceCheckInterceptor extends HandlerInterceptorAdapter {

	Logger logger = (Logger) LoggerFactory.getLogger(PerformanceCheckInterceptor.class);
	
	//전처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		request.setAttribute("start_time", System.currentTimeMillis());
		
		//다음 인터셉터로 연결
		return true;
	}
	
	//후처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//controller 메서드의 로직이 종료되고 실행
		long end_time = System.currentTimeMillis();
		long start_time = (long) request.getAttribute("start_time");
		
		logger.debug("duration {} : {}", request.getRequestURI(), (end_time - start_time));
		
	}
	
}
