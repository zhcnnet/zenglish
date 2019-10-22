package com.zhcnnet.zenglish.dao.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcnnet.zenglish.dao.WordDao;
import com.zhcnnet.zenglish.model.QueryList;

@Service
public class WordDaoImpl implements WordDao
{
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public int add(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		session.insert("word.add",prms);
		session.commit();
		session.close();
		return Integer.parseInt(prms.get("wordId").toString());
	}

	@Override
	public QueryList list(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		QueryList list = session.selectOne("word.list",prms);
		session.commit();
		session.close();
		return list;
	}

	@Override
	public int revise(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		int result = session.insert("word.revise",prms);
		session.commit();
		session.close();
		return result;
	}

}
