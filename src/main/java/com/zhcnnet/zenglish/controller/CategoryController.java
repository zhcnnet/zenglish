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
import com.zhcnnet.zenglish.service.CategoryService;
import com.zhcnnet.zenglish.tools.NumberTools;

@RestController
@RequestMapping("/api/category")
public class CategoryController 
{
	@Autowired
	private CategoryService categoryService;
	
	@CheckLogin
	@ResponseBody
	@PostMapping("/add")
	public Result add(@RequestBody Map<String, Object> prms)
	{
		Object scategoryName = prms.get("scategoryName");
		Object scategoryDesc = prms.get("scategoryDesc");
		Object subjectId = prms.get("subjectId");
		if(scategoryName==null || scategoryDesc==null || subjectId==null)
		{
			Result result = new Result();
			result.setMessage("参数不能为空");
			result.setStatus(Result.STATUS_LOSER);
			return result;
		}
		return this.categoryService.add(prms);
	}
	
	@CheckLogin
	@ResponseBody
	@PostMapping("/list")
	public Result list(@RequestBody Map<String, Object> prms)
	{
		prms.put("page", NumberTools.initPage(prms.get("page")));
		return this.categoryService.list(prms);
	}
	
	@CheckLogin
	@ResponseBody
	@PostMapping("/revise")
	public Result revise(@RequestBody Map<String, Object> prms)
	{
		Object subjectId =  prms.get("subjectId");
		Object scategoryName =  prms.get("scategoryName");
		Object scategoryDesc =  prms.get("scategoryDesc");
		Object scategoryId =  prms.get("scategoryId");
		
		if(scategoryId==null || (scategoryName==null && scategoryDesc==null && subjectId==null))
		{
			Result result = new Result();
			result.setMessage("参数不能为空");
			result.setStatus(Result.STATUS_LOSER);
			return result;
		}
		return this.categoryService.revise(prms);
	}
	
	@CheckLogin
	@ResponseBody
	@PostMapping("/del")
	public Result del(@RequestBody Map<String, Object> prms)
	{
		Object scategoryId =  prms.get("scategoryId");
		if(scategoryId==null)
		{
			Result result = new Result();
			result.setMessage("参数不能为空");
			result.setStatus(Result.STATUS_LOSER);
			return result;
		}
		return this.categoryService.del(prms);
	}
}