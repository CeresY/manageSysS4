package com.common.system.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.system.dao.RoleDao;
import com.common.system.pojo.Role;

/**
 * @discription 角色信息
 * @author yangzhan-xps13
 * @date 2016年7月31日-下午1:42:38
 */
@Service
public class RoleService {
	private Logger log = Logger.getLogger(RoleService.class);
	
	@Resource
	private RoleDao roleDao;
	
	/*
	 * 新增机构
	 */
	@Transactional
	public int addRole(String name) {
		return roleDao.addRole(name);
	}
	
	/**
	 * 修改角色
	 * @param sign
	 * @param name
	 * @return
	 */
	@Transactional
	public int editRole(Role role) {
		return roleDao.editRole(role);
	}
	
	/**
	 * 删除角色
	 * @param roleid
	 * @return
	 */
	@Transactional
	public int delRole(String roleid) {
		int i = roleDao.delRole(roleid);
		roleDao.delRoleMapAction(roleid);
		return i;
	}
	
}
