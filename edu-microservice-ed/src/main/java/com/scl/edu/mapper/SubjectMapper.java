package com.scl.edu.mapper;

import com.scl.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scl.edu.vo.SubjectVO2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2020-01-17
 */
@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {
    List<SubjectVO2> selectNestedListByParentId(String parentId);
}
