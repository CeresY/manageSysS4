package com.common.system.controller;

import java.util.HashMap;

/**
 * <p>标题：权限管理，Controller</p>
 * <p>描述：赋权限、增册改。</p>
 * <p>Copyright：Copyright(c) 2015 founder</p>
 * <p>日期：2016-7-31</p>
 * @author	yz
 */

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.system.pojo.Actions;
import com.common.system.pojo.Menus;
import com.common.system.pojo.UserType;
import com.common.system.service.ActionsService;


//prototype，多例，每次请求，会产生一个controller
@Scope("prototype")
@Controller
@RequestMapping(value="/actions")
public class ActionsController {
	private Logger log = Logger.getLogger(ActionsController.class);

	/*
	 * 推荐使用resource
	 * @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按byName自动注入罢了
	 */
//	@Autowired
	@Resource
	private ActionsService actionsService;

	/**
	 * 查询所有权限
	 * @return
	 */
	@RequestMapping(value="/queryAllActions")
	@ResponseBody
	public List<Actions> queryAllActions() {
		List<Actions> list = actionsService.queryAllActions();
		log.info("权限记录:"+list.size());
		//测试
		return list;
	}
	
	/**
	 * 查询所有菜单栏
	 * @return
	 */
	@RequestMapping(value="/findAllMenus")
	@ResponseBody
	public List<Menus> findAllMenus() {
		return actionsService.findAllMenus();
	}
	
	/**
	 * 编辑权限与菜单栏映射
	 * @param ac
	 * @return
	 */
	@RequestMapping(value="/editActionMenu")
	@ResponseBody
	public Map<String, String> editActionMenu(@ModelAttribute("actions")Actions ac){
		int i = actionsService.editActionMenu(ac);
		Map<String, String> map = new HashMap<String, String>();
		if(i==1) {
			map.put("success", "true");
			return map;
		}
		map.put("success", "false");
		return map;
	}
	
	/**
	 * 查询所有用户类型
	 * @return
	 */
	@RequestMapping(value="/findAllUsertype")
	@ResponseBody
	public List<UserType> findAllUsertype() {
		return actionsService.findAllUsertype();
	}
	
	/**
	 * 根据用户类型查询：具有和不具有的权限 
	 * @param usertypesign
	 * @return
	 */
	@RequestMapping(value="/findActionAbled")
	@ResponseBody
	public List<Menus> findActionAbled(@RequestParam(value="sign")String usertypesign) {
		return actionsService.findActionAbled(usertypesign);
	}
	
	/**
	 * 删除权限与用户类型关系
	 * @param actionid
	 * @param usertypesign
	 * @return
	 */
	@RequestMapping(value="/delActionMapping")
	@ResponseBody
	public Map<String,String> delActionMapping(String actionid, String usertypesign) {
		int i = actionsService.delActionMapping(actionid, usertypesign);
		Map<String, String> map = new HashMap<String, String>();
		if(i==1) {
			map.put("SUCCESS", "true");
			map.put("MSG", "删除权限成功");
		} else {
			map.put("SUCCESS", "false");
			map.put("MSG", "删除权限失败");
		}
		return map;
	}
	
	/**
	 * 新增权限与用户类型关系
	 * @param actionid
	 * @param usertypesign
	 * @return
	 */
	@RequestMapping(value="/addActionMapping")
	@ResponseBody
	public Map<String,String> addActionMapping(String actionid, String usertypesign) {
		int i= actionsService.addActionMapping(actionid, usertypesign);
		Map<String, String> map = new HashMap<String, String>();
		if(i==1) {
			map.put("SUCCESS", "true");
			map.put("MSG", "新增权限成功");
		} else {
			map.put("SUCCESS", "false");
			map.put("MSG", "新增权限失败");
		}
		return map;
	}
}
