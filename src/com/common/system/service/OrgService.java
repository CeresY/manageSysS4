package com.common.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.system.dao.OrgDao;
import com.common.system.pojo.Organization;

/**
 * @discription 机构信息
 * @author yangzhan-xps13
 * @date 2016年7月31日-下午1:42:38
 */
@Service
public class OrgService {
	private Logger log = Logger.getLogger(OrgService.class);
	
	@Resource
	private OrgDao orgDao;
	
	/*
	 * 新增机构
	 */
	@Transactional
	public int addOrg(Organization org) {
		return orgDao.addOrg(org);
	}
	
	/**
	 * 查询机构信息 easyui-tree
	 * @return
	 */
	public Organization findOrg() {
		Organization tree = new Organization();
		List<Organization> list = orgDao.findOrg();
		//拼接树形结构
		for(int i=0; i<list.size(); i++) {
			Organization org = list.get(i);
			String prentid = org.getParentid();
			if(prentid == null) {
				list.remove(i);
				tree.setId(org.getId());
				tree.setText(org.getText());
				//tree.setChecked(true);
				callback(tree, list);
				break;
			}
		}
		return tree;
	}
	
	/**
	 * 根据主键删除机构信息
	 * @param id
	 * @return
	 */
	@Transactional
	public int delOrgByid(String id) {
		return orgDao.delOrgByid(id);
	}
	
	/**
	 * 修改机构名称
	 * @param orgid
	 * @param orgname
	 * @return
	 */
	@Transactional
	public int editOrg(String orgid, String orgname) {
		return orgDao.editOrg(orgid, orgname);
	}
	
	/**
	 * 回调函数
	 * @param root
	 * @param list
	 */
	private void callback(Organization root, List<Organization> list) {
		List<Organization> children = new ArrayList<Organization>();
		for(int i=0; i<list.size(); i++) {
			Organization obj = list.get(i);
			if(root.getId().equals(obj.getParentid())) {
			//list.remove(i);
				Organization child = new Organization();
				child.setId(obj.getId());
				child.setText(obj.getText());
				child.setParentid(root.getId());
				children.add(child);
			}
		}
		root.setChildren(children);
		if(root.getChildren().isEmpty()) {
		} else {
			for(int i=0; i<children.size(); i++) {
				//回调
				callback(children.get(i), list);
			}
		}
	}
	
}
