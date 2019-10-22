package com.zhcnnet.zenglish.dao;

import java.util.Map;

import com.zhcnnet.zenglish.model.QueryList;

public interface CategoryDao 
{
	public int add(Map<String,Object> prms);
	public int revise(Map<String,Object> prms);
	public QueryList queryList(Map<String,Object> prms);
}
