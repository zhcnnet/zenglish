package com.zhcnnet.zenglish.dao.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcnnet.zenglish.dao.FileDao;

@Service
public class FileDaoImpl implements FileDao
{
	@Autowired
	private SqlSessionFactory sessionFactory;

	@Override
	public int add(Map<String, Object> prms) 
	{
		SqlSession session = sessionFactory.openSession();
		int result =session.insert("file.add",prms);
		session.commit();
		session.close();
		return result;
	}

}
