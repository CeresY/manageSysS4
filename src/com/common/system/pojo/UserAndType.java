package com.common.system.pojo;
/**
 * @discription 用户与类型映射
 * @author yangzhan-xps13
 * @date 2016年7月24日-下午3:47:36
 */
public class UserAndType {
	private String userid; //用户id
	private String usertypesign; //用户类型英文名称
	private String usertypename; //用户类型汉语名称
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsertypesign() {
		return usertypesign;
	}
	public void setUsertypesign(String usertypesign) {
		this.usertypesign = usertypesign;
	}
	public String getUsertypename() {
		return usertypename;
	}
	public void setUsertypename(String usertypename) {
		this.usertypename = usertypename;
	}
	
}
