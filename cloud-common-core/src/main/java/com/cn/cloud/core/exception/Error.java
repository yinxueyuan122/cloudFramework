package com.cn.cloud.core.exception;

import java.io.Serializable;

/**
 * 
 * @ClassName   : Error.java
 * @Description : 
 * @author Yin Xueyuan
 * @since 2017年3月9日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017年3月9日        Yin Xueyuan           fisrt create
 * </pre>
 */

public class Error implements Serializable {

    /**
     * Statements
     * (long)serialVersionUID
     */
    private static final long serialVersionUID = -4483675463265045412L;

    /**
     * the error code
     */
    private String errorCode;

    /**
     * the error message
     */
    private String errorMessage;

    /**
     * the exception class
     */
    private String exceptionClass;

    public Error(String errorMessage){
        this(null, errorMessage, null);
    }

    public Error(String errorMessage, String exceptionClass){
        this(null, errorMessage, exceptionClass);
    }

    public Error(String errorCode, String errorMessage, String exceptionClass){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.exceptionClass = exceptionClass;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }
    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    /**
     * @return the exceptionClass
     */
    public String getExceptionClass() {
        return exceptionClass;
    }
    /**
     * @param exceptionClass the exceptionClass to set
     */
    public void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }



}
