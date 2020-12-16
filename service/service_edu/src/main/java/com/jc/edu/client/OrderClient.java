package com.jc.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program sport_parent
 * @description:
 * @author: JC
 * @create: 2020/11/19 00:21
 */
@FeignClient(name = "service-order")
@Component
public interface OrderClient {

    @GetMapping("/eduorder/order/getOrderState/{courseId}/{memberId}")
    public boolean getOrderState(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);

}
