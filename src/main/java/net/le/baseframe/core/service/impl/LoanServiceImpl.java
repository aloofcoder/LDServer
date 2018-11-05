package net.le.baseframe.core.service.impl;

import net.le.baseframe.core.dao.LoanDao;
import net.le.baseframe.core.entity.Loan;
import net.le.baseframe.core.service.LoanService;
import net.le.baseframe.exception.AppControllerException;
import net.le.baseframe.util.CheckParamUtils;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class LoanServiceImpl implements LoanService {


    @Resource
    private LoanDao loanDao;

    @Override
    public PageBean getLoans(PageQuest pageQuest) {
        int pageNum = pageQuest.getPageNum();
        int pageSize = pageQuest.getPageSize();
        if (pageNum < 0) {
            throw new AppControllerException("pageNum必须大于0！");
        }
        if (pageSize < 0) {
            throw new AppControllerException("pageSize必须大于0！");
        }
        String condition = pageQuest.getCondition();
        // 获取分页开始索引
        int startIndex = pageQuest.getStartIndex(pageNum, pageSize);
        List<Map<String, Object>> list = loanDao.searchLoans(startIndex, pageSize, condition);
        System.out.println("loanlist ======> " + list);
        int count = loanDao.getRowCount(condition);
        int totalPage = PageBean.getTotalPage(pageSize, count);
        PageBean pageBean = new PageBean(pageNum, pageSize, totalPage, count, list);
        return pageBean;
    }

    @Override
    public List<Loan> getLoan(Long userId) {
        CheckParamUtils.isNull(userId, "用户信息Id不能为空！");
        List<Loan> list = loanDao.searchLoan(userId);
        return list;
    }

    @Override
    public int addLoan(Loan loan) {
        CheckParamUtils.isNull(loan, "贷款信息不能为空！");
        int count = loanDao.insertLoan(loan);
        CheckParamUtils.countErr(count, "添加贷款信息失败！");
        return count;
    }

    @Override
    public int renovateLoan(Loan loan) {
        CheckParamUtils.isNull(loan, "贷款信息不能为空！");
        int count = loanDao.updateLoan(loan);
        CheckParamUtils.countErr(count, "修改贷款信息失败！");
        return count;
    }

    @Override
    public int removeLoan(Long id) {
        CheckParamUtils.isNull(id, "贷款信息Id不能为空！");
        int count = loanDao.deleteLoan(id);
        CheckParamUtils.countErr(count, "删除贷款信息失败！");
        return count;
    }
}
