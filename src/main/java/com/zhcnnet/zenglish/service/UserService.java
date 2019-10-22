package com.zhcnnet.zenglish.service;

import java.util.Map;

import com.zhcnnet.zenglish.model.Result;

public interface UserService
{
	public Result reg(Map<String,Object> prms);
	public Result login(Map<String,Object> prms);
	public Result tokenLogin(Map<String,Object> prms);
	public Result setUserRole(Map<String,Object> prms);
	public Result list(Map<String,Object> prms);
	public Result del(Map<String, Object> prms);
	public Result revise(Map<String, Object> prms);
}
