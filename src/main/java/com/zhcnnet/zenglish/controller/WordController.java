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
import com.zhcnnet.zenglish.service.WordService;
import com.zhcnnet.zenglish.tools.NumberTools;

@RestController
@RequestMapping("/api/word")
public class WordController 
{
	@Autowired
	private WordService wordService;
	
	@CheckLogin
	@ResponseBody
	@PostMapping("/add")
	public Result add(@RequestBody Map<String, Object> prms)
	{
		Object wordCN	= prms.get("wordCN");
		Object wordEN	= prms.get("wordEN");
		Object wordPS	= prms.get("wordPS");
		Object wordFile	= prms.get("wordFile");
		if(wordCN == null || wordEN == null || wordPS == null || wordFile == null)
		{
			Result result = new Result();
			result.setMessage("参数不能为空");
			result.setStatus(Result.STATUS_LOSER);
			return result;
		}
		return this.wordService.add(prms);
	}
	
	@CheckLogin
	@ResponseBody
	@PostMapping("/list")
	public Result list(@RequestBody Map<String, Object> prms)
	{
		prms.put("page", NumberTools.initPage(prms.get("page")));
		return this.wordService.list(prms);
	}
	
	@CheckLogin
	@ResponseBody
	@PostMapping("/revise")
	public Result revise(@RequestBody Map<String, Object> prms)
	{
		Object wordId = prms.get("wordId");
		Object wordCN = prms.get("wordCN");
		Object wordEN = prms.get("wordEN");
		Object wordPS = prms.get("wordPS");
		Object wordFile = prms.get("wordFile");
		
		if(wordId == null || (wordCN == null && wordEN == null && wordPS == null && wordFile == null))
		{
			Result result = new Result();
			result.setMessage("参数不能为空");
			result.setStatus(Result.STATUS_LOSER);
			return result;
		}
		return this.wordService.revise(prms);
	}
	
	@CheckLogin
	@ResponseBody
	@PostMapping("/del")
	public Result del(@RequestBody Map<String, Object> prms)
	{
		if(prms.get("wordId") == null)
		{
			Result result = new Result();
			result.setMessage("参数不能为空");
			result.setStatus(Result.STATUS_LOSER);
			return result;
		}
		return this.wordService.del(prms);
	}
}
