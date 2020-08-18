package com.hbnu.order.controller;


import com.hbnu.order.service.PayLogService;
import com.hbnu.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-18
 */
@RestController
@RequestMapping("/order/pay-log")
@CrossOrigin
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    //生成微信支付二维码接口
    //参数是订单号
    @ApiOperation(value = "生成微信支付二维码接口")
    @GetMapping("createQrCode/{orderNo}")
    public Result createQrCode(@PathVariable String orderNo) {
        //返回信息，包含二维码地址，还有其他需要的信息
        Map map = payLogService.createQrCode(orderNo);
        System.out.println("****返回二维码map集合:"+map);
        return Result.sucess().setData(map);
    }

    //查询订单支付状态
    //参数：订单号，根据订单号查询 支付状态
    @ApiOperation(value = "查询订单支付状态")
    @GetMapping("queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo) {
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("*****查询订单状态map集合:"+map);
        if(map == null) {
            return Result.failed().setMessage("支付出错了");
        }
        //如果返回map里面不为空，通过map获取订单状态
        if(map.get("trade_state").equals("SUCCESS")) {//支付成功
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrdersStatus(map);
            return Result.sucess().setMessage("支付成功");
        }
        return Result.sucess().setCode(25000).setMessage("支付中");

    }

}

