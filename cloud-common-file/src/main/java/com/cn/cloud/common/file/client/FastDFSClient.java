package com.cn.cloud.common.file.client;

import java.io.IOException;
import java.io.InputStream;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

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
    
    public static final String CONFIG_FILE = "application.properties";
    
    private TrackerClient trackerClient = null;  
    private TrackerServer trackerServer = null;  
    private StorageServer storageServer = null;  
    private StorageClient1 storageClient = null; 


    public void init() throws IOException, MyException {
        
        ClientGlobal.initByProperties(CONFIG_FILE);
        
        // 建立连接
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);

    }
    
  	public String uploadFile(byte[] file_buff, String file_ext_name) throws Exception {  
        String result = storageClient.upload_file1(file_buff, file_ext_name, null);  
       return result;  
    }  
  
    public String uploadFile(String local_filename, String file_ext_name) throws Exception {  
       String result = storageClient.upload_file1(local_filename, file_ext_name, null);  
       return result;  
    }  
    
    public String uploadMultipartFile(InputStream is, String filename) throws Exception { 
    	// 读取流
        byte[] fileBuff = new byte[is.available()];
        is.read(fileBuff, 0, fileBuff.length);
        
        return uploadFile(fileBuff,filename);  
     }  
    
    
    public void stop() throws IOException {
    	trackerServer.close();
    	storageServer.close();
    }

    



}
