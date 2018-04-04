package com.cn.cloud.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseSaveModel<T> implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 608651889900721974L;
	
	
	private T model;
	/**
     * 添加
     */
    private List<T> insertList = new ArrayList<T>();

    /**
     * 修改
     */
    private List<T> updateList = new ArrayList<T>();

    /**
     * 删除
     */
    private List<T> deleteList = new ArrayList<T>();

    public List<T> getInsertList() {
        return insertList;
    }
    public void setInsertList(List<T> insertList) {
        this.insertList = insertList;
    }
    public List<T> getUpdateList() {
        return updateList;
    }
    public void setUpdateList(List<T> updateList) {
        this.updateList = updateList;
    }
    public List<T> getDeleteList() {
        return deleteList;
    }
    public void setDeleteList(List<T> deleteList) {
        this.deleteList = deleteList;
    }
	public T getModel() {
		return model;
	}
	public void setModel(T model) {
		this.model = model;
	}
    
    
}

