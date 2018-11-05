package net.le.baseframe.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.le.baseframe.core.dao.LoanDao;
import net.le.baseframe.core.dao.RepaymentDao;
import net.le.baseframe.core.dao.UserDao;
import net.le.baseframe.core.entity.Loan;
import net.le.baseframe.core.entity.Repayment;
import net.le.baseframe.core.entity.User;
import net.le.baseframe.core.service.RepaymentService;
import net.le.baseframe.exception.AppControllerException;
import net.le.baseframe.exception.AppServiceException;
import net.le.baseframe.util.CheckParamUtils;
import net.le.baseframe.util.DateUtils;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RepaymentServiceImpl implements RepaymentService {

    @Resource
    private RepaymentDao repaymentDao;

    @Resource
    private LoanDao loanDao;

    @Resource
    private UserDao userDao;


    @Override
    public PageBean getRepayments(PageQuest pageQuest) {
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
        List<Map<String, Object>> list = repaymentDao.searchRepayments(startIndex, pageSize, condition);
        int count = repaymentDao.getRowCount(condition);
        int totalPage = PageBean.getTotalPage(pageSize, count);
        PageBean pageBean = new PageBean(pageNum, pageSize, totalPage, count, list);
        return pageBean;
    }

    @Override
    public List<Repayment> getRepayment(Long userId) {
        CheckParamUtils.isNull(userId, "用户信息Id不能为空！");
        List<Repayment> list = repaymentDao.searchRepayment(userId);
        return list;
    }

    @Override
    public int addRepayment(Repayment repayment) {
        CheckParamUtils.isNull(repayment, "还款单信息不能为空！");
        int count = repaymentDao.insertRepayment(repayment);
        CheckParamUtils.countErr(count, "添加用户还款单失败");
        return count;
    }

    @Override
    public int renoavteRepayment(Repayment repayment) {
        CheckParamUtils.isNull(repayment, "还款单信息不能为空！");
        int count = repaymentDao.updateRepayment(repayment);
        CheckParamUtils.countErr(count, "修改用户还款单失败");
        return count;
    }

    @Override
    public int removeRepayment(Long id) {
        CheckParamUtils.isNull(id, "还款单信息id不能为空！");
        int count = repaymentDao.deleteRepayment(id);
        CheckParamUtils.countErr(count, "删除用户还款单失败");
        return count;
    }

    @Override
    public void bathLowerRepaymnetForm() {
        // 获取所有还款中的用户档案信息
        List<Loan> loanInfo = loanDao.searchLoanInfo();
        if (loanInfo.size() <= 0) {
            return;
        }
        for (Loan loan : loanInfo) {
            lowerOneRepaymentForm(loan);
        }
    }

    @Transactional
    public void lowerOneRepaymentForm (Loan loan) {
        // 获取当前月1号
        Date date = DateUtils.getNowMonthPre();
        long lowerTime = date.getTime();
        log.debug("还款中的用户贷款信息: " + loan);
        long userId = loan.getUserId();
        log.debug("userId: " + userId);
        // 获取到的用户贷款档案信息
        User user = userDao.searchUserById(userId);
        // 查看用户本期是否有还款单
        List<Repayment> list = repaymentDao.searchRepaymentByLowerTime(lowerTime / 1000, loan.getId(), 0);
        // 如果有未还款单，删删除
        if (list.size() > 0) {
            for (Repayment repayment: list) {
                log.debug("未还款账单： " + list);
                repaymentDao.deleteRepayment(repayment.getId());
            }
        }
        // 查看是否有已还款记录
        List<Repayment> repaymentList = repaymentDao.searchRepaymentByLowerTime(lowerTime, loan.getId(), 1);
        log.debug("已还款信息： " + repaymentList);
        if (repaymentList.size() > 0) {
            return;
        }
        // 用户需要还款金额
        int repayMoney = 0;
        int repayInterest = 0;
        // 用户的贷款总金额
        Integer loanMoney = loan.getLoanMoney();
        // 用户的利息总金额
        Integer loanInterest = loan.getLoanInterest();
        // 用户的贷款总期数
        Integer loanPeriods = loan.getLoanPeriods();
        // 用户的先息后本利息总期数
        Integer loanInterestPeriods = loan.getLoanInterestPeriod();
        // 用户的贷款类型（0.等额本息、1.先息后本）
        Integer loanType = loan.getLoanType();
        // 如果用户贷款为等额本息
        if (loanType == 0) {
            log.debug("用户贷款类型为等额本息");
            // 用户所有要还款的金额
            Integer totalMoney = loanMoney + loanInterest;
            // 用户每一期要还款金额
            repayMoney = totalMoney / loanPeriods;
        } else if (loanType == 1) {
            // 如果用户贷款为先息后本
            log.debug("用户贷款类型为先息后本");
            // 用户贷款时间 以用户的贷款时间和利息期数判断本期需要还款金额
            long loanTime = loan.getCreateTime();
            // 获取从贷款到当前的度过月份
            int totalMonth = DateUtils.spendMonth(Long.parseLong(loanTime +"000"));
//                由于还款开始时间为贷款时间的下一个月
//            if (totalMonth == 0) {
//                return;
//            }
            if (loanInterestPeriods != null && loanInterestPeriods > 0) {
                repayInterest = loanInterestPeriods;
            }
            if (totalMonth <= repayInterest) {
                // 本期该还金额为利息
                if (repayInterest > 0) {
                    repayMoney = loanInterest / repayInterest;
                } else {
//                    throw new AppServiceException("该条贷款信息异常！");
                    log.debug("该条贷款信息异常！");
                    return;
                }
            } else {
                repayMoney = loanMoney / (loanPeriods - repayInterest);
            }
        }
        Repayment repayment = new Repayment();
        repayment.setUserId(userId);
        repayment.setLoanId(loan.getId());
        repayment.setRepaymentMoney(repayMoney);
        repayment.setLowerTime(lowerTime / 1000);
        repayment.setRepaymentStatus(0);
        repayment.setCreateUser("管理员");
        repayment.setEditUser("管理员");
        log.debug("要保存的贷款账单信息： " + repayment);
        int count = repaymentDao.insertRepayment(repayment);
        CheckParamUtils.countErr(count, "生成用户还款单失败");
    }
}
