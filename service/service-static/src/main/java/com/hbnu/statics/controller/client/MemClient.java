package com.hbnu.statics.controller.client;

import com.hbnu.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname MemClient
 * @Author MayPrayer
 * @Description TODO
 * @Date 2020/8/18 19:11
 */
@Component
@FeignClient("service-ucenter")
public interface MemClient {

    @GetMapping("/ucenter/member/countRegister/{day}")
    public Result countRegister(@PathVariable("day") String day);
}
