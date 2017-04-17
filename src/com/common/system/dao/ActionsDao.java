package com.common.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.system.pojo.Actions;
import com.common.system.pojo.Menus;
import com.common.system.pojo.UserType;

/**
 * @discription 权限管理
 * @author yangzhan-xps13
 * @date 2016年7月31日-下午1:44:24
 */
@Repository
public interface ActionsDao {
	
	/**
	 * 查询所有权限
	 * @return
	 */
	public List<Actions> queryAllActions();
	
	/**
	 * 查询所有菜单栏
	 * @return
	 */
	public List<Menus> findAllMenus();
	
	/**
	 * 编辑权限与菜单栏映射
	 * @param ac
	 * @return
	 */
	public int editActionMenu(Actions ac);
	
	/**
	 * 查询所有用户类型
	 * @return
	 */
	public List<UserType> findAllUsertype();
	
	/**
	 * 根据用户类型查询：具有和不具有的权限 
	 * @param usertypesign
	 * @return
	 */
	public List<Menus> findActionAbled(String usertypesign);
	
	/**
	 * 删除权限与用户类型关系
	 * @param actionid
	 * @param usertypesign
	 * @return
	 */
	public int delActionMapping(String actionid, String usertypesign);
	
	/**
	 * 新增权限与用户类型关系
	 * @param actionid
	 * @param usertypesign
	 * @return
	 */
	public int addActionMapping(String actionid, String usertypesign);
}
