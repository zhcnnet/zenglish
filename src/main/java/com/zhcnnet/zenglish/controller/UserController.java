package com.zhcnnet.zenglish.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhcnnet.zenglish.annotation.CheckLogin;
import com.zhcnnet.zenglish.model.Login;
import com.zhcnnet.zenglish.model.Result;
import com.zhcnnet.zenglish.service.UserService;
import com.zhcnnet.zenglish.tools.CheckData;
import com.zhcnnet.zenglish.tools.TokenTools;

/**
 * 用户
 */
@RestController
@RequestMapping("/api/user")
public class UserController
{
	@Autowired
	private UserService userService;
	
	/**
	 * 用户注册
	 * @param prms json，{"userEmail":"邮箱","userPassword":"密码","userName":"昵称"}
	 */
	@ResponseBody
	@PostMapping("/reg")
	public Result userReg(@RequestBody Map<String, Object> prms)
	{
		Result result = null;
		String msg = "";
		int status = Result.STATUS_SUCCESS;
		if(prms.get("userEmail") == null)
		{
			msg = "账号不能为空";
			status = Result.STATUS_LOSER;
		}
		else if(CheckData.email(prms.get("userEmail").toString()) == false)
		{
			msg = "账号格式不正确";
			status = Result.STATUS_LOSER;
		}
		else if(prms.get("userPassword") == null)
		{
			msg = "密码不能为空";
			status = Result.STATUS_LOSER;
		}
		else if(CheckData.password(prms.get("userPassword").toString()) == false)
		{
			msg = "密码格式不正确";
			status = Result.STATUS_LOSER;
		}
		else if(prms.get("userName") == null)
		{
			msg = "昵称不能为空";
			status = Result.STATUS_LOSER;
		}
		else if(prms.get("userName").equals(""))
		{
			msg = "昵称不能为空";
			status = Result.STATUS_LOSER;
		}
		if(status == Result.STATUS_LOSER)
		{
			result = new Result();
			result.setMessage(msg);
			result.setStatus(status);
		}
		else
		{
			result = userService.reg(prms);
		}
		return result;
	}
	
	/**
	 * 登录
	 * { "userEmail":"账号", "userPassword":"密码" }
	 */
	@ResponseBody
	@PostMapping("/login")
	public Result userlogin(@RequestBody Map<String, Object> prms,HttpSession session)
	{
		Result result = null;
		String msg = "";
		int status = Result.STATUS_SUCCESS;
		if(prms.get("userEmail") == null)
		{
			msg = "账号不能为空";
			status = Result.STATUS_LOSER;
		}
		else if(CheckData.email(prms.get("userEmail").toString()) == false)
		{
			msg = "账号格式不正确";
			status = Result.STATUS_LOSER;
		}
		else if(prms.get("userPassword") == null)
		{
			msg = "密码不能为空";
			status = Result.STATUS_LOSER;
		}
		else if(CheckData.password(prms.get("userPassword").toString()) == false)
		{
			msg = "密码格式不正确";
			status = Result.STATUS_LOSER;
		}
		if(status == Result.STATUS_LOSER)
		{
			result = new Result();
			result.setMessage(msg);
			result.setStatus(status);
		}
		else
		{
			result = userService.login(prms);
			if(result.getStatus() == Result.STATUS_SUCCESS)
			{
				Login login = (Login)result.getData();
				int userId = (int)login.getUser().get("userId");
				login.getUser().put("token", TokenTools.generateToken(userId));
				session.setAttribute("user", login);
			}
		}
		return result;
	}
	
//	@ResponseBody
//	@PostMapping("/tokenLogin")
//	public Result tokenLogin(Map<String, Object> prms)
//	{
//		Result result = new Result();
//		result.setMessage("功能未开发");
//		result.setStatus(Result.STATUS_LOSER);
//		return result;
//	}
	
	
	/**
	 * 分配角色
	 * { "userId":用户id, "roleId":角色id }
	 */
	@CheckLogin
	@ResponseBody
	@PostMapping("/setRole")
	public Result setUserRole(@RequestBody Map<String, Object> prms)
	{
		Result result = null;
		String msg = "";
		int status = Result.STATUS_SUCCESS;
		if(prms.get("userId") == null)
		{
			msg = "用户Id不能为空";
			status = Result.STATUS_LOSER;
		}
		else if(prms.get("roleId") == null)
		{
			msg = "角色Id不能为空";
			status = Result.STATUS_LOSER;
		}
		if(status == Result.STATUS_LOSER)
		{
			result = new Result();
			result.setMessage(msg);
			result.setStatus(status);
		}
		else
		{
			result = userService.setUserRole(prms);
		}
		return result;
	}
	
	/**
	 * 搜索用户
	 * { "userEmail":"模糊搜索","roleId":"角色Id", "page":页数 }
	 * 
	 */
	@CheckLogin
	@ResponseBody
	@PostMapping("/list")
	public Result list(@RequestBody Map<String, Object> prms)
	{
		return userService.list(prms);
	}
	
	/**
	 * 删除用户
	 * { "userId":用户ID }
	 */
	@CheckLogin
	@ResponseBody
	@PostMapping("/del")
	public Result del(@RequestBody Map<String, Object> prms)
	{
		Result result = null;
		String msg = "";
		int status = Result.STATUS_SUCCESS;
		if(prms.get("userId") == null)
		{
			msg = "用户Id不能为空";
			status = Result.STATUS_LOSER;
		}
		
		if(status == Result.STATUS_LOSER)
		{
			result = new Result();
			result.setMessage(msg);
			result.setStatus(status);
		}
		else
		{
			result = userService.del(prms);
		}
		return result;
	}
	
	/**
	 * 修改用户
	 */
	@CheckLogin
	@ResponseBody
	@PostMapping("/revise")
	public Result revise(@RequestBody Map<String, Object> prms)
	{
		Result result = null;
		String msg = "";
		int status = Result.STATUS_SUCCESS;
		if(prms.get("userId") == null)
		{
			msg = "用户Id不能为空";
			status = Result.STATUS_LOSER;
		}
		
		if(status == Result.STATUS_LOSER)
		{
			result = new Result();
			result.setMessage(msg);
			result.setStatus(status);
		}
		else
		{
			result = userService.revise(prms);
		}
		return result;
	}
}
