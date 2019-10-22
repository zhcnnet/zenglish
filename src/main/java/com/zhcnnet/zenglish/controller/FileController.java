package com.zhcnnet.zenglish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zhcnnet.zenglish.model.Result;
import com.zhcnnet.zenglish.service.FileService;

@RestController
@RequestMapping("/api/file")
public class FileController 
{
	
	@Autowired
	private FileService fileService;
	
	@ResponseBody
	@PostMapping("/upload/{type}")
	public Result upload(MultipartFile file, @PathVariable("type") String type)
	{
		return this.fileService.upload(file, type);
	}
}
