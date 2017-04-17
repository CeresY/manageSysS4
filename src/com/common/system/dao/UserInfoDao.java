package com.common.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.common.system.pojo.Actions;
import com.common.system.pojo.Menus;
import com.common.system.pojo.UserAndType;
import com.common.system.pojo.UserInfo;
import com.common.system.pojo.UserMapOrg;
import com.common.system.pojo.UserType;
@Repository
public interface UserInfoDao {
	//-------------------------用户------------------------------
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public int addUser(UserInfo user);
	
	/**
	 * 查询所有用户信息(用于“系统管理-用户管理”)
	 * @return
	 */
	public List<UserInfo> queryAllUser(Map<String, Object> map);
	
	/**
	 * 批量删除用户
	 * @param userid
	 * @return
	 */
	public int batchDeleteUser(String[] userids);
	
	/**
	 * 批量删除用户与用户类型映射
	 * @param userids
	 * @return
	 */
	public int batchDelUserAndType(String[] userids);
	
	/**
	 * 所有用户计数器
	 * @return
	 */
	public int queryAllCount(UserInfo user);
	
	/**
	 * 根据id查询用户信息
	 * @param userid
	 * @return
	 */
//	public UserInfo queryUserByid(String userid);
	
	/**
	 * 根据id查询用户信息(包含映射信息)
	 * @param userid
	 * @return
	 */
	public UserInfo getUserMapByUserid(String userid);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int editUserByid(UserInfo user);
	//------------------------权限-------------------------------
	/**
	 * 新增权限
	 * @param ac
	 * @return
	 */
	public int addActions(Actions ac);
	
	/**
	 * 查询权限(以权限分栏信息分类)
	 */
	public List<Actions> queryActionsAll();
	
	//--------------------菜单分栏-----------------------------------
	/**
	 * 新增“权限分栏”
	 * @param me
	 * @return
	 */
	public int addMenus(Menus me);
	
	/**
	 * 查询所有的菜单分栏信息
	 * @return
	 */
	public List<Menus> queryAllMenu();
	
	//--------------------用户类型-----------------------------------
	/**
	 * 新增用户类型
	 * @param usertype
	 * @return
	 */
	public int addUsertype(UserType usertype);
	
	/**
	 * 查询所有的用户类型信息
	 * @return
	 */
	public List<UserType> queryAllUsertype();
	
	/**
	 * 查询所有的用户类型信息
	 * @return
	 */
	public List<Map<String,Object>> usertypeMap();
	
	//------------------------用户与用户类型映射-------------------------------------
	/**
	 * 新增用户与用户类型映射
	 * @param ut
	 * @return
	 */
	public int addUserAndType(UserAndType ut);
	
	/**
	 * 修改用户与用户类型映射
	 * @param ut
	 * @return
	 */
	public int editUserAndType(UserAndType ut);
	
	//------------------------用户与机构映射-------------------------------------
	/**
	 * 新增用户与机构关系
	 * @param user
	 * @return
	 */
	public int addUserOrgMap(UserInfo user);
	
	/**
	 * 更新用户与机构关系
	 * @param user
	 * @return
	 */
	public int editUserMapOrg(UserMapOrg uo);
}
