package com.scl.edu.service;

import com.scl.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scl.edu.vo.SubjectNestedVo;
import com.scl.edu.vo.SubjectVO2;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author cl
 * @since 2020-01-17
 */
public interface SubjectService extends IService<Subject> {
    List<String> batchImport(MultipartFile file) throws Exception;

    List<SubjectNestedVo> nestedList();

    List<SubjectVO2> nestedList2();
}
