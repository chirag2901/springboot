package com.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bean.ResponseMessage;
import com.dao.FileDao;

@RestController
@CrossOrigin
public class FileUploadController {
	@Autowired
	FileDao fileDao;
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,@RequestHeader("demo") int demo){
		
		String message = "";
		System.out.println("demo: "+demo	);
		//MIME TYPE
		System.out.println(file.getContentType());
		
		if(file.getContentType().equals("image/jpeg")) {
			fileDao.saveFile(file);
			message = "FIle uploaded.... "+file.getOriginalFilename();
			
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(message),HttpStatus.OK);
		}
		message = "Please Check File Type" + file.getOriginalFilename();
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(message),HttpStatus.CONFLICT);
		
	}
	@GetMapping(value = "/files")
	public ResponseEntity<List<com.bean.FileInfo>> getFileList(){
		System.out.println("FileList Called...");
		List<File> filelist  = fileDao.getAllFiles();
		List<com.bean.FileInfo> fileInfos = new ArrayList<>();
		for(File x:filelist) {
			fileInfos.add(new com.bean.FileInfo(x.getName()));
		}
		return new ResponseEntity<List<com.bean.FileInfo>>(fileInfos,HttpStatus.OK);
		
	}
}
