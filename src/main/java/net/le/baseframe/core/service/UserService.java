package net.le.baseframe.core.service;

import net.le.baseframe.core.entity.User;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;

import java.util.List;

public interface UserService {

    /**
     * 获取用户信息
     * @return
     */
    PageBean getUsers(PageQuest pageQuest);

    /**
     * 通过用户编号获取用户信息
     * @param userNumber
     * @return
     */
    User getUser(String userNumber);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int renovateUser(User user);

    /**
     * 删除用户
     */
    int removeUser(Long id);

}
