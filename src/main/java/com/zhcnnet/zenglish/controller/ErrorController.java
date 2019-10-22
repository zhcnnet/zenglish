package com.zhcnnet.zenglish.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.zhcnnet.zenglish.model.Result;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController
{
	@Autowired
    private ErrorAttributes errorAttributes;
	
	@ResponseBody
	@RequestMapping("/error")
	@ExceptionHandler(value = {Exception.class})
    public Result handleError(HttpServletResponse response, HttpServletRequest request,WebRequest webRequest)
	{
		Map<String, Object> map = errorAttributes.getErrorAttributes(webRequest, false);
		
		int status = response.getStatus();
		Result result = new Result();
		result.setData(status);
		result.setMessage(status + " error:" + map.get("message"));
		result.setStatus(Result.STATUS_LOSER);
		
		return result;
    }
	
	@Override
	public String getErrorPath() 
	{
		return "/error";
	}
}
