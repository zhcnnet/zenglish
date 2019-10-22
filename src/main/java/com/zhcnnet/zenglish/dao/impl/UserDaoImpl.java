package com.zhcnnet.zenglish.dao.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zhcnnet.zenglish.dao.UserDao;
import com.zhcnnet.zenglish.model.Login;
import com.zhcnnet.zenglish.model.QueryList;

@Service
public class UserDaoImpl implements UserDao
{
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	/**
	 * 登录
	 */
	@Transactional
	@Override
	public Login login(Map<String,Object> prms)
	{
		SqlSession session = sessionFactory.openSession();
		Login login = null;
		try 
		{
			int loginRecord = session.insert("user.loginRecord", prms);
			if(loginRecord > 0)
			{
				login = session.selectOne("user.login",prms);
			}
		} 
		catch (Exception e) 
		{
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println(e.getMessage());
		}
		
		session.commit();
		session.close();
		return login;
	}
	public Login tokenLogin(Map<String,Object> prms)
	{
		SqlSession session = sessionFactory.openSession();
		Login login = session.selectOne("user.login",prms);
		session.commit();
		session.close();
		return login;
	}

	/**
	 * 注册
	 */
	@Override
	public int reg(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		
		int result = 0;
		try 
		{
			result = session.insert("user.reg", prms);
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

	/**
	 * 分配角色
	 */
	@Override
	public int setRole(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		int result = 0;
		try 
		{
			result = session.insert("user.setRole", prms);
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
		QueryList queryUser = session.selectOne("user.queryAll",prms);
		session.commit();
		session.close();
		return queryUser;
	}

	@Override
	public int revise(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		int result = session.update("user.revise",prms);
		
		session.commit();
		session.close();
		return result;
	}

}
