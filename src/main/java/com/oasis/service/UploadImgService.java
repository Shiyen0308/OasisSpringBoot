package com.oasis.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImgService {
	private final String UPLOAD_DIR = "C:\\TIA101_Workspace\\oasisSpringBoot\\src\\main\\resources\\static\\image\\user\\";

	public String uploadImg(MultipartFile file) throws IOException {

		File dir = new File(UPLOAD_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		 String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename(); 
		 String filePath = UPLOAD_DIR + fileName ;
		 String avatarPath = "/image/user/" + fileName;
		 try {
		 file.transferTo(new File(filePath));
		 }catch(IOException e){
			 System.err.println("無法上傳檔案: " + e.getMessage());
			 return null;
		 }
		 return avatarPath; 
	}
}
