package net.le.baseframe.core.service.impl;

import net.le.baseframe.core.dao.ReportDao;
import net.le.baseframe.core.dao.UserDao;
import net.le.baseframe.core.service.ReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Resource
    private ReportDao reportDao;

    @Resource
    private UserDao userDao;


    @Override
    public Map<String, Object> getAllReport() {
        // 获取用户总数
        int userCount = userDao.getRowCount("1=1");
        List<Map<String, Object>> loan = reportDao.getLoanTypeMoney();
        // 获取近6月用户增加
        List<Map<String, Object>> monthCount = reportDao.getUserReport();
        // 获取未还款账单数
        List<Map<String, Object>> nopayList = reportDao.getNoPayReport();
        Map<String, Object> map = nopayList.get(0);
        String nopayAndallpay = (String)map.get("nopay");
        String[] payType = nopayAndallpay.split(",");
        Map<String, Integer> payMap = new HashMap<>();
        payMap.put("nopay", Integer.parseInt(payType[0]));
        payMap.put("allpay", Integer.parseInt(payType[1]));
        Map<String, Object> allReport = new HashMap<>();
        allReport.put("loanMoneyType", loan);
        allReport.put("userCount", userCount);
        allReport.put("monthCount", monthCount);
        allReport.put("payMap", payMap);
        return allReport;
    }
}
