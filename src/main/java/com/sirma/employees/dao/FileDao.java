package com.sirma.employees.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileDao {
	
	public String checkErrors(MultipartFile file);
	
	public List<String[]> getDataGridWithCommonProjects(MultipartFile file);

}
