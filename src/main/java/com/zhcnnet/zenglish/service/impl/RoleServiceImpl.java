package com.zhcnnet.zenglish.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcnnet.zenglish.dao.RoleDao;
import com.zhcnnet.zenglish.model.Result;
import com.zhcnnet.zenglish.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService
{
	@Autowired
	private RoleDao roleDao;

	@Override
	public Result add(Map<String, Object> prms) 
	{
		int r = roleDao.add(prms);
		Result result = new Result();
		result.setData(r);
		result.setStatus(r>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		result.setMessage(r>0?"添加成功":r==-1?"角色已重复添加":"添加失败");
		
		return result;
	}
	
	public Result list(Map<String, Object> prms)
	{
		int page = 1;
		Object val = prms.get("page");
		if(val instanceof Integer)
		{
			page = (int)val;
		}
		
		int pageNumber = page-1;
		if(pageNumber < 0)
		{
			pageNumber = 0;
		}
		pageNumber = pageNumber * 10;
		prms.put("page", pageNumber);
		
		Result result = new Result();
		result.setData(roleDao.list(prms));
		result.setStatus(Result.STATUS_SUCCESS);
		result.setMessage("查询成功");
		return result;
	}

	@Override
	public Result revise(Map<String, Object> prms) 
	{
		prms.remove("status");
		int r = roleDao.revise(prms);
		Result result = new Result();
		result.setData(r);
		result.setMessage(r>0?"修改成功":r==-1?"角色已存在":"修改失败");
		result.setStatus(r>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		return result;
	}

	@Override
	public Result del(Map<String, Object> prms) 
	{
		prms.remove("roleName");
		prms.remove("roleValue");
		prms.put("status", 1);
		int r = roleDao.revise(prms);
		Result result = new Result();
		result.setData(r);
		result.setMessage(r>0?"删除成功":"删除失败");
		result.setStatus(r>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		return result;
	}

}
