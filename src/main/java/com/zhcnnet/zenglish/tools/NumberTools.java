package com.zhcnnet.zenglish.tools;

public class NumberTools 
{
	public static int initPage(Object val)
	{
		return initPage(val,10);
	}
	public static int initPage(Object val,int pageSize)
	{
		int page = 1;
		if(val instanceof Integer)
		{
			page = (int)val;
		}
		int pageNumber = page-1;
		if(pageNumber <= 0)
		{
			pageNumber = 0;
		}
		pageNumber = pageNumber * pageSize;
		return pageNumber;
	}
}
