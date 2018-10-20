package net.le.baseframe.core.user;

import net.le.baseframe.core.entity.User;
import net.le.baseframe.core.service.UserService;
import net.le.baseframe.util.UserNumberUtil;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {

    @Autowired
    private UserService userService;


    @Test
    public void testGetAllUser() {
        PageQuest pageQuest = new PageQuest();
        pageQuest.setPageNum(1);
        pageQuest.setPageSize(10);
        PageBean pageBean = userService.getUsers(pageQuest);
        System.out.println("pageBean ==> " + pageBean);
        Assert.assertTrue(pageBean.getList().size() == 4);
    }


    @Test
    public void testAddUser () {
        User user = new User();
        user.setUserNumber(UserNumberUtil.getNumber());
        user.setUserName("韩小乐");
        user.setUserMobile("18149197030");
        user.setUserGender(0);
        user.setUserAddress("咸阳");
        user.setUserIdCard("610425199705131718");
        user.setUserIdCardUrl("d:/idcardurl/1718.jpg");
        user.setUserImgUrl("d:/imgurl/1718.jpg");
        user.setLinkmanName1("韩乐1");
        user.setLinkmanMobile1("18149197031");
        user.setLinkmanName2("韩乐2");
        user.setLinkmanMobile2("18149197032");
        user.setLinkmanName3("韩乐3");
        user.setLinkmanMobile3("18149197033");
        user.setUserStatus(0);
        user.setCreateUser("韩乐");
        user.setEditUser("韩乐");
        System.out.println(user);
        int count = userService.addUser(user);
        System.out.println(count);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void testUpdateUser () {
        PageQuest pageQuest = new PageQuest();
        pageQuest.setPageNum(1);
        pageQuest.setPageSize(10);
        PageBean pageBean = userService.getUsers(pageQuest);
        List<User> list = (List<User>)pageBean.getList();
        User user = list.get(0);
        user.setUserName("韩乐乐");
        int count = userService.renovateUser(user);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void testDeleteUser () {
        int count = userService.removeUser(Long.parseLong("20181009230202238"));
        Assert.assertTrue(count == 1);
    }

    @Test
    public void testGetUser () {
        User user = userService.getUser(Long.parseLong("20181009233548157"));
        System.out.println("user ======> " + user);
        Assert.assertTrue(user != null);
    }
}
