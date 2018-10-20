package net.le.baseframe.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.le.baseframe.core.entity.Repayment;
import net.le.baseframe.core.service.RepaymentService;
import net.le.baseframe.exception.AppControllerException;
import net.le.baseframe.util.CheckParamUtils;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;
import net.le.baseframe.web.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repayments")
@Api("用户还款操作接口类")
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;

    @ApiOperation("分页获取还款记录信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name="pageSize", value = "每页数据数", dataType = "int", required = true),
            @ApiImplicitParam(name="condition", value = "查询条件", dataType = "string")
    })
    @GetMapping
    public ResultBean getRepayments(PageQuest pageQuest) {
        if (pageQuest.getPageNum() < 0) {
            throw new AppControllerException("pageNum必须大于0！");
        }
        if (pageQuest.getPageSize() < 0) {
            throw new AppControllerException("pageSize必须大于0！");
        }
        PageBean pageBean = repaymentService.getRepayments(pageQuest);
        return new ResultBean(pageBean);
    }

    @ApiOperation("通过用户id获取贷款信息")
    @ApiImplicitParam(name="userId", value = "用户Id", paramType = "path", dataType = "long", required = true)
    @GetMapping("/{userId}")
    public ResultBean getRepayment (@PathVariable("userId") Long userId) {
        CheckParamUtils.isNull(userId, "用户Id不能为空");
        Repayment repayment = repaymentService.getRepayment(userId);
        return new ResultBean(repayment);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("添加用户还款记录信息")
    @ApiImplicitParam(name = "repayment", value = "还款记录json信息", paramType = "body", required = true)
    public ResultBean addRepayment(@RequestBody Repayment repayment) {
        CheckParamUtils.isNull(repayment, "还款单信息不能为空");
        repaymentService.addRepayment(repayment);
        return new ResultBean();
    }

    @PutMapping
    @ApiOperation("修改用户还款记录")
    @ApiImplicitParam(name = "repayment", value = "还款记录json信息", paramType = "body", required = true)
    public ResultBean editRepayment (@RequestBody Repayment repayment) {
        CheckParamUtils.isNull(repayment, "还款单信息不能为空");
        repaymentService.renoavteRepayment(repayment);
        return new ResultBean();
    }
    @DeleteMapping("/{id}")
    @ApiOperation("删除用户还款记录")
    @ApiImplicitParam(name = "id", value = "还款记录id", paramType = "path", dataType = "long", required = true)
    public ResultBean resultBean (@PathVariable("id") Long id) {
        CheckParamUtils.isNull(id, "还款信息id不能为空");
        repaymentService.removeRepayment(id);
        return new ResultBean();
    }
}
