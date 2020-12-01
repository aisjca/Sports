package com.jc.ucenter.mapper;

import com.jc.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author jc
 * @since 2020-10-14
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer countRegisterDay(String day);
}
