package net.le.baseframe.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.le.baseframe.core.dao.ManagerDao;
import net.le.baseframe.core.entity.Manager;
import net.le.baseframe.core.service.ManagerService;
import net.le.baseframe.exception.AppControllerException;
import net.le.baseframe.exception.AppServiceException;
import net.le.baseframe.util.CheckParamUtils;
import net.le.baseframe.util.DateUtils;
import net.le.baseframe.util.SecurityUtils;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ManagerServiceImpl implements ManagerService {

    @Resource
    private ManagerDao managerDao;


    @Override
    public PageBean getManagers(PageQuest pageQuest) {
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
        List<Manager> list = managerDao.searchManagers(startIndex, pageSize, condition);
        int count = managerDao.getRowCount(condition);
        int totalPage = PageBean.getTotalPage(pageSize, count);
        PageBean pageBean = new PageBean(pageNum, pageSize, totalPage, count, list);
        return pageBean;
    }

    @Override
    public Manager managerLogin(String managerNumber, String managerPwd,long nowMillis) {
        CheckParamUtils.isNull(managerNumber, "登陆账号不能为空！");
        CheckParamUtils.isNull(managerNumber, "登陆密码不能为空！");
        Manager manager = managerDao.searchManagerByManagerNumber(managerNumber);
        if (manager == null) {
            throw new AppServiceException("用户" + managerNumber + "不存在，请检查您输入的账号！");
        }
        String pwd = manager.getManagerPwd();
        if (!pwd.equals(managerPwd)) {
            throw new AppServiceException("密码错误，请检查你的登录密码！");
        }
        manager.setLoginTime(nowMillis);
        manager.setEditUser(manager.getManagerName());
        int count = managerDao.updateManager(manager);
        CheckParamUtils.countErr(count, "更新管理登录记录失败");
        return manager;
    }

    @Override
    public int addManager(Manager manager) {
        CheckParamUtils.isNull(manager, "管理员信息不能为空！");
        int count = managerDao.insertManager(manager);
        CheckParamUtils.countErr(count, "添加管理员信息失败");
        return count;
    }

    @Override
    public int renovateManager(Manager manager) {
        CheckParamUtils.isNull(manager, "管理员信息不能为空！");
        int count = managerDao.updateManager(manager);
        CheckParamUtils.countErr(count, "更新管理员信息失败");
        return count;
    }

    @Override
    public int removeManager(Long id) {
        CheckParamUtils.isNull(id, "管理员信息Id不能为空！");
        int count = managerDao.deleteManager(id);
        CheckParamUtils.countErr(count, "删除管理员信息失败");
        return count;
    }
}
