package com.scl.ucenter.service.impl;

import com.scl.ucenter.entity.Member;
import com.scl.ucenter.mapper.MemberMapper;
import com.scl.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-01-28
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Integer countRegistryByDay(String day) {
        return memberMapper.selectRegisterCount(day);
    }
}
