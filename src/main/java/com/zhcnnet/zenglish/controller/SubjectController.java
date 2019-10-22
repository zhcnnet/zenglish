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
import com.zhcnnet.zenglish.service.SubjectService;
import com.zhcnnet.zenglish.tools.NumberTools;

/**
 * 主题
 */
@RestController
@RequestMapping("/api/subject")
public class SubjectController 
{
	@Autowired
	private SubjectService subjectService;
	
	@CheckLogin
	@ResponseBody
	@PostMapping("/add")
	public Result add(@RequestBody Map<String, Object> prms)
	{
		Object subjectName = prms.get("subjectName");
		Object subjectDesc = prms.get("subjectDesc");
		Object subjectImgFile = prms.get("subjectImgFile");
		Object subjectType = prms.get("subjectType");
		if(subjectName == null || subjectDesc == null || subjectImgFile == null || subjectType == null)
		{
			Result result = new Result();
			result.setMessage("参数不能为空");
			result.setStatus(Result.STATUS_LOSER);
		}
		return this.subjectService.add(prms);
	}
	
	@CheckLogin
	@ResponseBody
	@PostMapping("/list")
	public Result query(@RequestBody Map<String, Object> prms)
	{
		prms.put("page", NumberTools.initPage(prms.get("page")));
		return this.subjectService.list(prms);
	}
	
	@CheckLogin
	@ResponseBody
	@PostMapping("/revise")
	public Result revise(@RequestBody Map<String, Object> prms)
	{
		Object subjectId =  prms.get("subjectId");
		Object subjectName =  prms.get("subjectName");
		Object subjectDesc =  prms.get("subjectDesc");
		Object subjectImgFile =  prms.get("subjectImgFile");
		Object subjectType =  prms.get("subjectType");
		
		if(subjectId==null || (subjectName==null && subjectDesc==null && subjectImgFile==null && subjectType==null))
		{
			Result result = new Result();
			result.setMessage("参数不能为空");
			result.setStatus(Result.STATUS_LOSER);
			return result;
		}
		return this.subjectService.revise(prms);
	}
	
	@CheckLogin
	@ResponseBody
	@PostMapping("/del")
	public Result del(@RequestBody Map<String, Object> prms)
	{
		Object subjectId =  prms.get("subjectId");
		if(subjectId==null)
		{
			Result result = new Result();
			result.setMessage("参数不能为空");
			result.setStatus(Result.STATUS_LOSER);
			return result;
		}
		return this.subjectService.del(prms);
	}
}
