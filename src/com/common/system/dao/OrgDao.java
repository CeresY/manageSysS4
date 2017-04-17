package com.common.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.system.pojo.Organization;

/**
 * @discription 机构管理
 * @author yangzhan-xps13
 * @date 2016年8月8日-下午8:47:18
 */
@Repository
public interface OrgDao {
	
	/*
	 * 新增机构
	 */
	public int addOrg(Organization org);
	
	/**
	 * 查询机构信息
	 * @return
	 */
	public List<Organization> findOrg();
	
	/**
	 * 根据id删除机构
	 * @param id
	 * @return
	 */
	public int delOrgByid(String id);
	
	public int editOrg(String orgid, String orgname);
}
