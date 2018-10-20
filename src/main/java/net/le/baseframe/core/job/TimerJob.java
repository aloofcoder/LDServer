package net.le.baseframe.core.job;


import lombok.extern.slf4j.Slf4j;
import net.le.baseframe.core.service.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TimerJob {

    @Autowired
    private RepaymentService repaymentService;

    /**
     * 每月一号0分0秒执行下发订单任务
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void lowerForm () {
        log.debug("将要进行用户还款订单下发");
        repaymentService.bathLowerRepaymnetForm();
    }
}
