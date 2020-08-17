package com.hbnu.ucenter.util;

import lombok.Data;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ReadPropertiesConfig implements InitializingBean {
    @Value("${wx.open.app_id}")
    private String keyid;
    @Value("${wx.open.app_secret}")
    private String keysecret;
    @Value("${wx.open.redirect_url}")
    private String redirect_url;


    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String REDIRCT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID=keyid;
        KEY_SECRET=keysecret;
        REDIRCT_URL=redirect_url;
    }

}