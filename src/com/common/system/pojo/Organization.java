package com.common.system.pojo;

import java.util.List;
import java.util.Map;

/**
 * @discription 机构信息
 * @author yangzhan-xps13
 * @date 2016年8月8日-下午8:46:10
 */
public class Organization {
	private String id;
	private String text;
	//private String state;
	//private boolean checked;
	private String parentid;
	//private Map<String,String> attributes;
	private List<Organization> children;
	
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
	public List<Organization> getChildren() {
		return children;
	}
	public void setChildren(List<Organization> children) {
		this.children = children;
	}
	
	
}
