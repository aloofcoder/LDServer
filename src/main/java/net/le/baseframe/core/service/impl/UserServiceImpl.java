package net.le.baseframe.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.le.baseframe.core.dao.UserDao;
import net.le.baseframe.core.entity.User;
import net.le.baseframe.core.service.UserService;
import net.le.baseframe.exception.AppControllerException;
import net.le.baseframe.exception.AppServiceException;
import net.le.baseframe.util.CheckParamUtils;
import net.le.baseframe.util.UserNumberUtil;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public PageBean getUsers(PageQuest pageQuest) {
        int pageNum = pageQuest.getPageNum();
        int pageSize = pageQuest.getPageSize();
        if (pageNum < 0) {
            throw new AppControllerException("pageNum必须大于0！");
        }
        if (pageSize < 0) {
            throw new AppControllerException("pageSize必须大于0！");
        }
        String condition = pageQuest.getCondition();
        int startIndex = pageQuest.getStartIndex(pageNum, pageSize);
        List<User> list = userDao.searchUser(condition, startIndex, pageSize);
        int count = userDao.getRowCount(condition);
        int totalPage = PageBean.getTotalPage(pageSize, count);
        PageBean pageBean = new PageBean(pageNum, pageSize, totalPage, count, list);
        return pageBean;
    }

    @Override
    public User getUser(Long userNumber) {
        CheckParamUtils.isNull(userNumber, "用户编号不能为空！");
        User user = userDao.searchUserByUserNum(userNumber);
        return user;
    }

    @Override
    public int addUser(User user) {
        CheckParamUtils.isNull(user, "用户不能为空！");
        user.setUserNumber(UserNumberUtil.getNumber());
        int count = userDao.insertUser(user);
        return count;
    }

    @Override
    public int renovateUser(User user) {
        CheckParamUtils.isNull(user, "用户不能为空！");
        int count = userDao.updateUser(user);
        return count;
    }

    @Override
    public int removeUser(Long id) {
        CheckParamUtils.isNull(id, "用户信息Id不能为空！");
        int count = userDao.deleteUser(id);
        return count;
    }

}
