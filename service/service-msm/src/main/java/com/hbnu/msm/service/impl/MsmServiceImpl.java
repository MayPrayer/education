package com.hbnu.msm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.hbnu.msm.Utils.ReadPropertiesConfig;
import com.hbnu.msm.service.MsmService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Classname MsmServiceImpl
 * @Author MayPrayer
 * @Description TODO
 * @Date 2020/8/15 14:27
 */
@Service
@Slf4j
public class MsmServiceImpl implements MsmService {
    /*使用redis过期时间完成，5分钟验证码失效*/
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public Boolean sendMsg(Map<String, Object> param, String phone) {

        //只有在redis中娶不到数据才能调用发送方法
        String code = redisTemplate.opsForValue().get(phone);
        if (StringUtils.isEmpty(code)) {
            try {
                send(param, phone);
            } catch (Exception e) {
                return false;
            }
            //发送成功后将值放入redis中并配置有效时间5分钟

            redisTemplate.opsForValue().set(phone, (String) param.get("code"), 1, TimeUnit.MINUTES);
            return true;
        } else {
            return false;
        }


    }

    @Override
    public void bombMsg(Map<String, Object> param, String phone, int count) {
        for (int i = 0; i < count; i++) {
            try {
                send(param, phone);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void send(Map<String, Object> param, String phone) throws Exception {
        //获取用户凭证
        DefaultProfile profile = DefaultProfile.getProfile("default", ReadPropertiesConfig.KEY_ID, ReadPropertiesConfig.KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        //封装发送信息请求
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        //选则发送短信服务
        request.setSysAction("SendSms");

        //设置手机号，签名，模板code ，模板参数，验证码，outid
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "飞翔在线教育网站");
        request.putQueryParameter("TemplateCode", "SMS_199796809");
        //将验证码转换成json参数
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();

        }
    }


}



