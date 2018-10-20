package net.le.baseframe.core.service;

import net.le.baseframe.core.entity.Repayment;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;

public interface RepaymentService {

    PageBean getRepayments(PageQuest pageQuest);
    Repayment getRepayment(Long userId);
    int addRepayment(Repayment repayment);
    int renoavteRepayment(Repayment repayment);
    int removeRepayment(Long id);
    // 批量生成还款单信息
    void bathLowerRepaymnetForm();
}
