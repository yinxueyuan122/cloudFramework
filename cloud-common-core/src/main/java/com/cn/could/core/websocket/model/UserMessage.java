package com.cn.could.core.websocket.model;

/**
 * 
 * @ClassName   : UserMessage.java
 * @Description : 点对点model
 * @author Yin Xueyuan
 * @since 2017年3月22日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017年3月22日        Yin Xueyuan           fisrt create
 * </pre>
 */
public class UserMessage {
	
	private String message;
	

	public UserMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
