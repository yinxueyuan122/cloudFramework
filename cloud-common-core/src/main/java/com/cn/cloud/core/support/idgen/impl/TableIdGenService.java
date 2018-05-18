package com.cn.cloud.core.support.idgen.impl;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.cn.cloud.core.exception.IdCreationException;
import com.cn.cloud.core.support.idgen.TableNameStrategy;

public class TableIdGenService extends AbstractDataBlockIdGenService {
	private static final Logger logger = LoggerFactory.getLogger(TableIdGenService.class);
	
	  private String table = "TABLE_SEQ";
	
	  private String tableName = "id";
	
	  private String tableNameFieldName = "TABLE_NAME";
	
	  private String nextIdFieldName = "NEXT_SEQ";
	
	  private JdbcTemplate jdbcTemplate;
	
	  private TransactionTemplate transactionTemplate;
	
	  private TableNameStrategy tableNameStrategy = new TableNameStrategy() {
	    public String makeTableName(String originalTableName) {
	      return originalTableName;
	    }
	  };
	  
	  public void setDataSource(DataSource dataSource)
	  {
	    super.setDataSource(dataSource);
	    
	    jdbcTemplate = new JdbcTemplate(dataSource);
	    
	    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
	    
	    transactionTemplate = new TransactionTemplate(transactionManager);
	    transactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
	    transactionTemplate.setIsolationLevelName("ISOLATION_READ_COMMITTED");
	  }
	  
	  private Object insertInitId(boolean useBigDecimals, int blockSize)
	  {
	    logger.debug(messageSource.getMessage("debug.idgen.init.idblock", new Object[] { tableNameStrategy.makeTableName(tableName) }, LocaleContextHolder.getLocale()));
	    
	    Object initId = null;
	    
	    String insertQuery = "INSERT INTO " + table + "(" + tableNameFieldName + ", " + nextIdFieldName + ") " + "values('" + tableNameStrategy.makeTableName(tableName) + "', ?)";
	    
	    logger.debug("Insert Query : {}", insertQuery);
	    
	    if (useBigDecimals) {
	      initId = new BigDecimal(blockSize);
	    }
	    else {
	      initId = new Long(blockSize);
	    }
	    
	    jdbcTemplate.update(insertQuery, new Object[] { initId });
	    
	    return initId;
	  }
	
	  private Object allocateIdBlock(final int blockSize, final boolean useBigDecimals)
	    throws IdCreationException
	  {
			Object id = transactionTemplate.execute(new TransactionCallback<Object>()
	    {
	      public Object doInTransaction(TransactionStatus status)
	      {
	        Object nextId;
	
	        try
	        {
	          String selectQuery = "SELECT " + nextIdFieldName + " FROM " + table + " WHERE " + tableNameFieldName + " = ?";
	          
	          TableIdGenService.logger.debug("Select Query : {}", selectQuery);
	          
	          if (useBigDecimals) {
	            try { nextId = jdbcTemplate.queryForObject(selectQuery, new Object[] { tableNameStrategy.makeTableName(tableName) }, BigDecimal.class);
	            } catch (EmptyResultDataAccessException erdae) {
	              nextId = null;
	            }
	            
	            if (nextId == null) {
	              TableIdGenService.this.insertInitId(useBigDecimals, blockSize);
	              
	              return new BigDecimal(0);
	            }
	          } else {
	            try {
	              nextId = jdbcTemplate.queryForObject(selectQuery, new Object[] { tableNameStrategy.makeTableName(tableName) }, Long.class);
	            } catch (EmptyResultDataAccessException erdae) {
	              nextId = Long.valueOf(-1L);
	            }
	            
	            if (((Long)nextId).longValue() == -1L) {
	              TableIdGenService.this.insertInitId(useBigDecimals, blockSize);
	              
	              return new Long(0L);
	            }
	          }
	        } catch (DataAccessException dae) {
	          dae.printStackTrace();
	          TableIdGenService.logger.debug("{}", dae);
	          status.setRollbackOnly();
	          throw new IdCreationException("id lock error");
	        }
	        try
	        {
	          String updateQuery = "UPDATE " + table + " SET " + nextIdFieldName + " = ?" + " WHERE " + tableNameFieldName + " = ?";
	          
	          TableIdGenService.logger.debug("Update Query : {}", updateQuery);
	          Object newNextId; 
	          if (useBigDecimals) {
	            newNextId = ((BigDecimal)nextId).add(new BigDecimal(blockSize));
	          }
	          else {
	            newNextId = new Long(((Long)nextId).longValue() + blockSize);
	          }
	          
	          jdbcTemplate.update(updateQuery, new Object[] { newNextId, tableNameStrategy.makeTableName(tableName) });
	          
	          return nextId;
	        } catch (DataAccessException dae) {
	          status.setRollbackOnly();
	          throw new IdCreationException("id lock error");
	        }
	      }
	    });
	    
	    return id;
	    
	  }
	  
	  protected BigDecimal allocateBigDecimalIdBlock(int blockSize)
	    throws IdCreationException
	  {
	    return (BigDecimal)allocateIdBlock(blockSize, true);
	  }
	  
	  protected long allocateLongIdBlock(int blockSize)
	    throws IdCreationException
	  {
	    Long id = (Long)allocateIdBlock(blockSize, false);
	    
	    return id.longValue();
	  }
	
	  public void setTable(String table)
	  {
	    this.table = table;
	  }
	
	  public void setTableName(String tableName)
	  {
	    this.tableName = tableName;
	  }
	
	  public void setTableNameFieldName(String tableNameFieldName)
	  {
	    this.tableNameFieldName = tableNameFieldName;
	  }
	
	  public void setNextIdFieldName(String nextIdFieldName)
	  {
	    this.nextIdFieldName = nextIdFieldName;
	  }
	
	  public void setTableNameStrategy(TableNameStrategy tableNameStrategy)
	  {
	    this.tableNameStrategy = tableNameStrategy;
	  }
}
