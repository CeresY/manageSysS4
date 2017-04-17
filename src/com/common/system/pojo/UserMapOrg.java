package com.common.system.pojo;

/**
 * @discription 人员与机构的映射关系
 * @author yangzhan-xps13
 * @date 2016年8月25日-下午7:00:07
 */
public class UserMapOrg {
	private String userid; 
	private String orgid;
	private String orgname;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	
	
}
