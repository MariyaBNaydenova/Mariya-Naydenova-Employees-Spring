package com.sirma.employees.dao;

import org.springframework.web.multipart.MultipartFile;

public interface FileDao {
	
	public String findResult(MultipartFile file);

}
