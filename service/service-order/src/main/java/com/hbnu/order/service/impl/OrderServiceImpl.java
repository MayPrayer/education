package com.hbnu.order.service.impl;

import com.hbnu.order.client.EduClient;
import com.hbnu.order.client.UcentClient;
import com.hbnu.order.entity.Order;
import com.hbnu.order.mapper.OrderMapper;
import com.hbnu.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbnu.order.utils.OrderNoUtil;
import com.hbnu.util.ordervo.CourseWebVoOrder;
import com.hbnu.util.ordervo.UcenterMemberOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-18
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcentClient  ucentClient;

    @Override
    public String makeorder(String courseid, String memid) {
      //远程调用方法 获取会员信息
        UcenterMemberOrder userInfoOrder = ucentClient.getMemInfo(memid);
        //远程调用方法获取 课程信息
        CourseWebVoOrder courseInfoOrder= eduClient.getCourseInforder(courseid);
        //设置并保存订单信息
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseid); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memid);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
