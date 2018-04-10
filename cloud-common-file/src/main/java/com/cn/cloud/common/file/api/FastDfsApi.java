package com.cn.cloud.common.file.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cn.cloud.common.file.client.FastDFSClient;

@RestController
public class FastDfsApi {

	
	@Autowired
	FastDFSClient fastDFSClient;
	
	@RequestMapping("/upload/doc/sample")
    public String uploadDocSample(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException, Exception{

        return fastDFSClient.uploadMultipartFile(file.getInputStream(), file.getName());
    }
}
