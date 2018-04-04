package com.cn.cloud.user.provider.api;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public List<UserInfoModel> selectUserInfoModel(@RequestBody UserInfoSearchModel searchModel)
	{		
                
			List<UserInfoModel> list = new ArrayList<UserInfoModel>();
			UserInfoModel model1 = new UserInfoModel();
			model1.setId(1);
			model1.setAge(10);
			
			UserInfoModel model2 = new UserInfoModel();
			model2.setId(1);
			model2.setAge(10);
			
			UserInfoModel model3 = new UserInfoModel();
			model3.setId(1);
			model3.setAge(10);
			
			UserInfoModel model4 = new UserInfoModel();
			model4.setId(1);
			model4.setAge(10);
			
			list.add(model1);
			list.add(model2);
			list.add(model3);
			list.add(model4);
		return userInfoService.selectUserInfoModel(searchModel);
		
		//return list;
	
	 }
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "sc";
    }
}
