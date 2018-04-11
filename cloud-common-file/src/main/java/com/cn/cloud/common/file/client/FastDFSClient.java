package com.cn.cloud.common.file.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.cn.cloud.common.file.util.FileUtil;

/**
 * 
 * @ClassName   : FastDFSClient.java
 * @Description : 
 * @author Yin Xueyuan
 * @since 2018年4月10日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2018年4月10日        Yin Xueyuan           fisrt create
 * </pre>
 */
public class FastDFSClient {
    
    
    private TrackerClient trackerClient = null;  
    private TrackerServer trackerServer = null;  
    private StorageServer storageServer = null;  
    private StorageClient1 storageClient = null; 
    
    /**
     * 文件名称Key
     */
    private static final String FILENAME = "filename";


    public void init() throws IOException, MyException {
        
    	Resource resource = new ClassPathResource("fastdf-client.properties");
    	Properties props = PropertiesLoaderUtils.loadProperties(resource);
    	
        ClientGlobal.initByProperties(props);
        
        // 建立连接
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);

    }
    
  	/**
  	 * 文件上传
  	 * @param file_buff
  	 * @param filename
  	 * @param descriptions
  	 * @return
  	 * @throws Exception
  	 */
  	public String uploadFile(byte[] file_buff, String filename) throws Exception {
  		return uploadFile(file_buff,filename,null);
  	}  
    
    /**
     * 文件上传
     * @param file_buff
     * @param filename
     * @param descriptions
     * @return
     * @throws Exception
     */
  	public String uploadFile(byte[] file_buff, String filename,Map<String, String> descriptions) throws Exception {
  		
  		 // 文件描述
        NameValuePair[] nvps = null;
        List<NameValuePair> nvpsList = new ArrayList<>();
        
        nvpsList.add(new NameValuePair(FILENAME, filename));
        
        // 描述信息
        if (descriptions != null && descriptions.size() > 0) {
            descriptions.forEach((key, value) -> {
                nvpsList.add(new NameValuePair(key, value));
            });
        }
        
        if (nvpsList.size() > 0) {
            nvps = new NameValuePair[nvpsList.size()];
            nvpsList.toArray(nvps);
        }
        
        String file_ext_name = FileUtil.getFilenameSuffix(filename);
        String path = storageClient.upload_file1(file_buff, file_ext_name, nvps); 
       return path;  
    }  
  	
    /**
     * uploadMultipartFile
     * @param is
     * @param filename
     * @return
     */
    public String uploadMultipartFile(InputStream is, String filename) {
    	return uploadMultipartFile(is,filename,null);
     }  
    
    /**
     * uploadMultipartFile
     * @param is
     * @param filename
     * @return
     */
    public String uploadMultipartFile(InputStream is, String filename,Map<String, String> descriptions) { 
    	String path = null;
    	try {
    		byte[] fileBuff = new byte[is.available()];
    		is.read(fileBuff, 0, fileBuff.length);
    		//开始上传
    		path = uploadFile(fileBuff,filename);  
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} finally {
    		// 关闭流
    		try {
    			if(is != null){
    				is.close();
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	return path;  
    }  
    
    
    public void stop() throws IOException {
    	trackerServer.close();
    	storageServer.close();
    }
    
    



}
