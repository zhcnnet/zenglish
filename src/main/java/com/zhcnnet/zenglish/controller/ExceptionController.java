package com.zhcnnet.zenglish.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhcnnet.zenglish.model.Result;

@RestController
@RequestMapping("/api/exception")
public class ExceptionController 
{
	@ResponseBody
	@GetMapping("/notlogin")
	public Result notLogin()
	{
		Result result = new Result();
		result.setData(null);
		result.setStatus(Result.STATUS_LOSER);
		result.setMessage("用户未登录");
		return result;
	}
	
	@ResponseBody
	@GetMapping("/tokenExpired")
	public Result tokenExpired()
	{
		Result result = new Result();
		result.setData(null);
		result.setStatus(Result.STATUS_LOSER);
		result.setMessage("登录失效，请重新登录");
		return result;
	}
}
