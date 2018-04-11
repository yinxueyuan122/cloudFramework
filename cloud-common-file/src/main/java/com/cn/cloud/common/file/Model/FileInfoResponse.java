package com.cn.cloud.common.file.Model;


public class FileInfoResponse {
	
	  /**
     * 返回状态编码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 成功标识
     */
    private String success ;

    /**
     * 文件路径
     */
    private String fileDocNo;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * Http URL
     */
    private String httpUrl;

    /**
     * Http Token
     */
    private String token;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFileDocNo() {
		return fileDocNo;
	}

	public void setFileDocNo(String fileDocNo) {
		this.fileDocNo = fileDocNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
    
    
}
