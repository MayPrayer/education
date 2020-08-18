package com.hbnu.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.hbnu.order.entity.Order;
import com.hbnu.order.entity.PayLog;
import com.hbnu.order.mapper.PayLogMapper;
import com.hbnu.order.service.OrderService;
import com.hbnu.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbnu.order.utils.HttpClient;
import com.hbnu.order.utils.ReadPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author MayPrayer
 * @since 2020-08-18
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private PayLogService payLogService;
    @Autowired
    private OrderService orderService;

    @Override
    public Map createQrCode(String orderNo) {
        //根据订单号查询订单信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_no", orderNo);
       Order order =  orderService.getOne(queryWrapper);
        //使用map封装需要的参数
        Map m = new HashMap();
        m.put("appid", ReadPropertiesConfig.APP_ID);
        m.put("mch_id", ReadPropertiesConfig.PARTNER);
        m.put("nonce_str", WXPayUtil.generateNonceStr());
        m.put("body", order.getCourseTitle()); //课程标题
        m.put("out_trade_no", orderNo); //订单号
        m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
        m.put("spbill_create_ip", "127.0.0.1");
        m.put("notify_url", ReadPropertiesConfig.NOTIFY_URL);
        m.put("trade_type", "NATIVE");

        //发送请求到微信固定接口
        HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
        Map map =null;
        try {
            httpClient.setXmlParam(WXPayUtil.generateSignedXml(m, ReadPropertiesConfig.PARTNER_KEY));
            //支持https
            httpClient.setHttps(true);
            //开始post提交
            httpClient.post();

            //获取返回内容
            String xml = httpClient.getContent();
            //通过wx工具包 jiangxml转换成map  map为二维码信息
           Map content =  WXPayUtil.xmlToMap(xml);
            //封装y有用的的信息
            //最终返回数据 的封装
            map= new HashMap();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", content.get("result_code"));  //返回二维码操作状态码
            map.put("code_url", content.get("code_url"));        //二维码地址
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        try {
            //1、封装参数
            Map m = new HashMap<>();
            m.put("appid", ReadPropertiesConfig.APP_ID);
            m.put("mch_id", ReadPropertiesConfig.PARTNER);
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            //2 发送httpclient
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,ReadPropertiesConfig.PARTNER_KEY));
            client.setHttps(true);
            client.post();

            //3 得到请求返回内容
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //6、转成Map再返回
            return resultMap;
        }catch(Exception e) {
            return null;
        }
    }

    @Override
    public void updateOrdersStatus(Map<String, String> map) {
//从map获取订单号
        String orderNo = map.get("out_trade_no");
        //根据订单号查询订单信息
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);

        //更新订单表订单状态
        if(order.getStatus().intValue() == 1) { return; }
        order.setStatus(1);//1代表已经支付
        orderService.updateById(order);

        //向支付表添加支付记录
        PayLog payLog = new PayLog();
        payLog.setOrderNo(orderNo);  //订单号
        payLog.setPayTime(new Date()); //订单完成时间
        payLog.setPayType(1);//支付类型 1微信
        payLog.setTotalFee(order.getTotalFee());//总金额(分)

        payLog.setTradeState(map.get("trade_state"));//支付状态
        payLog.setTransactionId(map.get("transaction_id")); //流水号
        payLog.setAttr(JSONObject.toJSONString(map));

        baseMapper.insert(payLog);
    }
}
