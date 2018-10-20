package net.le.baseframe.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.le.baseframe.core.entity.Loan;
import net.le.baseframe.core.service.LoanService;
import net.le.baseframe.exception.AppControllerException;
import net.le.baseframe.util.CheckParamUtils;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;
import net.le.baseframe.web.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
@Api("客户贷款信息接口类")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @ApiOperation("分页获取用户贷款信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name="pageSize", value = "每页数据数", dataType = "int", required = true),
            @ApiImplicitParam(name="condition", value = "查询条件", dataType = "string")
    })
    @GetMapping
    public ResultBean getLoans(PageQuest pageQuest) {
        if (pageQuest.getPageNum() < 0) {
            throw new AppControllerException("pageNum必须大于0！");
        }
        if (pageQuest.getPageSize() < 0) {
            throw new AppControllerException("pageSize必须大于0！");
        }
        PageBean pageBean = loanService.getLoans(pageQuest);
        return new ResultBean(pageBean);
    }

    @ApiOperation("通过用户id获取贷款信息")
    @ApiImplicitParam(name = "userId", value = "用户Id", paramType = "path", dataType = "long", required = true)
    @GetMapping("/{userId}")
    public ResultBean getLoan(@PathVariable("userId") Long userId) {
        CheckParamUtils.isNull(userId, "用户Id不能为空!");
        Loan loan = loanService.getLoan(userId);
        return new ResultBean(loan);
    }


    @ApiOperation(value = "用户添加一笔贷款")
    @ApiImplicitParam(name="loan", value = "贷款信息", paramType = "body", required = true)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultBean addLoan (@RequestBody Loan loan) {
        CheckParamUtils.isNull(loan, "贷款记录不能为空!");
        loanService.addLoan(loan);
        return new ResultBean();
    }

    @ApiOperation("修改用户贷款信息")
    @ApiImplicitParam(name="loan", value = "用户贷款信息", paramType = "body", required = true)
    @PutMapping
    public ResultBean editLoan(@RequestBody Loan loan) {
        CheckParamUtils.isNull(loan, "贷款记录不能为空!");
        loanService.renovateLoan(loan);
        return new ResultBean();
    }

    @ApiOperation("删除贷款信息")
    @ApiImplicitParam(name="id", value = "贷款信息id", paramType = "long", required = true)
    @DeleteMapping("/{id}")
    public ResultBean removeLoan(@PathVariable("id") Long id) {
        CheckParamUtils.isNull(id, "贷款记录Id不能为空!");
        loanService.removeLoan(id);
        return new ResultBean();
    }
}
