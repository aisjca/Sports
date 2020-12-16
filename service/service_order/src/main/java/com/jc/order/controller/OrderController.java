package com.jc.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.order.entity.Order;
import com.jc.order.service.OrderService;
import com.jc.utils.JwtUtils;
import com.jc.utils.Result;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author jc
 * @since 2020-11-16
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("createOrder/{courseId}")
    public Result createOrder(@PathVariable String courseId, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //创建订单，返回订单号
        String orderNo = orderService.createOrders(courseId, memberId);
        return Result.ok().data("orderNo", orderNo);
    }

    //2 根据订单号查询订单信息
    @GetMapping("getOrderInfo/{orderNo}")
    public Result getOrderInfo(@PathVariable String orderNo) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        Order order = orderService.getOne(wrapper);
        return Result.ok().data("item", order);
    }

    //3查询订单状态
    @GetMapping("getOrderState/{courseId}/{memberId}")
    public boolean getOrderState(@PathVariable String courseId, @PathVariable String memberId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.eq("member_id", memberId);
        wrapper.eq("status", 1);//支付状态 1代表已经支付
        int count = orderService.count(wrapper);
        return count == 0 ? false : true;
    }
}

