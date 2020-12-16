package com.jc.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.order.entity.Order;
import com.jc.order.entity.PayLog;
import com.jc.order.service.OrderService;
import com.jc.order.service.PayLogService;
import com.jc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author jc
 * @since 2020-11-16
 */
@RestController
@RequestMapping("/eduorder/payLog")
@CrossOrigin
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    @Autowired
    private OrderService orderService;

    //生成微信支付二维码接口
    //参数是订单号
    @GetMapping("createNative/{orderNo}")
    public Result createNative(@PathVariable String orderNo) {
        //返回二维码地址，还有其他信息。
        Map map = payLogService.createNative(orderNo);
        return Result.ok().data(map);
    }

    //查询订单支付状态
    //参数：订单号，根据订单号查询 支付状态
    @GetMapping("queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo) {
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("*****查询订单状态map集合:" + map);
        if (map == null) {
            return Result.error().message("支付出错了");
        }
        //如果返回map里面不为空，通过map获取订单状态
        if (map.get("trade_state").equals("SUCCESS")) {
            payLogService.updateOrdersStatus(map);
            orderService.updateOrderStatus(orderNo);
            return Result.ok().message("支付成功");
        }
        return Result.ok().code(25000).message("支付中");
    }
}

