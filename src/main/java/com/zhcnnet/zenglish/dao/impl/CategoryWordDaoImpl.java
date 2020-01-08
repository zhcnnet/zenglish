package com.zhcnnet.zenglish.dao.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhcnnet.zenglish.dao.CategoryWordDao;
import com.zhcnnet.zenglish.model.QueryList;

public class CategoryWordDaoImpl implements CategoryWordDao
{
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public int add(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		session.insert("categoryWord.add",prms);
		session.commit();
		session.close();
		return Integer.parseInt(prms.get("cwordId").toString());
	}

	@Override
	public QueryList list(Map<String, Object> prms) 
	{
		return null;
	}

	@Override
	public int revise(Map<String, Object> prms) 
	{
		return 0;
	}

}
