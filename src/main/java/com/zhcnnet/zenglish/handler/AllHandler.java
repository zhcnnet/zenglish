package com.zhcnnet.zenglish.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class AllHandler implements HandlerInterceptor
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		if (handler instanceof HandlerMethod) 
		{
			HandlerMethod method = (HandlerMethod)handler;
			return preHandle(request,response,method);
		}
		
		return true;
	}
	
	protected boolean preHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception
	{
		return true;
	}
}
