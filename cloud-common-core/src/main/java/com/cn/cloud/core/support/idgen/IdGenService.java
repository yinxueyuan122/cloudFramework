package com.cn.cloud.core.support.idgen;

import java.math.BigDecimal;

import com.cn.cloud.core.exception.IdCreationException;

public interface IdGenService
{
  public BigDecimal getNextBigDecimalId()
    throws IdCreationException;
  
  public long getNextLongId()
    throws IdCreationException;
  
  public int getNextIntegerId()
    throws IdCreationException;
  
  public short getNextShortId()
    throws IdCreationException;
  
  public byte getNextByteId()
    throws IdCreationException;
  
  public String getNextStringId()
    throws IdCreationException;
  
  public String getNextStringId(IdGenStrategy paramIdGenStrategy)
    throws IdCreationException;
}