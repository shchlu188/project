package com.scl.edumicroservicesysuser.controller;

import com.scl.common.vo.ResultVO;
import com.scl.edumicroservicesysuser.entity.Sysuser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/24
 * Description:
 */
@Api(description="系统用户管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/sysuser")
public class SysUserController {

    @PostMapping("login")
    @ApiOperation(value = "用户登录")
    public ResultVO login(
            @ApiParam(name = "sysuser", value = "系统用户对象", required = true)
            @RequestBody Sysuser sysuser){

        return ResultVO.ok().data("token", "admin");
    }

    @GetMapping("info")
    @ApiOperation(value = "获取用户信息")
    public ResultVO info(
            @ApiParam(name = "token", value = "令牌", required = true)
            @RequestParam String token){
        return ResultVO.ok()
                .data("roles", "admin")
                .data("name", "admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
    @PostMapping("logout")
    @ApiOperation(value = "用户登出")
    public ResultVO logout(){
        return ResultVO.ok();
    }
}