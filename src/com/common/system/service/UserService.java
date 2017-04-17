package com.common.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.system.dao.DeptDao;
import com.common.system.dao.UserInfoDao;
import com.common.system.pojo.Actions;
import com.common.system.pojo.Dept;
import com.common.system.pojo.Menus;
import com.common.system.pojo.Pageinfo;
import com.common.system.pojo.UserAndType;
import com.common.system.pojo.UserInfo;
import com.common.system.pojo.UserMapOrg;
import com.common.system.pojo.UserType;

@Service
public class UserService {
	private Logger log = Logger.getLogger(UserService.class);
	
	@Resource
	private UserInfoDao userDao;
	
	@Resource
	private DeptDao deptDao;
	
	//--------------------------------权限---------------------------------------
	/**
	 * 查询权限(以权限分栏信息分类)
	 */
	public Map<String, List<Actions>> queryActionsAll() {
		//菜单分栏信息
		List<Menus> menuList = userDao.queryAllMenu();
		//权限信息
		List<Actions> actionList = userDao.queryActionsAll();
		log.info("分栏信息：" + menuList.size() + ", 权限信息:" + actionList.size());
		
		Map<String, List<Actions>> map = new LinkedHashMap<String, List<Actions>>();
		//以菜单分栏为分组对象
		for(int i=0; i<menuList.size(); i++) {
			String menuid= menuList.get(i).getMenusid();
			String menuname = menuList.get(i).getMenuname();
			List<Actions> actionRec = new ArrayList<Actions>();
			boolean flag = false;
			for(int k=0; k<actionList.size(); k++) {
				Actions ac = actionList.get(k);
				if(menuid.equals(ac.getMenugroup())) {
					actionRec.add(ac);
					flag = true;
				}
			}
			
			if(flag) {
				map.put(menuname, actionRec);
			}
		}
		return map;
	}
	
	//--------------------------------用户信息---------------------------------------
	/**
	 * 查询所有用户信息
	 * @return
	 */
	public List<UserInfo> queryAllUser(Pageinfo page, UserInfo user) {
		int end = page.getPage()*page.getRows();
		int begin = (page.getPage()-1)*page.getRows()+1;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("username", user.getUsername());
		map.put("chname", user.getChname());
		map.put("end", end);
		map.put("begin", begin);
		List<UserInfo> list = userDao.queryAllUser(map);
		return list;
	}
	
	/**
	 * 查询所有的用户数量
	 * @return
	 */
	public int queryAllCount(UserInfo user) {
		return userDao.queryAllCount(user);
	}
	
	/**
	 * 批量删除用户
	 * @param userid
	 * @return
	 */
	@Transactional
	public int deleteUsers(String[] userids){
		int i = userDao.batchDeleteUser(userids);
		int ii = userDao.batchDelUserAndType(userids);
		return i;
	}
	
	/**
	 * 根据用户id查询用户信息
	 * @param userid
	 * @return
	 */
	public UserInfo getUserMapByid(String userid) {
		UserInfo user = userDao.getUserMapByUserid(userid);
		Dept dept = deptDao.findDeptByUser(userid);
		user.setDept(dept.getName());
		return user;
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	@Transactional
	public void editUser(UserInfo user) {
		//修改用户基本信息
		userDao.editUserByid(user);
		//修改用户与用户类型映射
		UserAndType ut = new UserAndType();
		ut.setUserid(user.getUserid());
		ut.setUsertypesign(user.getRole());
		userDao.editUserAndType(ut);
		//用户与机构
		UserMapOrg uo = new UserMapOrg();
		uo.setUserid(user.getUserid());
		uo.setOrgid(user.getOrg());
		userDao.editUserMapOrg(uo);
	}
	//--------------------------------用户类型---------------------------------------
	/**
	 * 查询所有用户类型信息
	 * @return
	 */
	public List<UserType> queryAllUsertype() {
		List<UserType> list = userDao.queryAllUsertype();
		return list;
	}
	
	//--------------------------------用户与用户类型映射---------------------------------------
	/**
	 * 新增用户
	 * @param user
	 */
	@Transactional
	public void addUser(UserInfo user) {
		//保存用户信息
		userDao.addUser(user);
		
		String userid = user.getUserid();
		UserAndType ut = new UserAndType();
		ut.setUserid(userid);
		ut.setUsertypesign(user.getRole());
		//保存用户与类型关系
		userDao.addUserAndType(ut);
		//保存用户与机构关系
		userDao.addUserOrgMap(user);
		//保存用户与岗位关系
		deptDao.addUserMapDept(userid, user.getDept());
	}
	
	//--------------------------------用户与用机构映射---------------------------------------
	/**
	 * 新增用户与机构关系
	 * @param user
	 * @return
	 */
	@Transactional
	public int addUserOrgMap(UserInfo user){
		return userDao.addUserOrgMap(user);
	}
}
