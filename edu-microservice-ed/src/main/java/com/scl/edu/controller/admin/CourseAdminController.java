package com.scl.edu.controller.admin;

import com.scl.common.vo.ResultVO;
import com.scl.edu.form.CourseInfoForm;
import com.scl.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return ResultVO.ok().data("courseId",courseId);
    }
}
