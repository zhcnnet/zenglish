package com.zhcnnet.zenglish.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhcnnet.zenglish.service.FileUploadService;

/**
 * 本地文件上传服务
 */
@Service("local")
public class FileLocalServiceImpl implements FileUploadService
{
	@Value("${custom.file-upload.server.local}")
	private String path;
	
	@Override
	public String uploadFile(MultipartFile file,String fileType) 
	{
		String fileName = file.getOriginalFilename();
		String filePath = fileType + "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";
		File folder = new File(this.path + filePath);
		String path = filePath + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
		if(!folder.isDirectory())
		{
			folder.mkdirs();
		}
		try 
		{
			file.transferTo(new File(this.path + path));
		}
		catch (IllegalStateException | IOException e)
		{
			e.printStackTrace();
		}
		return path;
	}

}
