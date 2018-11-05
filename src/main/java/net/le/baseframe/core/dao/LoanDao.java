package net.le.baseframe.core.dao;

import net.le.baseframe.core.entity.Loan;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LoanDao {
    // 分页获取贷款信息
    List<Map<String, Object>> searchLoans(@Param("startIndex") int startIndex,
                                          @Param("pageSize") int pageSize,
                                          @Param("condition") String condition);
    // 获取贷款信息总条数
    int getRowCount(@Param("condition") String condition);
    // 通过用户编号获取贷款信息
    List<Loan> searchLoan(Long userId);
    // 获取所有的贷款信息不分页 做批量处理
    List<Loan> searchLoanInfo();
    // 添加用户贷款信息
    int insertLoan(Loan loan);
    // 修改用户贷款信息
    int updateLoan(Loan loan);
    // 删除用户贷款信息
    int deleteLoan(Long id);
}
