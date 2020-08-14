package com.hbnu.edu.client;

import com.hbnu.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: VodClient <br/>
 * Description: <br/>
 * date: 2020/8/13 18:01<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Component
@FeignClient(name = "service-vod", fallback = VodClientImpl.class)
public interface VodClient {
    /*此处需要写完全映射路径,且注解需要写名称*/
    @DeleteMapping("/edd/vod/{videoid}")
    Result deleteOneVideo(@PathVariable("videoid") String videoid);

    @GetMapping("/edd/vod/test")
    Result test();
}
