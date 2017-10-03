package com.common.system.controller;

/**
 * <p>标题：基站基本信息，Controller</p>
 * <p>描述：基站位置、属性查询，列表展示。</p>
 * <p>Copyright：Copyright(c) 2015 founder</p>
 * <p>日期：2015-6-18</p>
 * @author	zhouyujie
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.pojo.Actions;
import com.common.system.pojo.Pageinfo;
import com.common.system.pojo.UserInfo;
import com.common.system.pojo.UserType;
import com.common.system.service.UserService;
import com.common.system.util.MsgUtil;


//prototype，多例，每次请求，会产生一个controller
@Scope("prototype")
@Controller
@RequestMapping(value="/user")
public class UserInfoController {
	private Logger log = Logger.getLogger(UserInfoController.class);

	/*
	 * 推荐使用resource
	 * @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按byName自动注入罢了
	 */
//	@Autowired
	@Resource
	private UserService userService;

	//-----------------------------页面跳转请求----------------------------------
	/**
	 * 登陆入口
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/login")
	public ModelAndView login(@ModelAttribute("userInfo")UserInfo user) {
		Map<String, List<Actions>> map = userService.queryActionsAll();
		ModelAndView view = new ModelAndView("main");
		log.info(user.getUsername());
		view.addObject("username", user.getUsername());
		view.addObject("menus", map);
		return view;
	}
	
	
	//-----------------------------权限----------------------------------
	/**
	 * 查询权限(以权限分栏信息分类)
	 */
	@RequestMapping(value="/queryActionsAll")
	@ResponseBody
	public Map<String, List<Actions>> queryActionsAll() {
		Map<String, List<Actions>> map = userService.queryActionsAll();
		return map;
	}
	
	//-----------------------------用户----------------------------------
	/**
	 * 查询所有用户信息
	 * @return
	 */
	@RequestMapping(value="/queryAllUser")
	@ResponseBody
	public Map<String, Object> queryAllUser(@ModelAttribute("pageinfo")Pageinfo page, UserInfo user) {
		log.info("page="+page.getPage()+", rows="+page.getRows());
		log.info("username:"+user.getUsername()+",chname:"+user.getChname());
		
		int count = userService.queryAllCount(user);
		List<UserInfo> list = userService.queryAllUser(page, user);
		
		log.info("recodes:"+list.size());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", count);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 新增用户
	 * @return
	 */
	@RequestMapping(value="/addUser")
	@ResponseBody
	public Map<String, String> addUser(@ModelAttribute("userInfo")UserInfo user) {
		Map<String, String> map = new HashMap<String, String>();
		userService.addUser(user);
		map.put("success", "true");
		map.put("msg", "用户添加成功");
		return map;
	}
	
	/**
	 * 修改用户
	 * @return
	 */
	@RequestMapping(value="/editUser")
	@ResponseBody
	public Map<String, String> editUser(@ModelAttribute("userInfo")UserInfo user) {
		userService.editUser(user);
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", "true");
		map.put("msg", "修改用户成功!");
		return map;
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	@RequestMapping(value="/delUsers")
	@ResponseBody
	public Map<String, String> delUsers(@RequestParam(value="userids")String[] userids) {
		int i = userService.deleteUsers(userids);
		Map<String, String> map = new HashMap<String, String>();
		if(i == userids.length) {
			map.put("success", "true");
			map.put("msg", "用户删除成功");
		} else {
			map.put("success", "false");
			map.put("msg", "用户删除失败");
		}
		return map;
	}
	
	/**
	 * 根据用户id查询用户
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="/getUserMapByid")
	@ResponseBody
	public UserInfo getUserMapByid(String userid) {
		return userService.getUserMapByid(userid);
	}
	
	//-----------------------------用户类型----------------------------------
	/**
	 * 查询所有用户类型信息
	 * @return
	 */
	@RequestMapping(value="/queryAllUsertype")
	@ResponseBody
	public List<UserType> queryAllUsertype() {
		return userService.queryAllUsertype();
	}
	
	
	//--------------------------------用户与用机构映射---------------------------------------
	/**
	 * 新增用户与机构关系
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/addUserOrgMap")
	@ResponseBody
	public Map<String, String> addUserOrgMap(UserInfo user){
		int i = userService.addUserOrgMap(user);
		if(i==1) {
			return MsgUtil.getMsgMap(true, "新增用户与机构关系");
		} else {
			return MsgUtil.getMsgMap(false, "新增用户与机构关系");
		}
	}
}
