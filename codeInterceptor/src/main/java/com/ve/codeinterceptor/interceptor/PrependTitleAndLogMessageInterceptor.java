package com.ve.codeinterceptor.interceptor;

import java.sql.Date;
//import java.util.Date;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ve.codeinterceptor.model.LogMessage;
import com.ve.codeinterceptor.model.LogMessageRepository;

@Component
public class PrependTitleAndLogMessageInterceptor extends HandlerInterceptorAdapter {

	private long currentTimeBeforeExecution;
	private long currentTimeAfterExecution;
	private long currentTimeAfterViewRender;

	
	private final LogMessageRepository logMessageRepository;

	
	public PrependTitleAndLogMessageInterceptor(LogMessageRepository logMessageRepository) {
		this.logMessageRepository = logMessageRepository;
	}


	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		this.currentTimeBeforeExecution = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime());
		
		return true;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		
		this.currentTimeAfterExecution = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime());

		
		
		ModelMap map = modelAndView.getModelMap();

		if (map.containsAttribute("title")) {

			String title = (String) map.get("title");

			map.put("title", title + " !");

		}

	}
	
	
	
	@Override
	public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
		
		this.currentTimeAfterViewRender = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime());

		
		if (handler instanceof HandlerMethod) {
			
		HandlerMethod handlerMethod = (HandlerMethod) handler;
	
		String message = String.format(
				"[%s - %s] Controler executed in: %d ms , View was shown in: %d ms", 
				handlerMethod.getBeanType().getName(),
				handlerMethod.getMethod().getName(),
				this.currentTimeAfterExecution - this.currentTimeBeforeExecution,
				this.currentTimeAfterViewRender - this.currentTimeBeforeExecution
				);
		
		Date date = new Date(0, 0, 0);
		LogMessage messageEntity = new LogMessage();
		messageEntity.setCreatedOn(date);
		messageEntity.setMessage(message);
		
		this.logMessageRepository.saveAndFlush(messageEntity);
		
		}
		
	}
	
	
	

}
