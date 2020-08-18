package com.hbnu.order.client;

import com.hbnu.util.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname UcentClient
 * @Author MayPrayer
 * @Description TODO
 * @Date 2020/8/18 10:01
 */
@Component
@FeignClient("service-ucenter")
public interface UcentClient {

    //全路径
    @GetMapping("/ucenter/member/getMemInfo/{memid}")
    public UcenterMemberOrder getMemInfo(@PathVariable("memid") String memid);
}
