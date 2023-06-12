package com.sirma.employees.service;

import java.util.List;

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
	public String checkErrors(MultipartFile file) {
		return fileDao.checkErrors(file);
	}

	@Override
	public List<String[]> getDataGridWithCommonProjects(MultipartFile file) {
		return fileDao.getDataGridWithCommonProjects(file);
	}
}
