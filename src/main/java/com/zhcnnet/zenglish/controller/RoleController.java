package com.zhcnnet.zenglish.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhcnnet.zenglish.annotation.CheckLogin;
import com.zhcnnet.zenglish.model.Result;
import com.zhcnnet.zenglish.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController 
{
	@Autowired
	private RoleService roleService;
	
	/**
	 * 添加
	 */
	@CheckLogin
	@ResponseBody
	@PostMapping("/add")
	public Result add(@RequestBody Map<String, Object> prms)
	{
		Result result = null;
		
		Object roleName = prms.get("roleName");
		Object roleValue = prms.get("roleValue");
		
		int status = Result.STATUS_SUCCESS;
		String msg = "";
		if(roleName == null)
		{
			status = Result.STATUS_LOSER;
			msg = "角色名称不能为空";
		}
		else if(roleName.toString().equals(""))
		{
			status = Result.STATUS_LOSER;
			msg = "角色名称不能为空";
		}
		
		else if(roleValue == null)
		{
			status = Result.STATUS_LOSER;
			msg = "角色值不能为空";
		}
		else if(roleValue.toString().equals(""))
		{
			status = Result.STATUS_LOSER;
			msg = "角色值不能为空";
		}
		if(status == Result.STATUS_LOSER)
		{
			result = new Result();
			result.setData(null);
			result.setMessage(msg);
			result.setStatus(status);
		}
		else 
		{
			result = roleService.add(prms);
		}
		return result;
	}
	
	/**
	 * 删除
	 */
	@CheckLogin
	@ResponseBody
	@PostMapping("/del")
	public Result del(@RequestBody Map<String, Object> prms)
	{
		Result result = null;
		
		Object roleId = prms.get("roleId");
		
		int status = Result.STATUS_SUCCESS;
		String msg = "";
		if(roleId == null)
		{
			status = Result.STATUS_LOSER;
			msg = "角色Id不能为空";
		}
		else if(roleId.toString().equals(""))
		{
			status = Result.STATUS_LOSER;
			msg = "角色Id不能为空";
		}
		if(status == Result.STATUS_LOSER)
		{
			result = new Result();
			result.setData(null);
			result.setMessage(msg);
			result.setStatus(status);
		}
		else 
		{
			result = roleService.del(prms);
		}
		return result;
	}
	
	/**
	 * 列表
	 */
	@CheckLogin
	@ResponseBody
	@PostMapping("/list")
	public Result list(@RequestBody Map<String, Object> prms)
	{
		return roleService.list(prms);
	}
	
	/**
	 * 修改
	 */
	@CheckLogin
	@ResponseBody
	@PostMapping("/revise")
	public Result revise(@RequestBody Map<String, Object> prms)
	{
		Result result = null;
		
		Object roleId = prms.get("roleId");
		Object roleName = prms.get("roleName");
		Object roleValue = prms.get("roleValue");
		
		int status = Result.STATUS_SUCCESS;
		String msg = "";
		if(roleId == null)
		{
			status = Result.STATUS_LOSER;
			msg = "角色Id不能为空";
		}
		else if(roleId.toString().equals(""))
		{
			status = Result.STATUS_LOSER;
			msg = "角色Id不能为空";
		}
		else if(roleName == null && roleValue == null)
		{
			status = Result.STATUS_LOSER;
			msg = "角色名称与角色值必填其中一项";
		}
		if(status == Result.STATUS_LOSER)
		{
			result = new Result();
			result.setData(null);
			result.setMessage(msg);
			result.setStatus(status);
		}
		else 
		{
			result = roleService.revise(prms);
		}
		return result;
	}
}
