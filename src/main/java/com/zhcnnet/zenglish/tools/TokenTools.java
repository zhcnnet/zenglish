package com.zhcnnet.zenglish.tools;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenTools 
{
	private static String key = "";
	
	@Value("${custom.token-key}")
	private void setKey(String key)
	{
		TokenTools.key = key;
	};
	/**
	 * 生成token
	 */
	public static String generateToken(int userId)
	{
		String token = Jwts.builder()
                .claim("userId", userId)
                .setExpiration(nowAddDays(10))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

		return token;
	}
	
	/**
	 * 验证token
	 */
	public static int verify(String token)
	{
		try 
		{
			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
			Object obj = claims.get("userId");
			if(obj instanceof Integer)
			{
				return (int)obj;
			}
		} 
		catch (ExpiredJwtException e) 
		{
			System.out.println("token过期");
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * 系统时间加天数
	 */
	public static Date nowAddDays(int day)
	{
		Date now = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(now);  
		calendar.add(Calendar.DAY_OF_MONTH, day);  
		
		return calendar.getTime();
	}
}
