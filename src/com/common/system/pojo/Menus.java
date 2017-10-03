package com.common.system.pojo;

import java.util.List;

/**
 * 权限分栏
 * 
 * @discription 
 * @author yangzhan-xps13
 * @date 2016年7月17日-下午1:38:38
 */
public class Menus {
	private String menusid;//分栏编号 
	private String menuname;//分栏中文名称
	
	//-----------关联的权限
	private List<Actions> actions;
	
	public String getMenusid() {
		return menusid;
	}
	public void setMenusid(String menusid) {
		this.menusid = menusid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public List<Actions> getActions() {
		return actions;
	}
	public void setActions(List<Actions> actions) {
		this.actions = actions;
	}
	
	
}
