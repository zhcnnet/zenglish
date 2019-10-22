package com.zhcnnet.zenglish.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcnnet.zenglish.dao.WordDao;
import com.zhcnnet.zenglish.model.QueryList;
import com.zhcnnet.zenglish.model.Result;
import com.zhcnnet.zenglish.service.WordService;

@Service
public class WordServiceImpl implements WordService
{
	@Autowired
	private WordDao wordDao;
	
	@Override
	public Result add(Map<String, Object> prms) 
	{
		int id = 0;
		try 
		{
			id = this.wordDao.add(prms);
		} 
		catch (Exception e) 
		{
			if(e.getMessage().contains("Duplicate entry"))
			{
				throw new RuntimeException("重复数据");
			}
			else
			{
				e.printStackTrace();
			}
		}
		
		Result result = new Result();
		result.setData(id);
		result.setMessage(id>0?"添加成功":"添加失败");
		result.setStatus(id>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		return result;
	}

	@Override
	public Result list(Map<String, Object> prms) 
	{
		QueryList list = this.wordDao.list(prms);
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
		map.put("wordId", prms.get("wordId"));
		map.put("wordCN", prms.get("wordCN"));
		map.put("wordEN", prms.get("wordEN"));
		map.put("wordPS", prms.get("wordPS"));
		map.put("wordFile", prms.get("wordFile"));
		
		int r = this.wordDao.revise(map);
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
		map.put("wordId", prms.get("wordId"));
		map.put("status", 1);
		
		int r = this.wordDao.revise(map);
		Result result = new Result();
		result.setData(r);
		result.setMessage(r>0?"删除成功":"删除失败");
		result.setStatus(r>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		return result;
	}
}
