package net.le.baseframe.core.manager;

import net.le.baseframe.core.entity.Manager;
import net.le.baseframe.core.service.ManagerService;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestManagerService {

    @Resource
    private ManagerService managerService;

    @Test
    public void testAddManager() {
        Manager manager = new Manager();
        manager.setManagerNumber(Long.parseLong("18149197030"));
        manager.setManagerPwd("123456");
        manager.setManagerName("韩乐");
        manager.setManagerStatus(0);
        manager.setCreateUser("韩乐");
        manager.setEditUser("韩乐");
        int count = managerService.addManager(manager);
        Assert.assertTrue(count == 1);
    }
    
    @Test
    public void testSearchManagers () {
        PageQuest pageQuest = new PageQuest(1, 10 ,null);
        PageBean pageBean = managerService.getManagers(pageQuest);
        Assert.assertTrue(pageBean.getList().size() == 0);
    }

    @Test
    public void testRenovateManager () {
        PageQuest pageQuest = new PageQuest(1, 10 ,null);
        PageBean pageBean = managerService.getManagers(pageQuest);
        Manager manager = (Manager) pageBean.getList().get(0);
        manager.setManagerName("韩乐乐");
        int count = managerService.renovateManager(manager);
        Assert.assertTrue(count == 1);
    }


    @Test
    public void testRemoveManager () {
        int count = managerService.removeManager(Long.parseLong("1"));
        Assert.assertTrue(count == 1);
    }
}
