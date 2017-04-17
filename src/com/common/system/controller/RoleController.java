package com.common.system.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.system.pojo.Role;
import com.common.system.service.RoleService;
import com.common.system.util.MsgUtil;


//prototype，多例，每次请求，会产生一个controller
@Scope("prototype")
@Controller
@RequestMapping(value="/role")
public class RoleController {
	private Logger log = Logger.getLogger(RoleController.class);

	/*
	 * 推荐使用resource
	 * @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按byName自动注入罢了
	 */
//	@Autowired
	@Resource
	private RoleService roleService;

	/*
	 * 新增机构
	 */
	@RequestMapping(value="/addRole")
	@ResponseBody
	public Map<String, String> addRole(String name) {
		int i = roleService.addRole(name);
		if(i==1) {
			return MsgUtil.getMsgMap(true, "添加角色");
		} else {
			return MsgUtil.getMsgMap(false, "添加角色");
		}
	}
	
	/**
	 * 修改角色名称
	 * @param roleid
	 * @param rolename
	 * @return
	 */
	@RequestMapping(value="/editRole")
	@ResponseBody
	public Map<String, String> editRole(@ModelAttribute("role")Role role) {
		int i = roleService.editRole(role);
		if(i==1) {
			return MsgUtil.getMsgMap(true, "修改角色");
		} else {
			return MsgUtil.getMsgMap(false, "修改角色");
		}
	}
	
	/**
	 * 根据主键删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delRole")
	@ResponseBody
	public Map<String, String> deleteRole(String id) {
		int i = roleService.delRole(id);
		if(i==1) {
			return MsgUtil.getMsgMap(true, "删除角色");
		} else {
			return MsgUtil.getMsgMap(false, "删除角色");
		}
	}
	
}
