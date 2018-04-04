package com.cn.cloud.user.provider.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cn.cloud.core.dao.GenericDao;
import com.cn.cloud.core.dao.GenericDaoImpl;
import com.cn.cloud.user.api.model.UserInfoModel;
import com.cn.cloud.user.api.model.UserInfoSearchModel;
/**
 * 
 * @ClassName   : UserInfoDaoImpl.java
 * @Description : UserInfoDao 实现
 * @author Yin Xueyuan
 * @since 2017年2月24日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017年2月24日        Yin Xueyuan           first create
 * </pre>
 */
@Repository("userInfoDao")
public class UserInfoDaoImpl extends GenericDaoImpl<UserInfoModel, UserInfoModel> implements UserInfoDao{

	@Override
	protected Class<? extends GenericDao<UserInfoModel, UserInfoModel>> getMapperClass() {
		return UserInfoDao.class;
	}
	
	/**
	 * 从数据库查询
	 */
	@Override
	public List<UserInfoModel> selectUserInfoModel(UserInfoSearchModel searchModel) {
		return getSqlSession().selectList(mapperClass.getName() + ".selectUserInfoModel", searchModel);
	}
	
}
