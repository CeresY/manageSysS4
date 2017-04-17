package test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.common.system.dao.ActionsDao;
import com.common.system.pojo.Actions;
import com.common.system.pojo.Menus;

public class TestActionsMenus {

	private ApplicationContext ctx = null;

	public static void main(String[] args) {
		TestActionsMenus foo = new TestActionsMenus();
		foo.findActionAbled();
		System.out.println("完成");
	}

	public TestActionsMenus() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public void findActionAbled() {
		ActionsDao dao = (ActionsDao) ctx.getBean("actionsDao");
		List<Menus> menus = dao.findActionAbled("system");
		for(int i=0; i<menus.size(); i++) {
			List<Actions> acs = menus.get(i).getActions();
			System.out.println(menus.get(i).getMenusid() + ", " + menus.get(i).getMenuname());
			for(int j=0; j<acs.size(); j++) {
				System.out.println("    " + acs.get(j).getActionid() + ", " + acs.get(j).getActionname() + ", " + acs.get(j).getIsabled());
			}
		}
	}
	
}
