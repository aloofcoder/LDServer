package net.le.baseframe.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.le.baseframe.core.entity.Manager;
import net.le.baseframe.core.service.ManagerService;
import net.le.baseframe.exception.AppControllerException;
import net.le.baseframe.util.CheckParamUtils;
import net.le.baseframe.util.DateUtils;
import net.le.baseframe.util.SecurityUtils;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;
import net.le.baseframe.web.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Api("管理员接口操作类")
@RequestMapping("/managers")
@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @ApiOperation("分页获取所有管理员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name="pageSize", value = "每页数据数", dataType = "int", required = true),
            @ApiImplicitParam(name="condition", value = "查询条件", dataType = "string")
    })
    @GetMapping
    public ResultBean searchManagers (PageQuest pageQuest) {
        if (pageQuest.getPageNum() < 0) {
            throw new AppControllerException("pageNum必须大于0！");
        }
        if (pageQuest.getPageSize() < 0) {
            throw new AppControllerException("pageSize必须大于0！");
        }
        PageBean pageBean = managerService.getManagers(pageQuest);
        return new ResultBean(pageBean);
    }

    @ApiOperation("增加一条管理员")
    @ApiImplicitParam(name = "manager", value = "管理员对象", paramType = "body", required = true)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultBean addManager(@RequestBody Manager manager) {
        CheckParamUtils.isNull(manager, "管理员信息不能为空！");
        managerService.addManager(manager);
        return new ResultBean();
    }

    @ApiOperation("修改管理员信息")
    @ApiImplicitParam(name = "manager", value = "管理员对象", paramType = "body", required = true)
    @PutMapping
    public ResultBean renovateManager (@RequestBody Manager manager) {
        CheckParamUtils.isNull(manager, "管理员信息不能为空！");
        managerService.renovateManager(manager);
        return new ResultBean();
    }

    @ApiOperation("通过id删除一条管理员信息")
    @ApiImplicitParam(name ="id", value = "管理员信息id", paramType = "path", dataType = "long", required = true)
    @DeleteMapping("/{id}")
    public ResultBean removeManager (@PathVariable("id") Long id) {
        CheckParamUtils.isNull(id, "id不能为空！");
        managerService.removeManager(id);
        return new ResultBean();
    }

    @ApiOperation("管理员登录")
    @ApiImplicitParam(name = "manager", value = "管理员对象", paramType = "body", required = true)
    @PostMapping("/login")
    public ResultBean managerLogin (@RequestBody Manager manager, HttpServletRequest request, HttpServletResponse response) {
        // 通过管理员编号获取管理员信息
        Long managerNumber = manager.getManagerNumber();
        String managerPwd = manager.getManagerPwd();
        // 验证参数
        CheckParamUtils.isNull(managerNumber, "登录账号不能为空！");
        CheckParamUtils.isNull(managerPwd, "登录密码不能为空！");
        long nowMillis = DateUtils.getNowTimeMillis();
        Manager managerInfo = managerService.managerLogin(managerNumber, managerPwd, nowMillis);
        // 将管理员账号密码和登陆时间MD5加密生成token令牌
//        String str = managerNumber + "" + managerPwd + DateUtils.getNowTimeMillis();
//        String token = SecurityUtils.getMd5(str);
//        // jiangtoken保存
//        System.out.println(token);
//        HttpSession session = request.getSession();
//        session.setAttribute(managerNumber.toString(), token);
//        response.setHeader("set-token", token);
        return new ResultBean(managerInfo);
    }
}
