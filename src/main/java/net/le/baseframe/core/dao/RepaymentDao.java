package net.le.baseframe.core.dao;

import net.le.baseframe.core.entity.Repayment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RepaymentDao {

    // 分页获取用户贷款信息
    List<Map<String, Object>> searchRepayments(@Param("startIndex") int startIndex,
                                               @Param("pageSize") int pageSize,
                                               @Param("condition") String condition);
    // 获取用户贷款信息总条数
    int getRowCount(@Param("condition") String condition);
    // 通过用户id获取用户还款单
    List<Repayment> searchRepayment(Long userId);
    // 通过下发时间获取用户还款单
    List<Repayment> searchRepaymentByLowerTime(@Param("lowerTime") Long lowerTime,
                                               @Param("loanId") Long loanId,
                                               @Param("repaymentStatus") Integer repaymentStatus);
    // 添加还款单信息
    int insertRepayment(Repayment repayment);
    // 修改还款信息
    int updateRepayment(Repayment repayment);
    // 删除还款信息
    int deleteRepayment(Long id);
}
