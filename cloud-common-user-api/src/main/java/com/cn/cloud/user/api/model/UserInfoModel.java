package com.cn.cloud.user.api.model;

import com.cn.cloud.core.model.BaseModel;
/**
 * 
 * @ClassName   : UserInfoModel.java
 * @Description : emp模型
 * @author Yin Xueyuan
 * @since 2017/4/2
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017/4/2       Yin Xueyuan           first create
 * </pre>
 */
public class UserInfoModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5477760898944205168L;
	/**
	 * ID
	 */
	private int id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 年龄
	 */
	private int age;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 职员类型
	 */
	private String empTp;
	/**
	 * 生日
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getEmpTp() {
		return empTp;
	}

	public void setEmpTp(String empTp) {
		this.empTp = empTp;
	}


}
