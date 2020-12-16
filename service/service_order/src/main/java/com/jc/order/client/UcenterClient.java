package com.jc.order.client;

import com.jc.utils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program sport_parent
 * @description:返回用户信息
 * @author: JC
 * @create: 2020/11/16 21:02
 */
@FeignClient(name = "service-ucenter")
@Component
public interface UcenterClient {

    @GetMapping("/educenter/member/getMemberInfoOrder/{memberId}")
    public UcenterMemberOrder getMemberInfoOrder(@PathVariable("memberId") String memberId);
}
