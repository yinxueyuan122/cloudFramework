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
	private String sId;
	/**
	 * 名称
	 */
	private String sName;
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
	
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
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
