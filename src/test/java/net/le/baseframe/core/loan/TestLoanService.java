package net.le.baseframe.core.loan;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.le.baseframe.core.entity.Loan;
import net.le.baseframe.core.service.LoanService;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestLoanService {

    @Autowired
    private LoanService loanService;


    @Test
    public void tetAddLoan() {
        Loan loan = new Loan();
        loan.setUserId(Long.parseLong("4"));
        loan.setLoanMoney(500000);
        loan.setLoanInterest(200);
        loan.setLoanPeriods(10);
        loan.setLoanInterestPeriod(0);
        loan.setLoanType(0);
        loan.setLoanPledge("信贷");
        loan.setLoanStatus(0);
        loan.setCreateUser("韩乐");
        loan.setEditUser("韩乐");
        int count = loanService.addLoan(loan);
        log.debug("保存的用户贷款信息条数", count);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void testGetLoans () {
        PageQuest pageQuest = new PageQuest(1, 10, null);
        PageBean pageBean = loanService.getLoans(pageQuest);
        Assert.assertTrue(pageBean.getList().size() > 0);
    }

    @Test
    public void testGetLoan () {
        Loan loan = loanService.getLoan(Long.parseLong("4"));
        Assert.assertTrue(loan != null);
    }


    @Test
    public void testRenovateLoan () {
        Loan loan = loanService.getLoan(Long.parseLong("4"));
        loan.setLoanInterest(8000);
        int count = loanService.renovateLoan(loan);
        Assert.assertTrue(count == 1);
    }
}
