package com.jc.statistics.client;

import com.jc.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/12/01 00:38
 */
@FeignClient("service-ucenter")
@Component
public interface UcenterClient {

    //查询某一天注册人数
    @GetMapping("/educenter/member/countRegister/{day}")
    public Result countRegister(@PathVariable("day") String day);
}
