package com.common.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.system.pojo.Organization;
import com.common.system.service.OrgService;
import com.common.system.util.MsgUtil;


//prototype，多例，每次请求，会产生一个controller
@Scope("prototype")
@Controller
@RequestMapping(value="/org")
public class OrgController {
	private Logger log = Logger.getLogger(OrgController.class);

	/*
	 * 推荐使用resource
	 * @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按byName自动注入罢了
	 */
//	@Autowired
	@Resource
	private OrgService orgService;

	/*
	 * 新增机构
	 */
	@RequestMapping(value="/addOrg")
	@ResponseBody
	public Map<String, String> addOrg(Organization org) {
		int i = orgService.addOrg(org);
		if(i==1) {
			return MsgUtil.getMsgMap(true, "添加机构");
		} else {
			return MsgUtil.getMsgMap(false, "添加机构");
		}
	}
	
	/**
	 * 查询机构信息- easyui-tree
	 * @return
	 */
	@RequestMapping(value="/findOrg")
	@ResponseBody
	public List<Organization> findOrgs() {
		Organization org = orgService.findOrg();
		List<Organization> list = new ArrayList<Organization>();
		list.add(org);
		return list;
	}
	
	/**
	 * 根据主键删除机构
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delOrg")
	@ResponseBody
	public Map<String, String> deleteOrg(String id) {
		int i = orgService.delOrgByid(id);
		if(i==1) {
			return MsgUtil.getMsgMap(true, "删除机构");
		} else {
			return MsgUtil.getMsgMap(false, "删除机构");
		}
	}
	
	/**
	 * 修改机构名称
	 * @param orgid
	 * @param orgname
	 * @return
	 */
	@RequestMapping(value="/editOrg")
	@ResponseBody
	public Map<String, String> editOrg(String orgid, String orgname) {
		int i = orgService.editOrg(orgid, orgname);
		if(i==1) {
			return MsgUtil.getMsgMap(true, "修改机构");
		} else {
			return MsgUtil.getMsgMap(false, "修改机构");
		}
	}
	
}
