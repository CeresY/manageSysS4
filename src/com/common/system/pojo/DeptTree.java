package com.common.system.pojo;

import java.util.List;

/**
 * @discription 岗位信息(用于岗位树)
 * @author yangzhan-xps13
 * @date 2016年8月28日-下午1:30:44
 */
public class DeptTree {
	private String id;
	private String text;
	private String parentid;
	private List<DeptTree> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public List<DeptTree> getChildren() {
		return children;
	}
	public void setChildren(List<DeptTree> children) {
		this.children = children;
	}
	
	
}
