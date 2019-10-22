package com.zhcnnet.zenglish.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;

import com.zhcnnet.zenglish.annotation.CheckLogin;
import com.zhcnnet.zenglish.model.Result;
import com.zhcnnet.zenglish.service.UserService;
import com.zhcnnet.zenglish.tools.TokenTools;

/**
 * 检查登录
 */
public class CheckLoginHandler extends AllHandler
{
	public CheckLoginHandler() 
	{
		System.out.println("CheckLoginHandler");
	}
	@Autowired
	private UserService userService;
	
	/**
	 * 1.判断是否需要登录
	 */
	@Override
	protected boolean preHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception
	{
		CheckLogin checkLogin= handlerMethod.getMethodAnnotation(CheckLogin.class);
		if(checkLogin != null)
		{
			if(request.getSession().getAttribute("user") == null)
			{
				return tokenCheck(request,response);
			}
		}
		return true;
	}
	
	/**
	 * 2.检查token
	 */
	private boolean tokenCheck(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String token = request.getHeader("token");
		if(token != null)
		{
			return tokenLogin(token, request, response);
		}
		response.sendRedirect("/api/exception/notlogin");
		return false;
	}
	
	/**
	 * 3.token登录
	 */
	private boolean tokenLogin(String token,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		int userId = TokenTools.verify(token);
		if(userId <= 0)
		{
			response.sendRedirect("/api/exception/tokenExpired");
			return false;
		}
		else
		{
			Map<String, Object> prms = new HashMap<String, Object>();
			prms.put("userId", userId);
			Result result = userService.tokenLogin(prms);
			
			if(result.getStatus() == Result.STATUS_SUCCESS) //查询结果状态
			{
				request.getSession().setAttribute("user", result.getData()); //设置session登录结果
				return true;
			}
			else
			{
				response.sendRedirect("/api/exception/tokenExpired");
				return false;
			}
		}
	}
}
