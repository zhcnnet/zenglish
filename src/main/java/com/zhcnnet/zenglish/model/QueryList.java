package com.zhcnnet.zenglish.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryList 
{
	private int count = 0;
	private List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	
	public int getCount() 
	{
		return count;
	}
	public void setCount(int count) 
	{
		this.count = count;
	}
	public List<Map<String, Object>> getList() 
	{
		return list;
	}
	public void setList(List<Map<String, Object>> list) 
	{
		this.list = list;
	}
}
