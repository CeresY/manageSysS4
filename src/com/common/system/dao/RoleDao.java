package com.common.system.dao;

import org.springframework.stereotype.Repository;

import com.common.system.pojo.Role;

/**
 * @discription 角色管理
 * @author yangzhan-xps13
 * @date 2016年8月8日-下午8:47:18
 */
@Repository
public interface RoleDao {
	/**
	 * 新增角色
	 * @param rolename
	 * @return
	 */
	public int addRole(String rolename);
	
	/**
	 * 修改角色
	 * @param sign
	 * @param name
	 * @return
	 */
	public int editRole(Role role);
	
	/**
	 * 删除角色
	 * @param roleid
	 * @return
	 */
	public int delRole(String roleid);
	
	/**
	 * 删除角色与权限的映射
	 * @param roleid
	 */
	public void delRoleMapAction(String roleid);
}
