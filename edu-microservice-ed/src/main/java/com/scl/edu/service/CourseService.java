package com.scl.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scl.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scl.edu.form.CourseInfoForm;
import com.scl.edu.query.CourseQuery;

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

    /**
     *  根据课程id获取课程信息
     * @param id
     * @return 课程信息表单
     */
    CourseInfoForm getCourseInfoFormById(String id);

    /**
     * 根据课程id更新课程信息
     * @param courseInfoForm
     */
    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    /**
     * 分页查询
     * @param pageParam
     * @param courseQuery
     */
    void pageQuery(Page<Course> pageParam, CourseQuery courseQuery);

    /**
     * 根据课程id删除课程信息
     * @param id
     */
    void removeCourseById(String id);
}
