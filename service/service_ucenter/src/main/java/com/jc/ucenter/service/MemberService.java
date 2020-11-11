package com.jc.ucenter.service;

import com.jc.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author jc
 * @since 2020-10-14
 */
public interface MemberService extends IService<Member> {

    String login(Member member);

    void register(RegisterVo registerVo);

    Member getOpenIdMember(String openid);
}
