package com.zhcnnet.zenglish.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcnnet.zenglish.dao.UserDao;
import com.zhcnnet.zenglish.model.Login;
import com.zhcnnet.zenglish.model.Result;
import com.zhcnnet.zenglish.service.UserService;
import com.zhcnnet.zenglish.tools.NumberTools;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;

	/**
	 * 注册
	 */
	@Override
	public Result reg(Map<String,Object> prms)
	{
		int reg = userDao.reg(prms);
		
		Result result = new Result();
		result.setData(reg);
		result.setStatus(reg>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		result.setMessage(reg>0?"注册成功":reg==-1?"此账号已经注册":"注册失败");
		
		return result;
	}

	/**
	 * 登录
	 */
	@Override
	public Result login(Map<String,Object> prms)
	{
		Login success = userDao.login(prms);
		
		Result result = new Result();
		result.setData(success);
		result.setStatus(success!=null ? Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		result.setMessage(success!=null ? "登录成功":"登录失败");
		
		return result;
	}

	/**
	 * 设置角色
	 */
	@Override
	public Result setUserRole(Map<String, Object> prms) 
	{
		int r = userDao.setRole(prms);
		Result result = new Result();
		result.setData(r);
		result.setStatus(r>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		result.setMessage(r>0?"分配成功":r==-1?"分配失败，此用户角色已分配":"分配失败");
		
		return result;
	}

	/**
	 * 用户列表
	 */
	@Override
	public Result list(Map<String, Object> prms) 
	{
		prms.put("page", NumberTools.initPage(prms.get("page")));
		
		Result result = new Result();
		result.setData(userDao.list(prms));
		result.setStatus(Result.STATUS_SUCCESS);
		result.setMessage("查询成功");
		return result;
	}

	@Override
	public Result del(Map<String, Object> prms) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("userId", prms.get("userId"));
		int r = userDao.revise(prms);
		
		Result result = new Result();
		result.setData(r);
		result.setStatus(r>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		result.setMessage(r>0?"删除成功":"删除失败");
		
		return result;
	}

	
	@Override
	public Result revise(Map<String, Object> prms)
	{
		prms.remove("status");
		int r = userDao.revise(prms);
		
		Result result = new Result();
		result.setData(r);
		result.setStatus(r>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		result.setMessage(r>0?"修改成功":"修改失败");
		return null;
	}

	@Override
	public Result tokenLogin(Map<String, Object> prms) 
	{
		Login success = userDao.tokenLogin(prms);
		
		Result result = new Result();
		result.setData(success);
		result.setStatus(success!=null ? Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		result.setMessage(success!=null ? "登录成功":"登录失败");
		return result;
	}
}
