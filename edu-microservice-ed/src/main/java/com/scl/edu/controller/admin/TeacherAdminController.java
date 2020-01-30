package com.scl.edu.controller.admin;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scl.common.constants.ResultCodeEnum;
import com.scl.common.exception.SclException;
import com.scl.common.vo.ResultVO;
import com.scl.edu.entity.Teacher;
import com.scl.edu.query.TeacherQuery;
import com.scl.edu.service.TeacherService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/17
 * Description:
 */
@RestController
@RequestMapping("/admin/edu/teacher")
@CrossOrigin
@ApiModel(description = "讲师管理")
public class TeacherAdminController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "查询所有讲师")
    @GetMapping
    public ResultVO list() {
        List<Teacher> teacherList = teacherService.list(null);
        return ResultVO.ok().message("查询讲师成功").data("items",teacherList);
    }


    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public ResultVO pageList(@ApiParam(name = "page", value = "当前页码", required = true)
                             @PathVariable("page") Long page,
                             @ApiParam(name = "limit", value = "每页记录数", required = true)
                             @PathVariable("limit") Long limit,
                             @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                                         TeacherQuery teacherQuery) {
        if (page<=0||limit<=0)
            throw new SclException(ResultCodeEnum.PARAM_ERROR);

        Page<Teacher> pageParam = new Page<>(page, limit);

//        teacherService.page(pageParam,null);
        teacherService.pageQuery(pageParam,teacherQuery);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        System.out.println(total);
        return ResultVO.ok().data("rows", records).data("total", total);


    }


    @ApiOperation("根据id查询讲师")
    @GetMapping("/{id}")
    public ResultVO queryTeacherById(
            @ApiParam(name = "id", value = "讲师id", required = true)
            @PathVariable("id") String id) {
        Teacher teacher = teacherService.getById(id);
        return ResultVO.ok().data("item", teacher);
    }

    @PutMapping("{id}")
    @ApiOperation("根据讲师id修改数据")
    public ResultVO updateTeacherById(
            @ApiParam(name = "id",value = "讲师id")
            @PathVariable("id")
            String id,
            @ApiParam(name = "teacher",value = "讲师信息")
            @RequestBody
            Teacher teacher) {
        teacher.setId(id);
        teacherService.updateById(teacher);


        return ResultVO.ok();
    }
    @ApiOperation("根据讲师id删除")
    @DeleteMapping("{id}")
    public ResultVO deleteTeacherById(
            @ApiParam(name = "id",value = "讲师id")
            @PathVariable("id")
            @Validated
            String id){
        boolean rest = teacherService.removeById(id);

        return ResultVO.ok().data("data",rest);
    }

    @ApiOperation("保存")
    @PostMapping
    public ResultVO save(@ApiParam(name = "teacher",value = "讲师对象",required = false)
                         @RequestBody
                         Teacher teacher){
        teacherService.save(teacher);
        return ResultVO.ok();
    }


}
