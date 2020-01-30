package com.scl.ucenter.mapper;

import com.scl.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2020-01-28
 */
@Repository
public interface MemberMapper extends BaseMapper<Member> {
    Integer selectRegisterCount(String day);

}
