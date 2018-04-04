package com.cn.cloud.core.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import com.cn.cloud.core.dao.GenericDao;
import com.cn.cloud.core.model.BaseSaveModel;
import com.cn.cloud.core.model.SearchModel;
/**
 * 
 * @ClassName   : GenericServiceImpl.java
 * @Description : 通用ServiceImpl
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
public class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T, PK>, MessageSourceAware {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * Generic DAO
	 */
	protected GenericDao<T, PK> genericDao;	
	
	protected MessageSource messageSource;
	
	public GenericServiceImpl() {
	}

	public GenericServiceImpl(GenericDao<T, PK> genericDao) {
		this.genericDao = genericDao;
	}
	
	@Override
	public void setGenericDao(GenericDao<T, PK> genericDao){
		this.genericDao = genericDao;
	}
	
	@Override
	public GenericDao<T, PK> getGenericDao(){
		return this.genericDao;
	}
	
	
	@Override
	public void setMessageSource(MessageSource messageSource) {	
		this.messageSource = messageSource;
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	@Override
	public T findByPk(PK id) {
		return genericDao.findByPk(id);
	}
	
	/**
	 * 根据主键判断有无
	 * @param obj
	 * @return
	 */
	@Override
	public boolean exists(PK id){
		if(findByPk(id) == null) return false;
		return true;
	}
	
	/**
	 * 插入单体
	 * @param obj
	 * @return
	 */
	@Override
	public int create(T obj) {
		return genericDao.create(obj);
	}
	
	/**
	 * 插入LIST
	 * @param list
	 * @return
	 */
	@Override
	public int create(List<T> list) {
		return genericDao.create(list);
	}
	
	/**
	 * 更新单体
	 * @param obj
	 * @return
	 */
	@Override
	public int update(T obj) {
		return genericDao.update(obj);
	}
	
	/**
	 * 更新LIST
	 * @param list
	 * @return
	 */
	@Override
	public int update(List<T> list) {
		return genericDao.update(list);
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	@Override
	public int remove(PK id) {
		return genericDao.remove(id);
	}
	
	/**
	 * 删除LIST
	 * @param list
	 * @return
	 */
	@Override
	public int remove(List<T> list) {
		return genericDao.remove(list);
	}
	
	/**
	 * 按条件查询
	 * @param SearchModel
	 * @return LIST
	 */
	@Override
	public List<?> select(SearchModel searchModel) {
		return genericDao.select(searchModel);
	}
	
	/**
	 * 返回COUNT
	 * @param SearchModel
	 * @return
	 */
	@Override
	public long selectCount(SearchModel searchModel) {
		return genericDao.selectCount(searchModel);
	}
	
	
	/**
	 * 按条件查询
	 * @param SearchModel
	 * @return LIST
	 */
	@Override
	public List<?> query(SearchModel searchModel) {
		return genericDao.query(searchModel);
	}
	
	/**
	 * 返回COUNT
	 * @param SearchModel
	 * @return
	 */
	@Override
	public long queryCount(SearchModel searchModel) {
		return genericDao.queryCount(searchModel);
	}
	
	
	/**
	 * grid 一并保存修改删除
	 */
	@Override
	public int save(BaseSaveModel<T> saveModel) {
		
		//保存
		if(saveModel.getInsertList().size()!=0){
			create(saveModel.getInsertList());
		}
		//更新
		if(saveModel.getUpdateList().size()!=0){
			update(saveModel.getUpdateList());
		}
		//删除
		if(saveModel.getDeleteList().size()!=0){
			remove(saveModel.getDeleteList());
		}
		return 0;
	}
	
}

