package com.cn.cloud.core.support.idgen.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;

import com.cn.cloud.core.exception.IdCreationException;
import com.cn.cloud.core.support.idgen.IdGenService;
import com.cn.cloud.core.support.idgen.IdGenStrategy;

public abstract class AbstractIdGenService  implements IdGenService, ApplicationContextAware {
	
	 private static final Logger logger = LoggerFactory.getLogger(AbstractIdGenService.class);
		
	 private static final BigDecimal BIG_DECIMAL_MAX_LONG = new BigDecimal(Long.MAX_VALUE);
		
	 private final Object mSemaphore = new Object();
		
	 protected MessageSource messageSource;
		
	 protected boolean useBigDecimals = false;
	 
	 private IdGenStrategy strategy = new IdGenStrategy() {
	   public String makeId(String originalId) {
	     return originalId;
	   }
	 };
	 
	 public boolean isUseBigDecimals()
	 {
	   return useBigDecimals;
	 }
	 
	 public void setUseBigDecimals(boolean useBigDecimals) {
	   this.useBigDecimals = useBigDecimals;
	 }
	 
	 public void setApplicationContext(ApplicationContext applicationContext)
	   throws BeansException
	 {
	   messageSource = ((MessageSource)applicationContext.getBean("messageSource"));
	 }
	 
	 protected abstract BigDecimal getNextBigDecimalIdInner() throws IdCreationException;
	 
	 protected abstract long getNextLongIdInner() throws IdCreationException;
	 
	 protected final long getNextLongIdChecked(long maxId) throws IdCreationException {
	   long nextId;
	   if (useBigDecimals)
	   {
	     BigDecimal bd;
	     synchronized (mSemaphore) {
	       bd = getNextBigDecimalIdInner();
	     }
	     
	     if (bd.compareTo(BIG_DECIMAL_MAX_LONG) > 0) {
	    	 throw new IdCreationException("아이티 사이즈 크기 에러");
	     }
	     nextId = bd.longValue();
	   }
	   else {
	     synchronized (mSemaphore) {
	       nextId = getNextLongIdInner();
	     }
	   }
	   
		
		
	   if (nextId > maxId) {
		   logger.error("아이티 사이즈 크기 에러");
		   throw new IdCreationException("아이티 사이즈 크기 에러");
	   }
	   
	   return nextId;
	 }
	 
	 public BigDecimal getNextBigDecimalId() throws IdCreationException
	 {
	   BigDecimal bd;
	   if (useBigDecimals) {
	     synchronized (mSemaphore) {
	       bd = getNextBigDecimalIdInner();
	     }
	   }
	   else {
	     synchronized (mSemaphore) {
	       bd = new BigDecimal(getNextLongIdInner());
	     }
	   }
	   
	   return bd;
	 }
	 
	 public long getNextLongId() throws IdCreationException
	 {
	   return getNextLongIdChecked(Long.MAX_VALUE);
	 }
	 
	 public int getNextIntegerId() throws IdCreationException
	 {
	   return (int)getNextLongIdChecked(2147483647L);
	 }
	 
	 public short getNextShortId() throws IdCreationException
	 {
	   return (short)(int)getNextLongIdChecked(32767L);
	 }
	 
	 public byte getNextByteId() throws IdCreationException
	 {
	   return (byte)(int)getNextLongIdChecked(127L);
	 }
	 
	 public String getNextStringId() throws IdCreationException
	 {
	   return strategy.makeId(getNextBigDecimalId().toString());
	 }
	 
	 public String getNextStringId(IdGenStrategy strategyObj)
	   throws IdCreationException
	 {
	   return strategyObj.makeId(getNextBigDecimalId().toString());
	 }
	 
		
		
		
	 public IdGenStrategy getStrategy()
	 {
	   return strategy;
	 }
	 
		
		
		
		
	 public void setStrategy(IdGenStrategy strategy)
	 {
	   this.strategy = strategy;
	 }
}
