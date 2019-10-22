package com.zhcnnet.zenglish.model;

public class Result 
{
	public static final int STATUS_SUCCESS = 1; // 成功
	public static final int STATUS_LOSER = 0; // 失败
	
	private String message;
	private int status;
	private Object data;
	
	public String getMessage() 
	{
		return message;
	}
	public void setMessage(String message) 
	{
		this.message = message;
	}
	public int getStatus() 
	{
		return status;
	}
	public void setStatus(int status) 
	{
		this.status = status;
	}
	public Object getData() 
	{
		return data;
	}
	public void setData(Object data) 
	{
		this.data = data;
	}
	
	
}
