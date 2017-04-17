package com.common.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.system.dao.DeptDao;
import com.common.system.pojo.Dept;
import com.common.system.pojo.DeptTree;
import com.common.system.pojo.Organization;

/**
 * @discription 岗位管理
 * @author yangzhan-xps13
 * @date 2016-8-28 14:13:06
 */
@Service
public class DeptService {
	private Logger log = Logger.getLogger(DeptService.class);
	
	@Resource
	private DeptDao deptDao;
	
	/**
	 * 新增岗位
	 */
	@Transactional
	public int addDept(Dept dept) {
		return deptDao.addDept(dept);
	}
	
	/**
	 * 删除岗位
	 */
	@Transactional
	public int delDeptByid(String id) {
		int i = deptDao.delDeptByid(id);
		//更新岗位与人员映射
		deptDao.resetUserMapDept(id);
		return i;
	}
	
	/**
	 * 删除岗位
	 */
	@Transactional
	public int editDept(Dept dept) {
		return deptDao.editDept(dept);
	}
	
	/**
	 * 组装树形结构
	 * @return
	 */
	public DeptTree findDeptTree(){
		List<Dept> list = deptDao.findDept();
		DeptTree tree = new DeptTree();
		//拼接树形结构
		for(int i=0; i<list.size(); i++) {
			Dept dept = list.get(i);
			String prentid = dept.getParentid();
			if(prentid == null) {
				list.remove(i);
				tree.setId(dept.getId());
				tree.setText(dept.getName());
				callback(tree, list);
				break;
			}
		}
		return tree;
	}
	
	/**
	 * 岗位列表
	 * @return
	 */
	public List<Dept> findDeptDetail() {
		List<Dept> list = deptDao.findDeptDetail();
		float totalPerson = deptDao.deptCount(null);
		for(int i=0; i<list.size(); i++) {
			int person = list.get(i).getPersons();
			float per = Math.round(person/totalPerson*100);
			list.get(i).setPer(per);
		}
		return list;
	}
	
	/**
	 * 查询岗位人数
	 * @param deptid
	 * @return
	 */
	public int deptCount(String deptid) {
		return deptDao.deptCount(deptid);
	}
	
	/**
	 * 根据用户id查询岗位信息
	 * @param userid
	 * @return
	 */
	public Dept findDeptByUser(String userid) {
		return deptDao.findDeptByUser(userid);
	}
	
	/**
	 * 回调函数
	 * @param root
	 * @param list
	 */
	private void callback(DeptTree root, List<Dept> list) {
		List<DeptTree> children = new ArrayList<DeptTree>();
		for(int i=0; i<list.size(); i++) {
			Dept obj = list.get(i);
			if(root.getId().equals(obj.getParentid())) {
				//list.remove(i);//切记不能删除,因为会造成list的下标不停的更新
				DeptTree child = new DeptTree();
				child.setId(obj.getId());
				child.setText(obj.getName());
				child.setParentid(root.getId());
				children.add(child);
			}
		}
		root.setChildren(children);
		if(root.getChildren().isEmpty()) {
			return;
		} else {
			for(int i=0; i<children.size(); i++) {
				//回调
				callback(children.get(i), list);
			}
		}
	}
}
