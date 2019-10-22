package com.zhcnnet.zenglish.dao.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcnnet.zenglish.dao.RoleDao;
import com.zhcnnet.zenglish.model.QueryList;

@Service
public class RoleDaoImpl implements RoleDao
{
	@Autowired
	private SqlSessionFactory sessionFactory;

	@Override
	public int add(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		
		int result = 0;
		
		try 
		{
			result = session.insert("role.add", prms);
		} 
		catch (Exception e) 
		{
			if(e.getMessage().contains("Duplicate entry") == false)
			{
				e.printStackTrace();
			}
			result = -1;
		}
		session.commit();
		session.close();
		return result;
	}

	@Override
	public QueryList list(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		QueryList list = session.selectOne("role.queryAll", prms);
		session.commit();
		session.close();
		return list;
	}

	@Override
	public int revise(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		
		int result = 0;
		
		try 
		{
			result = session.update("role.revise",prms);
		} 
		catch (Exception e) 
		{
			if(e.getMessage().contains("Duplicate entry") == false)
			{
				e.printStackTrace();
			}
			result = -1;
		}
		session.commit();
		session.close();
		return result;
	}

}
