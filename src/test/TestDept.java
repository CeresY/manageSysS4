package test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.common.system.dao.DeptDao;
import com.common.system.dao.OrgDao;
import com.common.system.pojo.Dept;
import com.common.system.pojo.Organization;

public class TestDept {

	private ApplicationContext ctx = null;

	public static void main(String[] args) {
		TestDept foo = new TestDept();
		foo.findDeptDetail();
		System.out.println("完成");
	}

	public TestDept() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public void addDept() {
		DeptDao dao = (DeptDao) ctx.getBean("deptDao");
		Dept d = new Dept();
		d.setName("科员");
		d.setParentid("4");
		dao.addDept(d);
	}
	
	public void findDeptDetail(){
		DeptDao dao = (DeptDao) ctx.getBean("deptDao");
		List<Dept> list = dao.findDeptDetail();
		for(int i=0; i<list.size(); i++) {
			System.out.println("id="+list.get(i).getId()+", name=" +list.get(i).getName()+", get_parentId=" +
					list.get(i).get_parentId()+", persons=" + list.get(i).getPersons());
		}
	}
	
	public void deptCount(){
		DeptDao dao = (DeptDao) ctx.getBean("deptDao");
		System.out.println(dao.deptCount(null));
	}
	
	public void findOrgList() {
		OrgDao dao = (OrgDao) ctx.getBean("orgDao");
		Organization tree = new Organization();
		List<Organization> list = dao.findOrg();
		//拼接树形结构
		for(int i=0; i<list.size(); i++) {
			Organization org = list.get(i);
			String prentid = org.getParentid();
			if(prentid == null) {
				list.remove(i);
				tree.setId(org.getId());
				tree.setText(org.getText());
				callback(tree, list);
				break;
			}
		}
		//打印部分
		System.out.println(tree.getId() + " " + tree.getText() + " " + tree.getParentid());
		print(tree.getChildren(), 1);
	}
	
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
	
	
	private void print(List<Organization> list, int level) {
		if(list == null || list.isEmpty()) {
			return ;
		} else {
			for(int i=0; i<list.size(); i++) {
				Organization obj = list.get(i);
				String blank = "";
				int orgin = level, temp = level;
				while(orgin-->0) {
					blank += "\t";
				}
				System.out.println(blank + obj.getId() + " " + obj.getText() + " " + obj.getParentid());
				print(obj.getChildren(), ++temp);
			}
		}
	}

}
