package com.scl.edu.service.impl;

import com.netflix.discovery.converters.Auto;
import com.scl.edu.entity.Course;
import com.scl.edu.entity.CourseDescription;
import com.scl.edu.form.CourseInfoForm;
import com.scl.edu.mapper.CourseDescriptionMapper;
import com.scl.edu.mapper.CourseMapper;
import com.scl.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-01-17
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;
    @Transactional
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        Course course = new Course();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm,course);
        baseMapper.insert(course);

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(courseInfoForm.getId());
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescriptionMapper.insert(courseDescription);
        return course.getId();
    }
}
