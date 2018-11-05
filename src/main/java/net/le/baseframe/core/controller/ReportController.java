package net.le.baseframe.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.le.baseframe.core.service.ReportService;
import net.le.baseframe.web.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/reports")
@Api("借款平台报表统计接口类")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @ApiOperation("获取报表统计明细")
    @GetMapping
    public ResultBean getLoans() {
        Map<String, Object> map = reportService.getAllReport();
        return new ResultBean(map);
    }


}
