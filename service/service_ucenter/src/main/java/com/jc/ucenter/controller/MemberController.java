package com.jc.ucenter.controller;


import com.jc.ucenter.entity.Member;
import com.jc.ucenter.entity.vo.RegisterVo;
import com.jc.ucenter.service.MemberService;
import com.jc.utils.JwtUtils;
import com.jc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author jc
 * @since 2020-10-14
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService memberService;

    //登陆
    @PostMapping("login")
    public Result loginUser(@RequestBody Member member) {
        //member封装手机号和密码
        //调用登陆方法，返回token，用jwt生成
        String token = memberService.login(member);
        System.out.println(token);
        return Result.ok().data("token", token);
    }

    //注册
    @PostMapping("register")
    public Result registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return Result.ok();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        Member member = memberService.getById(memberId);
        return Result.ok().data("userInfo", member);
    }
}

