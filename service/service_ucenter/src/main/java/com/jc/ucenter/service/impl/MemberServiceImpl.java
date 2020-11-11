package com.jc.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.base.exceptionhandler.SportException;
import com.jc.ucenter.entity.Member;
import com.jc.ucenter.entity.vo.RegisterVo;
import com.jc.ucenter.mapper.MemberMapper;
import com.jc.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.utils.JwtUtils;
import com.jc.utils.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author jc
 * @since 2020-10-14
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //登陆的方法
    @Override
    public String login(Member member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new SportException(20001, "登陆失败");
        }
        //判断手机号是否为空
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        //根据手机号从数据库查询出来的用户
        Member mobileUser = baseMapper.selectOne(wrapper);
        if (mobileUser==null) {//没有这个手机号
            throw new SportException(20001, "登陆失败");
        }
        //判断密码
        //数据库的密码是加密的，所以把用户输入的密码进行加密，和数据库比对
        //MD5 加密

        if (!MD5.encrypt(password).equals(mobileUser.getPassword())) {
            throw new SportException(20001, "登陆失败");
        }
        //判断用户是否被禁用
        if (mobileUser.getIsDisabled()) {
            throw new SportException(20001, "登陆失败");
        }
        //登陆成功,生成token字符串，使用jwt工具类
        String jwtToken = JwtUtils.getJwtToken(mobileUser.getId(), mobileUser.getNickname());
        return jwtToken;
    }

    //注册功能
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册数据
        String code = registerVo.getCode();//验证码
        String mobile = registerVo.getMobile();//手机号
        String nickname = registerVo.getNickname();//昵称
        String password = registerVo.getPassword();//密码

        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile)
                || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)) {
            throw new SportException(20001, "注册失败");
        }
        //获取缓存验证码 并验证
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)) {
            throw new SportException(20001, "注册失败");
        }

        //判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new SportException(20001, "注册失败");
        }
        //数据添加数据库中
        Member member = new Member();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));//密码需要加密的
        member.setIsDisabled(false);//用户不禁用
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(member);
    }

    @Override
    public Member getOpenIdMember(String openid) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        Member member = baseMapper.selectOne(wrapper);
        return member;
    }
}
