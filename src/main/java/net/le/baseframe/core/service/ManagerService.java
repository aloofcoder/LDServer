package net.le.baseframe.core.service;

import net.le.baseframe.core.entity.Manager;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;

public interface ManagerService {

    PageBean getManagers(PageQuest pageQuest);
    Manager managerLogin(Long managerNumber, String managerPwd, long nowMillis);
    int addManager(Manager manager);
    int renovateManager(Manager manager);
    int removeManager(Long id);
}
