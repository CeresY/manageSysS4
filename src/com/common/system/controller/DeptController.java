package com.common.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.system.pojo.Dept;
import com.common.system.pojo.DeptTree;
import com.common.system.service.DeptService;
import com.common.system.util.MsgUtil;


//prototype，多例，每次请求，会产生一个controller
@Scope("prototype")
@Controller
@RequestMapping(value="/dept")
public class DeptController {
	private Logger log = Logger.getLogger(DeptController.class);

	/*
	 * 推荐使用resource
	 * @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按byName自动注入罢了
	 */
	@Resource
	private DeptService deptService;
	
	/**
	 * 查询岗位信息- easyui-tree
	 * @return
	 */
	@RequestMapping(value="/findDeptTree")
	@ResponseBody
	public List<DeptTree> findDeptTree() {
		DeptTree dept = deptService.findDeptTree();
		List<DeptTree> list = new ArrayList<DeptTree>();
		if(dept.getId() != null) {
			list.add(dept);
		}
		return list;
	}
	
	/**
	 * 岗位列表
	 * @return
	 */
	@RequestMapping(value="/findDeptDetail")
	@ResponseBody
	public Map<String, Object> findDeptDetail() {
		int total = deptService.deptCount(null);
		List<Dept> list = deptService.findDeptDetail();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("rows", list);
		
		List<Dept> foot = new ArrayList<Dept>();
		Dept p = new Dept();
		p.setName("总人数");
		p.setPersons(total);
		foot.add(p);
		map.put("footer", foot);
		return map;
	}
	
	/**
	 * 新增岗位
	 */
	@RequestMapping(value="/addDept")
	@ResponseBody
	public Map<String, String> addDept(Dept dept) {
		int i = deptService.addDept(dept);
		if(i==1) {
			return MsgUtil.getMsgMap(true, "新增岗位");
		} else {
			return MsgUtil.getMsgMap(false, "新增岗位");
		}
	}
	
	/**
	 * 根据主键删除岗位
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delDept")
	@ResponseBody
	public Map<String, String> delDept(String id) {
		int i = deptService.delDeptByid(id);
		if(i==1) {
			return MsgUtil.getMsgMap(true, "删除岗位");
		} else {
			return MsgUtil.getMsgMap(false, "删除岗位");
		}
	}
	
	/**
	 * 修改岗位名称
	 * @param orgid
	 * @param orgname
	 * @return
	 */
	@RequestMapping(value="/editDept")
	@ResponseBody
	public Map<String, String> editDept(Dept dept) {
		int i = deptService.editDept(dept);
		if(i==1) {
			return MsgUtil.getMsgMap(true, "修改岗位");
		} else {
			return MsgUtil.getMsgMap(false, "修改岗位");
		}
	}
	
	/**
	 * 根据用户id查询岗位信息
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="/findDeptByUser")
	@ResponseBody
	public Dept findDeptByUser(String userid) {
		return deptService.findDeptByUser(userid);
	}
	
}
