package com.cn.cloud.user.provider.dao;

import java.util.List;

import com.cn.cloud.core.dao.GenericDao;
import com.cn.cloud.user.api.model.UserInfoModel;
import com.cn.cloud.user.api.model.UserInfoSearchModel;

/**
 * 
 * @ClassName   : UserInfoDao.java
 * @Description : 
 * @author Yin Xueyuan
 * @since 2018年4月2日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2018年4月2日        Yin Xueyuan           first create
 * </pre>
 */
public interface UserInfoDao extends GenericDao<UserInfoModel, UserInfoModel> {

	/**
	 * 从mycat数据库查询
	 * @param demo1SearchModel
	 * @return
	 */
	public List<UserInfoModel> selectUserInfoModel(UserInfoSearchModel searchModel);
	
}
