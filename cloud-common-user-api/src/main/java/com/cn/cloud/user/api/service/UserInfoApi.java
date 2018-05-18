package com.cn.cloud.user.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.cn.cloud.user.api.model.UserInfoModel;
import com.cn.cloud.user.api.model.UserInfoSearchModel;
/**
 * 
 * @ClassName   : UserInfoService.java
 * @Description : emp接口
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
@FeignClient(value="user-api")
public interface UserInfoApi{
	
	/**
	 * 查询用户信息
	 * @return UserInfoModel
	 */
	@PostMapping(value="/user/selectUserInfoModel.do")
	public List<UserInfoModel> selectUserInfoModel(UserInfoSearchModel searchModel);
	
	/**
	 * 插入用户信息
	 * @return UserInfoModel
	 */
	@PostMapping(value="/user/insertUserInfo.do")
	public Map<String,String> insertUserInfo(UserInfoModel userInfoModel);
	
	/**
	 * 更新用户信息
	 * @return UserInfoModel
	 */
	@PostMapping(value="/user/updateUserInfo.do")
	public Map<String,String> updateUserInfo(UserInfoModel userInfoModel);
	
	/**
	 * 查询用户
	 * @return UserInfoModel
	 */
	@PostMapping(value="/user/selectUserInfoByKey.do")
	public UserInfoModel selectUserInfoByKey(UserInfoSearchModel searchModel);
	

}
