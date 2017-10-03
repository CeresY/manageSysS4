package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.common.system.dao.UserInfoDao;
import com.common.system.pojo.Actions;
import com.common.system.pojo.Menus;
import com.common.system.pojo.UserAndType;
import com.common.system.pojo.UserInfo;
import com.common.system.pojo.UserMapOrg;
import com.common.system.pojo.UserType;

public class TestUserMybatis {

	private ApplicationContext ctx = null;

	public static void main(String[] args) {
		TestUserMybatis foo = new TestUserMybatis();
		foo.findUserType();
		System.out.println("完成");
	}

	public TestUserMybatis() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	/**
	 * 修改用户与用户类型映射
	 */
	public void editUserType() {
		UserInfoDao dao = (UserInfoDao) ctx.getBean("userInfoDao");
		UserAndType ut = new UserAndType();
		ut.setUserid("80");
		ut.setUsertypesign("user");
		System.out.println("结果："+dao.editUserAndType(ut));
	}
	
	/**
	 * 修改用户信息
	 */
	public void editUser() {
		UserInfoDao dao = (UserInfoDao) ctx.getBean("userInfoDao");
		UserInfo user = new UserInfo();
		user.setUserid("45");
		user.setChname("用户02mofify");
		System.out.println("结果："+dao.editUserByid(user));
	}
	
	/**
	 * 根据id查询用户信息（包含映射关系）
	 */
	public void getUserMapByUserid() {
		UserInfoDao dao = (UserInfoDao) ctx.getBean("userInfoDao");
		UserInfo user = dao.getUserMapByUserid("103");
		System.out.println(user.getUserid() + "," +user.getUsername()+","+user.getChname());
		List<UserAndType> utlist = user.getUtList();
		for(int i=0; i<utlist.size(); i++) {
			System.out.println("\t 类型："+utlist.get(i).getUserid()+","+utlist.get(i).getUsertypesign());
		}
		List<UserMapOrg> uolist = user.getUoList();
		for(int i=0; i<uolist.size(); i++) {
			System.out.println("\t 机构："+uolist.get(i).getUserid()+","+uolist.get(i).getOrgid());
		}
	}
	
	/**
	 * 统计所有用户数量
	 */
	public void queryAllCount() {
		UserInfoDao dao = (UserInfoDao) ctx.getBean("userInfoDao");
		UserInfo user = new UserInfo();
		//user.setUsername("01");
		System.out.println(dao.queryAllCount(user));
	}
	
	
	/**
	 * 批量删除用户
	 */
	public void batchDeleteUser() {
		UserInfoDao dao = (UserInfoDao) ctx.getBean("userInfoDao");
		int i = dao.batchDeleteUser(new String[]{"33","38"});
		System.out.println(i);
	}
	
	/**
	 * 新增用户与用户类型映射
	 * @param ut
	 * @return
	 */
	public void addUserAndType(){
		UserInfoDao dao = (UserInfoDao) ctx.getBean("userInfoDao");
		UserAndType ut = new UserAndType();
		ut.setUserid("abc1");
		ut.setUsertypesign("user");
		dao.addUserAndType(ut);
	}
	
	/**
	 * 查询所有用户类型
	 */
	@Test
	public void findUserType() {
		UserInfoDao dao = (UserInfoDao) ctx.getBean("userInfoDao");
		/*List<UserType> list = dao.queryAllUsertype();
		for(UserType u : list) {
			System.out.println(u.getTypesign() + ", " + u.getTypename());
		}*/
		
		List<Map<String, Object>> list = dao.usertypeMap();
		for(Map map : list) {
			Set set = map.keySet();
			Iterator it = set.iterator();
			while(it.hasNext()) {
				String key = (String) it.next();
				System.out.print(key + "=" + map.get(key)+", ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 新增用户类型
	 */
	public void addUserType() {
		UserInfoDao dao = (UserInfoDao) ctx.getBean("userInfoDao");
		UserType u = new UserType();
		u.setTypesign("user");
		u.setTypename("普通用户");
		int i = dao.addUsertype(u);
		System.out.println(i);
	}
	
	/**
	 * 新增用户
	 */
	public void addUser() {
		UserInfoDao dao = (UserInfoDao) ctx.getBean("userInfoDao");
		for(int i=1; i<35; i++) {
			UserInfo user = new UserInfo();
			user.setUsername("User0"+i);
			//user.setPassword("123456");
			user.setChname("测试人员0"+i);
			//user.setContact("");
			dao.addUser(user);
		}
	}

	/**
	 * 查询所有用户
	 */
	public void queryAllUser() {
		UserInfoDao dao = (UserInfoDao) ctx.getBean("userInfoDao");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("username", "01");
		map.put("chname", "员");
		map.put("end", 10);
		map.put("begin", 1);
		List<UserInfo> list = dao.queryAllUser(map);
		for(UserInfo u : list) {
			System.out.println(u.getUserid() + ", " + u.getChname() + ", " + u.getUsername() + ", " + u.getCreatetime());
		}
	}
	
	/**
	 * 新增权限
	 */
	public void addActions() {
		UserInfoDao dao = (UserInfoDao) ctx.getBean("userInfoDao");
		Actions ac = new Actions();
		ac.setActionname("新增用户");
		ac.setActionsign("addUser");
		ac.setIsview("1");
		int i = dao.addActions(ac);
		System.out.println("新增成功: "+ i);
	}
	
	/**
	 * 新增"权限分栏"
	 */
	public void addMenus() {
		UserInfoDao dao = (UserInfoDao) ctx.getBean("userInfoDao");
		Menus me = new Menus();
		me.setMenusid("99");
		me.setMenuname("系统管理");
		int i = dao.addMenus(me);
		System.out.println("新增成功: "+ i);
	}
	
	/**
	 * 查询权限(以权限分栏信息分类)
	 */
	public void queryActionsAll() {
		List<Actions> list = new ArrayList<Actions>();
		UserInfoDao dao = (UserInfoDao) ctx.getBean("userInfoDao");
		list = dao.queryActionsAll();
		for(Actions ac : list){
			System.out.println("权限：" + ac.getActionid() + ", " + ac.getActionname() + ", " + ac.getMenugroup() + ", " + ac.getMenuname());
		}
	}
}
