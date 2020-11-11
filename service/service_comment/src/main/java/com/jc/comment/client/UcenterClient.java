package com.jc.comment.client;

import com.jc.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/11/08 00:37
 */
@Component
@FeignClient(name = "service-ucenter")
public interface UcenterClient {
    //根据token获取用户信息
    @GetMapping("/educenter/member/getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request);
}
