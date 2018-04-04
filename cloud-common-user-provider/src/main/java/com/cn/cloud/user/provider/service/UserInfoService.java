package com.cn.cloud.user.provider.service;

import java.util.List;

import com.cn.cloud.core.service.GenericService;
import com.cn.cloud.user.api.model.UserInfoModel;
import com.cn.cloud.user.api.model.UserInfoSearchModel;
/**
 * 
 * @ClassName   : UserInfoService.java
 * @Description : user接口
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
public interface UserInfoService extends GenericService<UserInfoModel, UserInfoModel> {
	
	/**
	 * 从mycat数据库查询数据
	 * @return UserInfoModel
	 */
	public List<UserInfoModel> selectUserInfoModel(UserInfoSearchModel searchModel);
	

}
