package com.sirma.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sirma.employees.service.FileService;

@Controller
public class FileUploadController {
	
	private FileService fileService;
	
	@Autowired
	public FileUploadController(FileService fileService) {
		this.fileService = fileService;
	}
	
	
	@RequestMapping("/")
	public String start() {
		return "fileUpload";
	}

	@PostMapping("/fileUpload")
	public String receiveFile(@RequestParam("file") MultipartFile file, ModelMap model) {
		String result = fileService.findResult(file);	
		model.addAttribute("result", result);
		return "resultPage";		
	}
		
	@GetMapping("/fileUpload")
	public String reloadFileUpload(ModelMap model) {
		model.addAttribute("result", "No file was uploaded");
		return "resultPage";
	}
	
}
