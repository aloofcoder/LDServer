package net.le.baseframe.core.dao;

import net.le.baseframe.core.entity.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerDao {

    List<Manager> searchManagers(@Param("startIndex") int startIndex,
                                 @Param("pageSize") int pageSize,
                                 @Param("condition") String condition);
    int getRowCount(@Param("condition") String condition);
    Manager searchManagerByManagerNumber(String managerNumber);
    int insertManager(Manager manager);
    int updateManager(Manager manager);
    int deleteManager(Long id);
}
