package net.le.baseframe.core.dao;

import net.le.baseframe.core.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> searchUser(@Param("condition") String condition,
                          @Param("startIndex") int startIndex,
                          @Param("pageSize") int pageSize);
    int getRowCount(@Param("condition") String condition);
    User searchUserByUserNum(String userNumber);
    User searchUserById(Long id);
    int insertUser (User user);
    int updateUser (User user);
    int deleteUser (Long id);
}
