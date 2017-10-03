package com.common.system.pojo;
/**
 * @discription 岗位信息 
 * @author yangzhan-xps13
 * @date 2016年8月28日-下午1:30:44
 */
public class Dept {
	private String id;
	private String name;
	private String parentid;
	private int persons;//人数
	private float per;//人数所占百分比
	
	//原来想建“人员与岗位映射”的pojo，但是考虑到pojo已经快泛滥了所以把user映射放在dept pojo里。
	private String userid;
	private String _parentId;//树形结构字段
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String get_parentId() {
		return _parentId;
	}
	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}
	public int getPersons() {
		return persons;
	}
	public void setPersons(int persons) {
		this.persons = persons;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public float getPer() {
		return per;
	}
	public void setPer(float per) {
		this.per = per;
	}
	
	
}
