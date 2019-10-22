package com.zhcnnet.zenglish.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Login
{
	private List<Map<String, Object>> roles = new ArrayList<Map<String, Object>>(); //角色
	private Map<String, Object> user; //用户
	
	public List<Map<String, Object>> getRoles() 
	{
		return roles;
	}
	public void setRoles(List<Map<String, Object>> roles) 
	{
		this.roles = roles;
	}
	public Map<String, Object> getUser() 
	{
		return user;
	}
	public void setUser(Map<String, Object> user) 
	{
		this.user = user;
	}
	
	
}
