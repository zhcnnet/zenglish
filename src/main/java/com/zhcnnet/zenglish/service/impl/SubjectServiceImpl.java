package com.zhcnnet.zenglish.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcnnet.zenglish.dao.SubjectDao;
import com.zhcnnet.zenglish.model.QueryList;
import com.zhcnnet.zenglish.model.Result;
import com.zhcnnet.zenglish.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService
{
	@Autowired
	private SubjectDao subjectDao;

	@Override
	public Result add(Map<String, Object> prms) 
	{
		int r = subjectDao.add(prms);
		Result result = new Result();
		result.setStatus(r>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		result.setMessage(r>0?"添加成功":"添加失败");
		result.setData(r);
		return result;
	}

	@Override
	public Result list(Map<String, Object> prms) 
	{
		QueryList list = subjectDao.queryList(prms);
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
		map.put("subjectId", prms.get("subjectId"));
		map.put("subjectName", prms.get("subjectName"));
		map.put("subjectDesc", prms.get("subjectDesc"));
		map.put("subjectImgFile", prms.get("subjectImgFile"));
		map.put("subjectType", prms.get("subjectType"));
		
		int r = subjectDao.revise(map);
		Result result = new Result();
		result.setData(r);
		result.setMessage(r>0?"修改成功":"修改失败");
		result.setStatus(r>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		return result;
	}
	
	@Override
	public Result del(Map<String, Object> prms) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subjectId", prms.get("subjectId"));
		map.put("status", 1);
		
		int r = subjectDao.revise(map);
		Result result = new Result();
		result.setData(r);
		result.setMessage(r>0?"删除成功":"删除失败");
		result.setStatus(r>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		return result;
	}

}
