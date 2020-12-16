package com.jc.order.mapper;

import com.jc.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author jc
 * @since 2020-11-16
 */
public interface OrderMapper extends BaseMapper<Order> {

    void updateOrderStatus(String orderNo);
}
