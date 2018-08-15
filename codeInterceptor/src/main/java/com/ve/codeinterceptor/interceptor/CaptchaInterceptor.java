package com.ve.codeinterceptor.interceptor;

import java.util.Random;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Component
public class CaptchaInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (request.getMethod().equalsIgnoreCase("POST")) {

			String sum = request.getParameter("sum");

			if (sum == null) {
				
				response.sendRedirect(request.getHeader(HttpHeaders.REFERER));
				
				return false;
			}

			int sumCandidate = (int) request.getSession().getAttribute("sum");

			if (sumCandidate != Integer.parseInt(sum)) {
				
				response.sendRedirect(request.getHeader(HttpHeaders.REFERER));
				
				return false;
			}

		}

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		Random rand = new Random();

		int num1 = rand.nextInt(50) + 1;

		int num2 = rand.nextInt(50) + 1;

		request.getSession().setAttribute("sum", num1 + num2);

		modelAndView.getModelMap().addAttribute("num1", num1);
		modelAndView.getModelMap().addAttribute("num2", num2);

	}

}
