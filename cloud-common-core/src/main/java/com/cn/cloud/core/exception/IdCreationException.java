package com.cn.cloud.core.exception;


public class IdCreationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public IdCreationException(String message) {
		super(message);
	}
	
	public IdCreationException(String message, Throwable exception) {
		super(message, exception);
	}
	
}
