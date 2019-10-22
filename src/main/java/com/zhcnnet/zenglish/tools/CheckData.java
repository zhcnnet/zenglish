package com.zhcnnet.zenglish.tools;

import java.util.regex.Pattern;

public class CheckData 
{
	/**
	 * 检查邮箱
	 */
	public static boolean email(String email)
	{
		return Pattern.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", email);
	}
	
	/**
	 * 检查密码6位数以上
	 */
	public static boolean password(String password)
	{
		return Pattern.matches("^[\\s\\S]{6,}$", password);
	}
}
