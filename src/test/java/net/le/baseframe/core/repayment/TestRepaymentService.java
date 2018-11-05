package net.le.baseframe.core.repayment;

import net.le.baseframe.core.entity.Repayment;
import net.le.baseframe.core.service.RepaymentService;
import net.le.baseframe.util.DateUtils;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRepaymentService {

    @Autowired
    private RepaymentService repaymentService;

    @Test
    public void testAddRepayment () {
        Repayment repayment = new Repayment();
        repayment.setUserId(Long.parseLong("4"));
        repayment.setLoanId(Long.parseLong("1"));
        repayment.setRepaymentMoney(100);
        repayment.setLowerTime(DateUtils.getNowMonthPre().getTime()/1000);
        repayment.setRepaymentStatus(0);
        repayment.setCreateUser("韩乐");
        repayment.setEditUser("韩乐");
        int count = repaymentService.addRepayment(repayment);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void testGetRepayments () {
        PageQuest pageQuest = new PageQuest(1, 10, "");
        PageBean pageBean = repaymentService.getRepayments(pageQuest);
        Assert.assertTrue(pageBean.getList().size() == 1);
    }

    @Test
    public void testRenovateRepayment () {
        List<Repayment> list = repaymentService.getRepayment(Long.parseLong("4"));
        Assert.assertTrue(list.size() > 0);
        Repayment repayment = list.get(0);
        repayment.setRepaymentMoney(100000);
        repayment.setEditUser("韩乐");
        int count = repaymentService.renoavteRepayment(repayment);
        Assert.assertTrue(count == 1);
    }


    @Test
    public void testBatchLowerForm () {
        repaymentService.bathLowerRepaymnetForm();
        Assert.assertTrue(1 == 1);
    }
}
