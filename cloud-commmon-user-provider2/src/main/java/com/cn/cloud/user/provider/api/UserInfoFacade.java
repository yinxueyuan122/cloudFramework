package com.cn.cloud.user.provider.api;


import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cn.cloud.user.api.model.UserInfoModel;
import com.cn.cloud.user.api.model.UserInfoSearchModel;
import com.cn.cloud.user.provider.service.UserInfoService;

@RestController
public class UserInfoFacade {

	
	@Autowired   
	UserInfoService userInfoService;
	
	/**
	 * user 查询列表
	 * @return
	 */
	@PostMapping(value="/user/selectUserInfoModel.do")
	@ResponseBody
	public List<UserInfoModel> selectUserInfoModel(@RequestBody UserInfoSearchModel searchModel)
	{		
                
		return userInfoService.selectUserInfoModel(searchModel);
		
	 }
	
	
	/**
	 * user 插入
	 * @return
	 */
	@PostMapping(value="/user/insertUserInfo.do")
	public Map<String,String> insertUserInfo(@RequestBody UserInfoModel userInfoModel)
	{  
		Map<String,String> map = new HashedMap<String,String>();
		userInfoService.create(userInfoModel);  
		map.put("result", "succese");
		
		return map;
	
	 }
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "sc";
    }
	
	/**
	 * user 更新
	 * @return
	 */
	@PostMapping(value="/user/updateUserInfo.do")
	public Map<String,String> updateUserInfo(@RequestBody UserInfoModel userInfoModel)
	{		
		Map<String,String> map = new HashedMap<String,String>();
		userInfoService.update(userInfoModel);  
		map.put("result", "succese");
		
		return map;
		
	}
}
