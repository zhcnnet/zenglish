package com.zhcnnet.zenglish.dao.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcnnet.zenglish.dao.CategoryDao;
import com.zhcnnet.zenglish.model.QueryList;

@Service
public class CategoryDaoImpl implements CategoryDao
{
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public int add(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		int result = session.insert("category.add", prms);
		session.commit();
		session.close();
		return result;
	}

	@Override
	public int revise(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		int result = session.insert("category.revise", prms);
		session.commit();
		session.close();
		return result;
	}

	@Override
	public QueryList queryList(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		QueryList list = session.selectOne("category.list",prms);
		session.commit();
		session.close();
		return list;
	}
}
