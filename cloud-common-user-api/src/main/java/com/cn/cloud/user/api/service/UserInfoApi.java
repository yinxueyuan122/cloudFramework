package com.cn.cloud.user.api.service;

import java.util.List;

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
	 * 从mycat数据库查询数据
	 * @return UserInfoModel
	 */
	@PostMapping(value="/user/selectUserInfoModel.do")
	public List<UserInfoModel> selectUserInfoModel(UserInfoSearchModel searchModel);
	

}
