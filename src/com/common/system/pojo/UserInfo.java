package com.common.system.pojo;

import java.util.Date;
import java.util.List;

public class UserInfo {
	private String userid;
	private String username;
	private String chname;
	private String password;
	private String contact;
	private Date createtime;
	
	//-------------------------
	private String role;//用户角色
	private String org;//用户所属机构
	private String dept;//所属岗位
	private List<UserAndType> utList;//用户与用户类型映射
	private List<UserType> typeList;//用户类型
	private List<UserMapOrg> uoList;//用户与机构映射
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getChname() {
		return chname;
	}
	public void setChname(String chname) {
		this.chname = chname;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<UserType> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<UserType> typeList) {
		this.typeList = typeList;
	}
	public List<UserAndType> getUtList() {
		return utList;
	}
	public void setUtList(List<UserAndType> utList) {
		this.utList = utList;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public List<UserMapOrg> getUoList() {
		return uoList;
	}
	public void setUoList(List<UserMapOrg> uoList) {
		this.uoList = uoList;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
}
