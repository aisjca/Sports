package com.jc.order.service.impl;

import com.jc.order.client.EduClient;
import com.jc.order.client.UcenterClient;
import com.jc.order.entity.Order;
import com.jc.order.mapper.OrderMapper;
import com.jc.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.utils.OrderUtils;
import com.jc.utils.ordervo.CourseWebVoOrder;
import com.jc.utils.ordervo.UcenterMemberOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author jc
 * @since 2020-11-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrders(String courseId, String memberId) {
        CourseWebVoOrder courseWebVoOrder = eduClient.getCourseInfoOrder(courseId);
        UcenterMemberOrder ucenterMemberOrder = ucenterClient.getMemberInfoOrder(memberId);
        Order order = new Order();
        order.setOrderNo(OrderUtils.CreateOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseWebVoOrder.getTitle());
        order.setCourseCover(courseWebVoOrder.getCover());
        order.setTeacherName(courseWebVoOrder.getTeacherName());
        order.setTotalFee(courseWebVoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(ucenterMemberOrder.getMobile());
        order.setNickname(ucenterMemberOrder.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
        return order.getOrderNo();
    }

    @Override
    public void updateOrderStatus(String orderNo) {
        baseMapper.updateOrderStatus(orderNo);
    }
}
