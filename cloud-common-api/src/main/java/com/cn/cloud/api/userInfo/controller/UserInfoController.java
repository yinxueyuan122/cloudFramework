package com.cn.cloud.api.userInfo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cn.cloud.user.api.model.UserInfoModel;
import com.cn.cloud.user.api.model.UserInfoSearchModel;
import com.cn.cloud.user.api.service.UserInfoApi;

@RestController
public class UserInfoController {

	
	@Autowired
	UserInfoApi userInfoApi;
	
	/**
	 * user 查询列表
	 * @return
	 */
	@PostMapping(value="/user/selectUserInfoModel.do")
	public List<UserInfoModel> selectUserInfoModel(@RequestBody UserInfoSearchModel searchModel)
	{		
                
		return userInfoApi.selectUserInfoModel(searchModel);
	
	 }
}
