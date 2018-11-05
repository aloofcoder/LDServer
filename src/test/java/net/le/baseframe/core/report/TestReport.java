package net.le.baseframe.core.report;

import com.sun.javafx.collections.MappingChange;
import lombok.extern.slf4j.Slf4j;
import net.le.baseframe.core.dao.ReportDao;
import net.le.baseframe.core.service.ReportService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestReport {

    @Resource
    private ReportService reportService;


    @Test
    public void testGetReport() {
        Map<String, Object> map = reportService.getAllReport();
        System.out.println(map);
        Assert.assertTrue(map.containsKey("userCount"));
    }
}

