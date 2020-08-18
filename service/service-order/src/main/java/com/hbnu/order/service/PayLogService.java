package com.hbnu.order.service;

import com.hbnu.order.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-18
 */
public interface PayLogService extends IService<PayLog> {

    Map createQrCode(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrdersStatus(Map<String, String> map);
}
