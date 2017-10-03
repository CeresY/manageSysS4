package com.common.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.system.pojo.Dept;

/**
 * @discription 岗位管理 
 * @author yangzhan-xps13
 * @date 2016年8月28日-下午1:48:15
 */
@Repository
public interface DeptDao {
	
	/**
	 * 新增岗位
	 * @param dept
	 * @return
	 */
	public int addDept(Dept dept);
	
	/**
	 * 新增"用户与岗位关系"
	 * @param dept
	 */
	public int addUserMapDept(String userid, String deptid);
	
	/**
	 * 更新用户与岗位映射
	 * @param dept
	 */
	public void editUserMapDept(Dept dept);
	
	/**
	 * 查询岗位基本信息
	 * @param dept
	 * @return
	 */
	public List<Dept> findDept();
	
	/**
	 * 查询岗位基本信息及相关信息
	 * @return
	 */
	public List<Dept> findDeptDetail();
	
	/**
	 * 查询该岗位下员工人数
	 * @param deptid
	 * @return
	 */
	public int deptCount(String deptid);
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	public int delDeptByid(String id);
	
	/**
	 * 更新岗位信息
	 * @param dept
	 */
	public int editDept(Dept dept);
	
	/**
	 * 重置岗位与人员映射
	 * @param deptid
	 */
	public void resetUserMapDept(String deptid);
	
	/**
	 * 根据用户id查询岗位信息
	 * @param userid
	 * @return
	 */
	public Dept findDeptByUser(String userid);
}
