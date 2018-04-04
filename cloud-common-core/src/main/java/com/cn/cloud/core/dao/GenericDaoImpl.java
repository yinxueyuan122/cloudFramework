package com.cn.cloud.core.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import com.cn.cloud.core.model.SearchModel;
/**
 * 
 * @ClassName   : GenericDaoImpl.java
 * @Description : 通用DaoImpl
 * @author Yin Xueyuan
 * @since 2017年2月23日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017年2月23日        Yin Xueyuan           fisrt create
 * </pre>
 */
public abstract class GenericDaoImpl<T, PK extends Serializable> extends SqlSessionDaoSupport implements GenericDao<T, PK>, MessageSourceAware {
	
	
	protected final Logger log = LoggerFactory.getLogger(getClass());

	protected MessageSource messageSource;
	
	protected SqlSessionFactory sqlSessionFactory;
	protected Class<? extends GenericDao<T, PK>> mapperClass;
	
	@Resource(name="sqlSessionFactory")
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	protected abstract Class<? extends GenericDao<T, PK>> getMapperClass();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initDao() throws Exception {
		super.initDao();
		this.mapperClass = getMapperClass();
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findByPk(PK id) {		
		return (T)getSqlSession().selectOne(mapperClass.getName() + ".findByPk", id);
	}
	
	/**
	 * 插入单体
	 * @param obj
	 * @return
	 */
	public int create(T obj) {
		return getSqlSession().insert(mapperClass.getName() + ".create", obj);
	}
	
	/**
	 * 插入LIST
	 * @param list
	 * @return
	 */
	public int create(List<T> list) {
		List<BatchResult> batchResults = new ArrayList<BatchResult>();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
                
        try {
             
            int i = 0;
            for (T obj : list) {
            	sqlSession.insert(mapperClass.getName() + ".create", obj);
                if (i > 0 && i % 500 == 0) {
                    batchResults.addAll(sqlSession.flushStatements());
                    sqlSession.commit();
                }
                i++;
            }
            batchResults.addAll(sqlSession.flushStatements());
            sqlSession.commit();
        } finally {
        	sqlSession.close();
        }
        return updateCount(batchResults);	
	}
	
	/**
	 * 更新单体
	 * @param obj
	 * @return
	 */
	@Override
	public int update(T obj) {
		return getSqlSession().update(mapperClass.getName() + ".update", obj);
	}
	
	/**
	 * 更新LIST
	 * @param list
	 * @return
	 */
	@Override
	public int update(List<T> list) {
		List<BatchResult> batchResults = new ArrayList<BatchResult>();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        
        try {
             
            int i = 0;
            for (T obj : list) {
            	sqlSession.update(mapperClass.getName() + ".update", obj);
                if (i > 0 && i % 500 == 0) {
                    batchResults.addAll(sqlSession.flushStatements());
                    sqlSession.commit();
                }
                i++;
            }
            batchResults.addAll(sqlSession.flushStatements());
            sqlSession.commit();
        } finally {
        	sqlSession.close();
        }
        return updateCount(batchResults);
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	@Override
	public int remove(PK id) {
		return getSqlSession().delete(mapperClass.getName() + ".remove", id);
	}
	
	/**
	 * 删除LIST
	 * @param list
	 * @return
	 */
	@Override
	public int remove(List<T> list) {
		List<BatchResult> batchResults = new ArrayList<BatchResult>();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        
        try {
             
            int i = 0;
            for (T obj : list) {
            	sqlSession.delete(mapperClass.getName() + ".remove", obj);
                if (i > 0 && i % 500 == 0) {
                    log.info("Flush :" + i);
                    batchResults.addAll(sqlSession.flushStatements());
                    sqlSession.commit();
                }
                i++;
            }
            log.info("Latest Flush");
            batchResults.addAll(sqlSession.flushStatements());
            sqlSession.commit();
        } finally {
        	sqlSession.close();
        }
        return updateCount(batchResults);	
	}
	
	/**
	 * 从master数据库按条件查询
	 * @param SearchModel
	 * @return LIST
	 */
	@Override
	public List<?> select(SearchModel searchModel) {
		return getSqlSession().selectList(mapperClass.getName() + ".select", searchModel);
	}
	
	/**
	 * 从master数据库返回COUNT
	 * @param SearchModel
	 * @return
	 */
	@Override
	public long selectCount(SearchModel searchModel) {
		return getSqlSession().selectOne(mapperClass.getName() + ".selectCount", searchModel);
	}
	
	/**
	 * 从slave数据库按条件查询
	 * @param SearchModel
	 * @return LIST
	 */
	@Override
	public List<?> query(SearchModel searchModel) {
		return getSqlSession().selectList(mapperClass.getName() + ".query", searchModel);
	}
	
	/**
	 * 从slave数据库返回COUNT
	 * @param SearchModel
	 * @return
	 */
	@Override
	public long queryCount(SearchModel searchModel) {
		return getSqlSession().selectOne(mapperClass.getName() + ".queryCount", searchModel);
	}
	
	/**
	 * 更新COUNT.
	 * @param batchResults
	 * @return
	 */
	protected int updateCount(List<BatchResult> batchResults){
		int resultCount = 0;
		for(BatchResult result : batchResults){
			for(int updateCount : result.getUpdateCounts()){
				resultCount += updateCount;
			}
		}
		
		return resultCount;
	}
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;		
	}
}
