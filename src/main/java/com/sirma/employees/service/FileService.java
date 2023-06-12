package com.sirma.employees.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	public String checkErrors(MultipartFile file);
	
	public List<String[]> getDataGridWithCommonProjects(MultipartFile file);

}
