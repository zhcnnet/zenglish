package com.zhcnnet.zenglish.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcnnet.zenglish.dao.CategoryDao;
import com.zhcnnet.zenglish.model.QueryList;
import com.zhcnnet.zenglish.model.Result;
import com.zhcnnet.zenglish.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public Result add(Map<String, Object> prms) 
	{
		int r = this.categoryDao.add(prms);
		Result result = new Result();
		result.setData(r);
		result.setMessage(r>0?"新增成功":"新增失败");
		result.setStatus(r>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		return result;
	}

	@Override
	public Result list(Map<String, Object> prms) 
	{
		QueryList list = this.categoryDao.queryList(prms);
		Result result = new Result();
		result.setData(list);
		result.setMessage(list!=null?"查询成功":"查询失败");
		result.setStatus(list!=null?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		return result;
	}

	@Override
	public Result revise(Map<String, Object> prms) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("scategoryId",prms.get("scategoryId"));
		map.put("scategoryName",prms.get("scategoryName"));
		map.put("scategoryDesc",prms.get("scategoryDesc"));
		map.put("subjectId",prms.get("subjectId"));
		int data = this.categoryDao.revise(map);
		Result result = new Result();
		result.setData(data);
		result.setMessage(data>0?"修改成功":"修改失败");
		result.setStatus(data>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		return result;
	}

	@Override
	public Result del(Map<String, Object> prms) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("scategoryId",prms.get("scategoryId"));
		map.put("status", 1);
		int data = this.categoryDao.revise(map);
		Result result = new Result();
		result.setData(data);
		result.setMessage(data>0?"删除成功":"删除失败");
		result.setStatus(data>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		return result;
	}

}
