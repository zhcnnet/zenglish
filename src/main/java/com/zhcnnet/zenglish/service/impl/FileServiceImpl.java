package com.zhcnnet.zenglish.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhcnnet.zenglish.dao.FileDao;
import com.zhcnnet.zenglish.model.Result;
import com.zhcnnet.zenglish.service.FileService;
import com.zhcnnet.zenglish.service.FileUploadService;

@Service
public class FileServiceImpl implements FileService
{
	@Autowired
	@Qualifier("local")
	private FileUploadService fileUploadService;
	
	@Autowired
	private FileDao fileDao;

	@Override
	public Result upload(MultipartFile file, String type) 
	{
		boolean isImage = type.equals("image");
		String path = fileUploadService.uploadFile(file, isImage?type:"audio");
		String name = file.getOriginalFilename();
		
		Map<String, Object> prms = new HashMap<String, Object>();
		prms.put("filePath", path);
		prms.put("fileName", name);
		prms.put("fileType", isImage?2:1);
		
		int r = fileDao.add(prms);
		Result result = new Result();
		result.setData(path);
		result.setStatus(r>0?Result.STATUS_SUCCESS:Result.STATUS_LOSER);
		result.setMessage(r>0?"上传成功":"上传失败");
		return result;
	}

}
