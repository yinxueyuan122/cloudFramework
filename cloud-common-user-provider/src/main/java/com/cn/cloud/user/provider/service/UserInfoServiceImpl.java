package com.cn.cloud.user.provider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.cloud.core.service.GenericServiceImpl;
import com.cn.cloud.user.api.model.UserInfoModel;
import com.cn.cloud.user.api.model.UserInfoSearchModel;
import com.cn.cloud.user.provider.dao.UserInfoDao;
/**
 * 
 * @ClassName   : UserInfoServiceImpl.java
 * @Description : user接口实现
 * @author Yin Xueyuan
 * @since 2017年2月24日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017年2月24日        Yin Xueyuan           fisrt create
 * </pre>
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends GenericServiceImpl<UserInfoModel, UserInfoModel> implements UserInfoService {
	
	UserInfoDao userInfoDao;
	
	@Autowired
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		super.setGenericDao(userInfoDao);
		this.userInfoDao = userInfoDao;
	}
	
	/**
	 * 从mycat数据库查询数据
	 */
	@Override
	public List<UserInfoModel> selectUserInfoModel(UserInfoSearchModel searchModel) {
		return userInfoDao.selectUserInfoModel(searchModel);
	}
	

}
