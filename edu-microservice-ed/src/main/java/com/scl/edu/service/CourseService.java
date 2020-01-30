package com.scl.edu.service;

import com.scl.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scl.edu.form.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cl
 * @since 2020-01-17
 */
public interface CourseService extends IService<Course> {
    /**
     * 保存课程和课程详情信息
     * @param courseInfoForm
     * @return 新生成的课程id
     */
    String saveCourseInfo(CourseInfoForm courseInfoForm);
}
