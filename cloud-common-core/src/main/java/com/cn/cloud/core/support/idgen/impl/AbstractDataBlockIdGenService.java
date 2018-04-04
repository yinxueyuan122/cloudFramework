package com.cn.cloud.core.support.idgen.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.cn.cloud.core.exception.IdCreationException;

public abstract class AbstractDataBlockIdGenService extends AbstractDataIdGenService implements InitializingBean{

	private static final Logger logger = LoggerFactory.getLogger(AbstractDataBlockIdGenService.class);
	  
	
	  private BigDecimal mFirstBigDecimal;
	
	  private long mFirstLong;
	
	  private int mAllocated;
	
	  protected int blockSize;
	
	  protected abstract BigDecimal allocateBigDecimalIdBlock(int paramInt)
	    throws IdCreationException;
	  
	  protected abstract long allocateLongIdBlock(int paramInt)
	    throws IdCreationException;
	  
	  public void afterPropertiesSet()
	    throws Exception
	  {
	    mAllocated = Integer.MAX_VALUE;
	  }
	  
	  protected BigDecimal getNextBigDecimalIdInner() throws IdCreationException
	  {
	    if (mAllocated >= blockSize) {
	      try
	      {
	        mFirstBigDecimal = allocateBigDecimalIdBlock(blockSize);
	        
	        mAllocated = 0;
	      }
	      catch (IdCreationException be) {
	        mAllocated = Integer.MAX_VALUE;
	        throw be;
	      }
	    }
	    
	    BigDecimal id = mFirstBigDecimal.add(new BigDecimal(mAllocated));
	    mAllocated += 1;
	    
	    return id;
	  }
	  
	  protected long getNextLongIdInner() throws IdCreationException
	  {
	    if (mAllocated >= blockSize) {
	      try
	      {
	        mFirstLong = allocateLongIdBlock(blockSize);
	        
	        mAllocated = 0;
	
	      }
	      catch (IdCreationException e)
	      {
	        mAllocated = Integer.MAX_VALUE;
	        throw e;
	      }
	    }
	    
	    long id = mFirstLong + mAllocated;
	    if (id < 0L)
	    {
	      logger.error("아이티 사이즈 크기 에러");
	      throw new IdCreationException("아이티 사이즈 크기 에러");
	    }
	    mAllocated += 1;
	    
	    return id;
	  }
	  
	  public void setBlockSize(int blockSize)
	  {
	    this.blockSize = blockSize;
	  }
}
