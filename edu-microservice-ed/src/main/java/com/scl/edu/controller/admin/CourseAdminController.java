package com.scl.edu.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scl.common.vo.ResultVO;
import com.scl.edu.entity.Course;
import com.scl.edu.form.CourseInfoForm;
import com.scl.edu.query.CourseQuery;
import com.scl.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/29
 * Description:
 */
@Api(description = "课程管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/edu/course")
public class CourseAdminController {
    @Autowired
    private CourseService courseService;

    @ApiOperation("添加课程")
    @PostMapping("save-course-info")
    public ResultVO saveCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody
                    CourseInfoForm courseInfoForm
    ) {
        String courseId = courseService.saveCourseInfo(courseInfoForm);
        return ResultVO.ok().data("courseId", courseId);
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("course-info/{id}")
    public ResultVO getCourseInfoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable("id") String id) {
        CourseInfoForm courseInfoFormById = courseService.getCourseInfoFormById(id);

        return ResultVO.ok().data("item", courseInfoFormById);
    }

    @ApiOperation(value = "更新课程")
    @PutMapping("update-course-info/{id}")
    public ResultVO updateCourseInfoById(
            @ApiParam(name = "id", value = "课程id", required = true)
            @PathVariable("id") String id,
            @ApiParam(name = "courseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm
    ) {
        courseService.updateCourseInfoById(courseInfoForm);

        return ResultVO.ok();
    }

    @ApiOperation(value = "分页课程列表")
    @GetMapping("{page}/{limit}")
    public ResultVO pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    CourseQuery courseQuery
    ) {
        Page<Course> pageParam = new Page<>(page, limit);

        courseService.pageQuery(pageParam, courseQuery);

        List<Course> courseList = pageParam.getRecords();

        long total = pageParam.getTotal();

        return ResultVO.ok().data("total", total).data("rows", courseList);
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{id}")
    public ResultVO removeCourseInfoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id
    ) {
        courseService.removeCourseById(id);
        return ResultVO.ok();
    }

}
