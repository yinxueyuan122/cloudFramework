package com.cn.cloud.core.service;

import java.io.Serializable;
import java.util.List;

import com.cn.cloud.core.dao.GenericDao;
import com.cn.cloud.core.model.BaseSaveModel;
import com.cn.cloud.core.model.SearchModel;
/**
 * 
 * @ClassName   : GenericService.java
 * @Description : 通用Service
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
public interface GenericService<T, PK extends Serializable> {
	
	public void setGenericDao(GenericDao<T, PK> genericDao);
	
	public GenericDao<T, PK> getGenericDao();
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public T findByPk(PK id);
	
	/**
	 * 根据主键判断有无
	 * @param id
	 * @return
	 */
	public boolean exists(PK id);
	
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
	 * 按条件查询
	 * @param SearchModel
	 * @return LIST
	 */
	public List<?> select(SearchModel searchModel);
	
	/**
	 * 返回COUNT
	 * @param SearchModel
	 * @return
	 */
	public long selectCount(SearchModel searchModel);
	
	/**
	 * grid 增加/修改/删除/ model参数  一起处理
	 * @param saveModel
	 * @return
	 */
	public int save(BaseSaveModel<T> saveModel);
	

	/**
	 * 按条件查询
	 * @param SearchModel
	 * @return LIST
	 */
	public List<?> query(SearchModel searchModel);
	
	/**
	 * 返回COUNT
	 * @param SearchModel
	 * @return
	 */
	public long queryCount(SearchModel searchModel);
	
}
