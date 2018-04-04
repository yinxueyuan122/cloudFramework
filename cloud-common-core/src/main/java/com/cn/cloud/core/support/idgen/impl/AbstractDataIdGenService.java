package com.cn.cloud.core.support.idgen.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.DisposableBean;

public abstract class AbstractDataIdGenService extends AbstractIdGenService implements DisposableBean{
  
   protected DataSource dataSource = null;
 
   protected String query = "";
 
   protected int mAllocated;
 
   protected long mNextId;
 
   protected Connection getConnection()
     throws SQLException
   {
     return dataSource.getConnection();
   }
 
   public void destroy()
   {
     dataSource = null;
   }
   
   public void setDataSource(DataSource dataSource)
   {
     this.dataSource = dataSource;
   }
 
   public void setQuery(String query)
   {
     this.query = query;
   }
   
}
