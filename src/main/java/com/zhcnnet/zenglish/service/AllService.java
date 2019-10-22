package com.zhcnnet.zenglish.service;

import java.util.Map;

import com.zhcnnet.zenglish.model.Result;

public interface AllService 
{
	/**
	 * 新增
	 */
	public Result add(Map<String, Object> prms);
	/**
	 * 查询
	 */
	public Result list(Map<String, Object> prms);
	/**
	 * 修改
	 */
	public Result revise(Map<String, Object> prms);
	/**
	 * 删除
	 */
	public Result del(Map<String, Object> prms);
}
