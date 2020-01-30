package com.scl.edu.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scl.edu.entity.Teacher;
import com.scl.edu.query.TeacherQuery;
import com.scl.edu.service.TeacherService;
import io.swagger.annotations.Authorization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/24
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherAdminControllerTest {

    @Autowired
    private TeacherService teacherService;
    @Test
    public void list() {
    }

    @Test
    public void pageList() {
        Page<Teacher> page = new Page<>();
        TeacherQuery teacherQuery = new TeacherQuery();
        teacherService.pageQuery(page,teacherQuery);
        long total = page.getTotal();

        System.out.println(total+"------------------------");
    }

    @Test
    public void queryTeacherById() {
    }

    @Test
    public void updateTeacherById() {
    }

    @Test
    public void deleteTeacherById() {
    }

    @Test
    public void save() {
    }
}