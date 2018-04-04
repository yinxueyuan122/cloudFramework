package com.cn.cloud.core.dao;

import java.io.Serializable;
import java.util.List;

import com.cn.cloud.core.model.SearchModel;
/**
 * 
 * @ClassName   : GenericDao.java
 * @Description : 通用Dao
 * @author Yin Xueyuan
 * @since 2018/4/2
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2018/4/2       Yin Xueyuan           first create
 * </pre>
 */
public interface GenericDao<T, PK extends Serializable> {


	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public T findByPk(PK id);
	
	
	/**
	 * 插入单体
	 * @param obj
	 * @return
	 */
	public int create(T obj);
	
	/**
	 * 插入LIST
	 * @param list
	 * @return
	 */
	public int create(List<T> list);
	
	/**
	 * 更新单体
	 * @param obj
	 * @return
	 */
	public int update(T obj);
	
	/**
	 * 更新LIST
	 * @param list
	 * @return
	 */
	public int update(List<T> list);
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	public int remove(PK id);
	
	/**
	 * 删除LIST
	 * @param list
	 * @return
	 */
	public int remove(List<T> list);
	
	/**
	 * 从master数据库按条件查询
	 * @param SearchModel
	 * @return LIST
	 */
	public List<?> select(SearchModel searchModel);
	
	/**
	 * 从master数据库返回COUNT
	 * @param SearchModel
	 * @return
	 */
	public long selectCount(SearchModel searchModel);
	
	/**
	 * 从slave数据库按条件查询
	 * @param SearchModel
	 * @return LIST
	 */
	public List<?> query(SearchModel searchModel);
	
	/**
	 * 从slave数据库返回COUNT
	 * @param SearchModel
	 * @return
	 */
	public long queryCount(SearchModel searchModel);
}
