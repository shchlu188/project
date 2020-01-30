package com.scl.ucenter.controller.admin;


import com.scl.common.vo.ResultVO;
import com.scl.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author cl
 * @since 2020-01-28
 */
@Api("会员中心")
@RestController
@CrossOrigin
@RequestMapping("/admin/ucenter/member")
public class MemberAdminController {
    @Autowired
    private MemberService memberService;


    @ApiOperation("今日注册会员人数")
    @GetMapping("count-register/{day}")
    public ResultVO registerCount(
            @ApiParam(name = "day", value = "统计日期")
            @PathVariable("day") String day) {
        Integer countRegistryDay = memberService.countRegistryByDay(day);

        return ResultVO.ok().data("countRegister", countRegistryDay);
    }

}

