package com.common.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.common.system.dao.ActionsDao;
import com.common.system.pojo.Actions;
import com.common.system.pojo.Menus;
import com.common.system.pojo.UserType;

/**
 * @discription 权限信息
 * @author yangzhan-xps13
 * @date 2016年7月31日-下午1:42:38
 */
@Service
public class ActionsService {
	private Logger log = Logger.getLogger(ActionsService.class);
	
	@Resource
	private ActionsDao actionsDao;
	
	/**
	 * 查询所有权限
	 * @return
	 */
	public List<Actions> queryAllActions(){
		List<Actions> list = actionsDao.queryAllActions();
		return list;
	}
	
	/**
	 * 查询所有菜单栏
	 * @return
	 */
	public List<Menus> findAllMenus(){
		return actionsDao.findAllMenus();
	}
	
	/**
	 * 编辑权限与菜单栏映射
	 * @param ac
	 * @return
	 */
	public int editActionMenu(Actions ac){
		return actionsDao.editActionMenu(ac);
	}
	
	/**
	 * 查询所有用户类型
	 * @return
	 */
	public List<UserType> findAllUsertype() {
		return actionsDao.findAllUsertype();
	}
	
	/**
	 * 根据用户类型查询：具有和不具有的权限 
	 * @param usertypesign
	 * @return
	 */
	public List<Menus> findActionAbled(String usertypesign) {
		return actionsDao.findActionAbled(usertypesign);
	}
	
	/**
	 * 删除权限与用户类型关系
	 * @param actionid
	 * @param usertypesign
	 * @return
	 */
	public int delActionMapping(String actionid, String usertypesign) {
		return actionsDao.delActionMapping(actionid, usertypesign);
	}
	
	/**
	 * 新增权限与用户类型关系
	 * @param actionid
	 * @param usertypesign
	 * @return
	 */
	public int addActionMapping(String actionid, String usertypesign) {
		return actionsDao.addActionMapping(actionid, usertypesign);
	}
}
