package com.scl.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scl.common.constants.ResultCodeEnum;
import com.scl.common.exception.SclException;
import com.scl.edu.entity.Chapter;
import com.scl.edu.entity.Course;
import com.scl.edu.entity.CourseDescription;
import com.scl.edu.entity.Video;
import com.scl.edu.form.CourseInfoForm;
import com.scl.edu.mapper.ChapterMapper;
import com.scl.edu.mapper.CourseDescriptionMapper;
import com.scl.edu.mapper.CourseMapper;
import com.scl.edu.mapper.VideoMapper;
import com.scl.edu.query.CourseQuery;
import com.scl.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private ChapterMapper chapterMapper;

    @Transactional
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        Course course = new Course();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, course);
        baseMapper.insert(course);

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(courseInfoForm.getId());
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescriptionMapper.insert(courseDescription);
        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseInfoFormById(String id) {
        //从course表中取数据
        Course course = baseMapper.selectById(id);
        if (course == null)
            throw new SclException(ResultCodeEnum.DATA_NOT_EXIST);
        //从course_description表中取数据
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);
        if (courseDescription == null)
            throw new SclException(ResultCodeEnum.DATA_NOT_ALL);
        //创建courseInfoForm对象
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);
        BeanUtils.copyProperties(courseDescription, courseInfoForm);
        return courseInfoForm;
    }

    @Transactional
    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);
        baseMapper.updateById(course);

        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfoForm, courseDescription);
        courseDescriptionMapper.updateById(courseDescription);
    }

    @Override
    public void pageQuery(Page<Course> pageParam, CourseQuery courseQuery) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByAsc("gmt_create");

        if (courseQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }
        String subjectId = courseQuery.getSubjectId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String teacherId = courseQuery.getTeacherId();
        String title = courseQuery.getTitle();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.like("subject_id", subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.like("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            queryWrapper.like("teacher_id", teacherId);
        }
        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Transactional
    @Override
    public void removeCourseById(String id) {
        //根据id删除所有视频
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id);
        videoMapper.deleteById(id);

        //根据id删除所有章节
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",id);
        //删除课程详情
        courseDescriptionMapper.deleteById(id);
        //根据id删除课程
        baseMapper.deleteById(id);
    }
}
