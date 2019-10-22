package com.zhcnnet.zenglish.service;

import org.springframework.web.multipart.MultipartFile;

import com.zhcnnet.zenglish.model.Result;

public interface FileService 
{
	public Result upload(MultipartFile file, String type);
}
