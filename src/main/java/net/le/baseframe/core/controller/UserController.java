package net.le.baseframe.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.le.baseframe.core.entity.User;
import net.le.baseframe.core.service.UserService;
import net.le.baseframe.exception.AppControllerException;
import net.le.baseframe.exception.AppServiceException;
import net.le.baseframe.util.CheckParamUtils;
import net.le.baseframe.web.PageBean;
import net.le.baseframe.web.PageQuest;
import net.le.baseframe.web.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api("客户信息操作类")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "分页获取用户档案信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name="pageSize", value = "每页数据数", dataType = "int", required = true),
            @ApiImplicitParam(name="condition", value = "查询条件", dataType = "String")
    })
    @GetMapping
    public ResultBean getUsers (PageQuest pageQuest) {
        if (pageQuest.getPageNum() < 0) {
            throw new AppControllerException("pageNum必须大于0！");
        }
        if (pageQuest.getPageSize() < 0) {
            throw new AppControllerException("pageSize必须大于0！");
        }
        PageBean pageBean = userService.getUsers(pageQuest);
        return new ResultBean(pageBean);
    }

    @ApiOperation(value = "通过用户编号获取用户信息")
    @ApiImplicitParam(name="userNumber", value = "用户编号",paramType = "path", dataType = "String", required = true)
    @GetMapping("/{userNumber}")
    public ResultBean getUser(@PathVariable("userNumber") String userNumber) {
        CheckParamUtils.isNull(userNumber, "用户编号不能为空！");
        User user = userService.getUser(userNumber);
        return new ResultBean(user);
    }


    @ApiOperation(value = "添加用户档案信息")
    @ApiImplicitParam(name="user", value = "用户信息", paramType = "body", required = true)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultBean addUser(@RequestBody User user) {
        CheckParamUtils.isNull(user, "用户信息不能为空！");
        userService.addUser(user);
        return new ResultBean();
    }
    @ApiOperation(value = "修改用户档案信息")
    @ApiImplicitParam(name="user", value = "用户信息", paramType = "body", required = true)
    @PutMapping
    public ResultBean renovateUser(@RequestBody User user) {
        CheckParamUtils.isNull(user, "用户信息不能为空！");
        userService.renovateUser(user);
        return new ResultBean();
    }

    @ApiOperation(value = "通过用户编号删除用户档案信息")
    @ApiImplicitParam(name="id", value = "用户信息id", paramType = "path", dataType = "String", required = true)
    @DeleteMapping("/{id}")
    public ResultBean removeUser(@PathVariable("id") Long id) {
        CheckParamUtils.isNull(id, "用户信息id不能为空！");
        userService.removeUser(id);
        return new ResultBean();
    }
}
