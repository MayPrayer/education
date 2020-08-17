package com.hbnu.msm.Controller;

import com.hbnu.msm.Utils.RandomUtil;
import com.hbnu.msm.service.MsmService;
import com.hbnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname MsmController
 * @Author MayPrayer
 * @Description TODO
 * @Date 2020/8/15 14:12
 */
@RestController
@RequestMapping("esm/msm")
@CrossOrigin
@Slf4j
public class MsmController {

    @Autowired
    private MsmService msmService;

    @GetMapping("msg/{phone}")
    public Result sendMsg(@PathVariable String phone) {
        //生成验证码，封装参数
        String code = RandomUtil.getSixBitRandom();
        Map<String, Object> param = new HashMap();
        param.put("code", code);
        Boolean issend = msmService.sendMsg(param, phone);
        //
        log.error("执行了短信");
        return issend ? Result.sucess() : Result.failed();
    }


    //短信轰炸
    @GetMapping("msg/{phone}/{count}")
    public Result sendMsg(@PathVariable String phone,int count) {
        //生成验证码，封装参数
        String code = RandomUtil.getSixBitRandom();
        Map<String, Object> param = new HashMap();
        param.put("code", code);
        msmService.bombMsg(param, phone,count);
        return Result.sucess();
    }

}
