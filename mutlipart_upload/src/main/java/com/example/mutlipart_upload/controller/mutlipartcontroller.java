package com.example.mutlipart_upload.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.apache.tika.Tika;


@RestController
public class mutlipartcontroller {
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException{
        File convertFile = new File("/Users/vandhu/Documents/java_programs/new/" + file.getOriginalFilename());
		convertFile.createNewFile();


		try (FileOutputStream fout = new FileOutputStream(convertFile))
		{
			fout.write(file.getBytes());
			Tika tika = new Tika();
      		String detectfile = tika.detect(convertFile);
			String filecontent = tika.parseToString(convertFile);
			System.out.println("File Type:"+detectfile);
			System.out.println("File Content:"+filecontent);
			return detectfile+filecontent;
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
		return "File has uploaded successfully";
	}  
    
}
