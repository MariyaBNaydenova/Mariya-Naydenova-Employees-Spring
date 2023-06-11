package com.sirma.employees.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	public String findResult(MultipartFile file);

}
