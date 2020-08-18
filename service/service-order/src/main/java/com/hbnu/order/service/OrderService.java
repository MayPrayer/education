package com.hbnu.order.service;

import com.hbnu.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbnu.util.ordervo.UcenterMemberOrder;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-18
 */
public interface OrderService extends IService<Order> {

    String makeorder(String courseid, String memid);
}
