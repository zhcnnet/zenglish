package com.zhcnnet.zenglish.dao;

import java.util.Map;

import com.zhcnnet.zenglish.model.QueryList;

public interface CategoryWordDao 
{
	public int add(Map<String, Object> prms);
	public QueryList list(Map<String, Object> prms);
	public int revise(Map<String, Object> prms);
}
