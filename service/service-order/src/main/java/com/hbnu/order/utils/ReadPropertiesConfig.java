package com.hbnu.order.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ReadPropertiesConfig implements InitializingBean {
    @Value("${wx.pay.appid}")
    private String appid;
    @Value("${wx.pay.partner}")
    private String partner;
    @Value("${wx.pay.partnerkey}")
    private String partnerkey;
    @Value("${wx.pay.notifyurl}")
    private String notifyurl;


    public static String APP_ID;
    public static String PARTNER;
    public static String PARTNER_KEY;
    public static String NOTIFY_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID=appid;
        PARTNER=partner;
        PARTNER_KEY=partnerkey;
        NOTIFY_URL=notifyurl;
    }

}