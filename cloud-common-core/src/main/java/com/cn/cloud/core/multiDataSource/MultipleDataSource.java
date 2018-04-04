package com.cn.cloud.core.multiDataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 
 * @ClassName   : MultipleDataSource.java
 * @Description :继承 spring 动态数据源 
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
public class MultipleDataSource extends AbstractRoutingDataSource {

	private static final  ThreadLocal<String> threadLocalDatasource=new ThreadLocal<String>();
  
    public static void setThreadLocalDatasource(String dsName){  
        threadLocalDatasource.set(dsName);  
    }  
  
  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return threadLocalDatasource.get();  
    }  
}  
