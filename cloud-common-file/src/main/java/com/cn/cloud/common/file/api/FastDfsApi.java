package com.cn.cloud.common.file.api;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cn.cloud.common.file.Model.FileErrorCode;
import com.cn.cloud.common.file.Model.FileInfoModel;
import com.cn.cloud.common.file.Model.FileInfoResponse;
import com.cn.cloud.common.file.client.FastDFSClient;
import com.cn.cloud.common.file.util.FileUtil;


@RestController
public class FastDfsApi {

	
	@Autowired
	FastDFSClient fastDFSClient;
	
	/**
     * 文件服务器地址
     */
    @Value("${fastdfs.server}")
    private String fileServerAddr;
	
	@RequestMapping("/upload/img/multipart.do")
    public FileInfoResponse uploadFileByMultiPart(@RequestParam MultipartFile file) throws IOException, Exception{


         String filepath = null;
         FileInfoResponse fileInfoResponse = new FileInfoResponse();
         
         // 检查文件类型
         if(!FileUtil.checkImage(file.getOriginalFilename())){
        	 fileInfoResponse.setCode(FileErrorCode.FILE_TYPE_ERROR_IMAGE.CODE);
        	 fileInfoResponse.setMessage(FileErrorCode.FILE_TYPE_ERROR_IMAGE.MESSAGE);
        	 fileInfoResponse.setSuccess("fail");
             return fileInfoResponse;
         }
         
         try {
             // 上传到服务器
             filepath = fastDFSClient.uploadMultipartFile(file.getInputStream(), file.getOriginalFilename());

             fileInfoResponse.setFileName(file.getOriginalFilename());
             fileInfoResponse.setFileType(FileUtil.EXT_MAPS.get(FileUtil.getFilenameSuffix(file.getOriginalFilename())));
             fileInfoResponse.setHttpUrl(fileServerAddr+"/"+filepath);
             fileInfoResponse.setSuccess("Success");
         } catch (Exception e) {
        	 fileInfoResponse.setSuccess("fail");
        	 fileInfoResponse.setCode(FileErrorCode.FILE_TYPE_ERROR_IMAGE.CODE);
        	 fileInfoResponse.setMessage(FileErrorCode.FILE_TYPE_ERROR_IMAGE.MESSAGE);
         }
         
         System.out.println("path : "+filepath);
         return fileInfoResponse;
    }
	
	@RequestMapping("/upload/img/binary")
    public FileInfoResponse uploadFileByBinary(@RequestBody FileInfoModel fileInfoModel) throws IOException, Exception{
		
		 String filepath = null;
		 String fileName = fileInfoModel.getFileName();
         FileInfoResponse fileInfoResponse = new FileInfoResponse();
         
         // 检查文件类型
         if(!FileUtil.checkImage(fileName)){
        	 fileInfoResponse.setCode(FileErrorCode.FILE_TYPE_ERROR_IMAGE.CODE);
        	 fileInfoResponse.setMessage(FileErrorCode.FILE_TYPE_ERROR_IMAGE.MESSAGE);
        	 fileInfoResponse.setSuccess("fail");
             return fileInfoResponse;
         }
         
         try {
             // 上传到服务器
             filepath= fastDFSClient.uploadFile(Base64.decodeBase64(fileInfoModel.getFileData()), fileName);

             fileInfoResponse.setFileName(fileName);
             fileInfoResponse.setFileType(FileUtil.EXT_MAPS.get(FileUtil.getFilenameSuffix(fileName)));
             fileInfoResponse.setHttpUrl(fileServerAddr+"/"+filepath);
             fileInfoResponse.setSuccess("Success");
         } catch (Exception e) {
        	 fileInfoResponse.setSuccess("fail");
        	 fileInfoResponse.setCode(FileErrorCode.FILE_TYPE_ERROR_IMAGE.CODE);
        	 fileInfoResponse.setMessage(FileErrorCode.FILE_TYPE_ERROR_IMAGE.MESSAGE);
         }
		System.out.println("path : "+filepath);
        return fileInfoResponse;
    }
}
