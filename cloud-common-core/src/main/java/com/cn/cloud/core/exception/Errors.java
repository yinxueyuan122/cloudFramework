package com.cn.cloud.core.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName   : Errors.java
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

public class Errors implements Serializable {

    /**
     * Statements
     * (long)serialVersionUID
     */
    private static final long serialVersionUID = 730868129901014363L;

    private List<Error> errors = new ArrayList<Error>();


    /**
     * @return the errors
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    /**
     *
     * @param error the error
     */
    public void add(Error error){
        this.errors.add(error);
    }

}
