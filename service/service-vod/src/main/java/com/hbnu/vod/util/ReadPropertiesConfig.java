package com.hbnu.vod.util;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ClassName: ReadPropertiesConfig <br/>
 * Description: <br/>
 * date: 2020/8/13 9:47<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Data
@Component
public class ReadPropertiesConfig implements InitializingBean {
    @Value("${aliyun.vod.file.keyid}")
    private String keyid;
    @Value("${aliyun.vod.file.keysecret}")
    private String keysecret;


    public static String KEY_ID;
    public static String KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID=keyid;
        KEY_SECRET=keysecret;
    }

}
