package com.hbnu.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbnu.order.entity.Order;
import com.hbnu.order.service.OrderService;
import com.hbnu.util.JwtUtil;
import com.hbnu.util.Result;
import com.hbnu.util.ordervo.UcenterMemberOrder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-18
 */
@RestController
@RequestMapping("/order/")
@Slf4j
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("返回订单id")
    @PostMapping ("createoredr/{courseid}")
    public Result addOrder(@PathVariable String courseid , HttpServletRequest request){
        String memid = JwtUtil.getMemberIdByJwtToken(request);
        String orderNo = orderService.makeorder(courseid,memid);
        return  Result.sucess().setData(orderNo);
    }

    @ApiOperation("根据订单id生成订单信息")
    @PostMapping("orderinfo/{orderno}")
    public Result getOrderInfo(@PathVariable String orderno , HttpServletRequest request){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_no",orderno);
        Order orderinfo = orderService.getOne(queryWrapper);
        return  Result.sucess().setData(orderinfo);
    }



}

