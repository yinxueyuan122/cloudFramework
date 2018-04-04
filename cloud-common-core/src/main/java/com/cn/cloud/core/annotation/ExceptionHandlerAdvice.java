package com.cn.cloud.core.annotation;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cn.cloud.core.exception.Error;
import com.cn.cloud.core.exception.Errors;

/**
 * 
 * @ClassName   : ExceptionHandlerAdvice.java
 * @Description : Controller处理所有异常 并JSON返回
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

@EnableWebMvc
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Errors bizError(Exception ex, WebRequest request) {

        Errors errors = new Errors();
        errors.add(new Error(ex.getMessage(), ex.getClass().getSimpleName()));

        return errors;
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Errors dataAccessError(DataAccessException ex, WebRequest request) {

        Errors errors = new Errors();
        errors.add(new Error(ex.getMessage(), ex.getClass().getSimpleName()));

        return errors;
    }

    @ExceptionHandler(TransactionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Errors transactionError(TransactionException ex, WebRequest request) {

        Errors errors = new Errors();
        errors.add(new Error(ex.getMessage(), ex.getClass().getSimpleName()));

        return errors;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Errors accessDeniedError(AccessDeniedException ex, WebRequest request) {

        Errors errors = new Errors();
        errors.add(new Error(ex.getMessage(), ex.getClass().getSimpleName()));

        return errors;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Errors validationError(MethodArgumentNotValidException ex, WebRequest request) {

    	 Errors errors = new Errors();
         errors.add(new Error(ex.getMessage(), ex.getClass().getSimpleName()));

         return errors;
    }


}
