package com.jc.order.service;

import com.jc.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author jc
 * @since 2020-11-16
 */
public interface OrderService extends IService<Order> {

    String createOrders(String courseId, String memberId);

    void updateOrderStatus(String orderNo);
}
