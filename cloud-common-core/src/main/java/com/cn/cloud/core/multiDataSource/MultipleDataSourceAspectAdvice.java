package com.cn.cloud.core.multiDataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName   : MultipleDataSourceAspectAdvice.java
 * @Description :根据方法前缀名称 使用不同的数据源 
 * @author Yin Xueyuan
 * @since 2017年3月7日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017年3月7日        Yin Xueyuan           fisrt create
 * </pre>
 */
@Component  
@Aspect
@Order(0)
public class MultipleDataSourceAspectAdvice {
  
    @Around("execution(* com.cn.naver..service..*Impl.mycatFor*(..))")  
    public Object changeMycatDataSource(ProceedingJoinPoint jp) throws Throwable {  
        MultipleDataSource.setThreadLocalDatasource("mycat");  
        return jp.proceed();  
    }  
    
    @Around("execution(* com.cn.naver..service..*Impl.query*(..))")  
    public Object changeSlaveDataSource(ProceedingJoinPoint jp) throws Throwable {  
    	MultipleDataSource.setThreadLocalDatasource("slave");  
    	return jp.proceed();  
    }  

}
