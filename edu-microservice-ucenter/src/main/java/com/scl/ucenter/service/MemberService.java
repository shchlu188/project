package com.scl.ucenter.service;

import com.scl.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author cl
 * @since 2020-01-28
 */
public interface MemberService extends IService<Member> {
    Integer countRegistryByDay(String day);
}
