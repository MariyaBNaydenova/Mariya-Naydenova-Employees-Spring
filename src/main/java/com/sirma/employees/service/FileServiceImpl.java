package com.sirma.employees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sirma.employees.dao.FileDao;

@Service("fileService")
public class FileServiceImpl implements FileService {
	
	private FileDao fileDao;
	
	@Autowired
	public FileServiceImpl(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public String findResult(MultipartFile file) {
		return fileDao.findResult(file);
	}

}
