package com.zhcnnet.zenglish.dao;

import java.util.Map;

import com.zhcnnet.zenglish.model.Login;
import com.zhcnnet.zenglish.model.QueryList;

public interface UserDao 
{
	public Login login(Map<String,Object> prms);
	public Login tokenLogin(Map<String,Object> prms);
	public int reg(Map<String,Object> prms);
	public int setRole(Map<String,Object> prms);
	public QueryList list(Map<String, Object> prms);
	public int revise(Map<String, Object> prms);
}
