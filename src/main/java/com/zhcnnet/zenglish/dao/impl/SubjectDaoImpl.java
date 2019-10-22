package com.zhcnnet.zenglish.dao.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcnnet.zenglish.dao.SubjectDao;
import com.zhcnnet.zenglish.model.QueryList;

@Service
public class SubjectDaoImpl implements SubjectDao
{
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public int add(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		int result = session.insert("subject.add", prms);
		session.commit();
		session.close();
		return result;
	}

	@Override
	public QueryList queryList(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		QueryList list = session.selectOne("subject.queryAll", prms);
		session.commit();
		session.close();
		return list;
	}

	@Override
	public int revise(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		int result = session.update("subject.revise",prms);
		session.commit();
		session.close();
		return result;
	}

}
