package com.scl.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scl.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scl.edu.query.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author cl
 * @since 2020-01-17
 */
public interface TeacherService extends IService<Teacher> {
    void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery);
}
