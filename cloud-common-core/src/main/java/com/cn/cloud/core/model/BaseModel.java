package com.cn.cloud.core.model;

import java.io.Serializable;
import java.util.Date;

public class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4106925649738043086L;
	
	/**
     * number
     */
    private int rnum;

    /**
     * 登录ID
     */
    private String regId;

    /**
     * 登录时间
     */
    private Date regDt;

    /**
     * 修改人ID
     */
    private String updtId;

    /**
     * 修改日期
     */
    private Date updtDt;

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public String getUpdtId() {
		return updtId;
	}

	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}

	public Date getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(Date updtDt) {
		this.updtDt = updtDt;
	}

    
    

}
