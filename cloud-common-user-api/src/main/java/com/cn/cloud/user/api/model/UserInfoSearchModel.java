package com.cn.cloud.user.api.model;

import com.cn.cloud.core.model.SearchModel;

public class UserInfoSearchModel extends SearchModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3939741512144342661L;
	
	/**
	 * ID
	 */
	private String sUserId;
	/**
	 * 名称
	 */
	private String sUserName;
	/**
	 * 年龄
	 */
	private int sAge;
	/**
	 * 职员类型
	 */
	private String sEmpTp;
	/**
	 * 生日
	 */
	public String getsUserId() {
		return sUserId;
	}
	public void setsUserId(String sUserId) {
		this.sUserId = sUserId;
	}
	public String getsUserName() {
		return sUserName;
	}
	public void setsUserName(String sUserName) {
		this.sUserName = sUserName;
	}
	public int getsAge() {
		return sAge;
	}
	public void setsAge(int sAge) {
		this.sAge = sAge;
	}
	public String getsEmpTp() {
		return sEmpTp;
	}
	public void setsEmpTp(String sEmpTp) {
		this.sEmpTp = sEmpTp;
	}
	
	
	

}
