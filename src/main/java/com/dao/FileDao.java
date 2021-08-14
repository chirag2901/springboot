package com.dao;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;


@Repository
public class FileDao {
	Path folder = Paths.get("upload");
	public void saveFile(MultipartFile file) {
		try {
//			Files.copy(file.getInputStream(), this.folder.resolve(file.getOriginalFilename()));	
			Files.copy(file.getInputStream(), this.folder.resolve(file.getOriginalFilename()));
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		}
	public List<File> getAllFiles(){
		File file = folder.toFile();
		File[] fileList = file.listFiles();
		List<File> listOfFiles  = new  ArrayList<>();
		for(File x:fileList) {
			listOfFiles.add(x);
		}
		return listOfFiles;
		
	}
}
