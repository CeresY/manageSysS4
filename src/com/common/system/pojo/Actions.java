package com.common.system.pojo;

/**
 * 菜单（权限）
 * 
 * @discription 
 * @author yangzhan-xps13
 * @date 2016年7月17日-下午1:28:02
 */
public class Actions {
	private String actionid; //菜单id
	private String actionname; //权限名称,汉语
	private String menugroup;//所属菜单id
	private String actionsign;//权限英文标识
	private String isview;//是否可见
	private String url;
	
	//-----------权限所属菜单栏----------
	private String menuname;//菜单分栏名称
	private String isabled;//是否有调用权限
	
	public String getActionid() {
		return actionid;
	}
	public void setActionid(String actionid) {
		this.actionid = actionid;
	}
	public String getActionname() {
		return actionname;
	}
	public void setActionname(String actionname) {
		this.actionname = actionname;
	}
	public String getMenugroup() {
		return menugroup;
	}
	public void setMenugroup(String menugroup) {
		this.menugroup = menugroup;
	}
	public String getActionsign() {
		return actionsign;
	}
	public void setActionsign(String actionsign) {
		this.actionsign = actionsign;
	}
	public String getIsview() {
		return isview;
	}
	public void setIsview(String isview) {
		this.isview = isview;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIsabled() {
		return isabled;
	}
	public void setIsabled(String isabled) {
		this.isabled = isabled;
	}
	
}
